package cymple.data;
import cymple.common.*;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Data extends Header implements Graphable, Chartable, Status, Runnable {
	private ListenVector total;
	private Seeker seeker;
	private GraphData graphData;
	private ChartData chartData;
	private int update;
	private String status;
	volatile private double complete;
	private Set<User> selectedUsers;
	private Set<Track> selectedTracks;

	public Data(String filename) {
		super(filename);
		total = readListenVector();
		selectedUsers = new HashSet<User>();
		selectedTracks = new HashSet<Track>();
		(new Thread(this)).start();
		this.update();
		Map<String, Integer> users, artists, albums, tracks;
	}

	public void connect(Seeker seeker) {
		this.seeker = seeker;
	}

	public String startString() {
		return startString(rangeStart(), rangeFinish());
	}

	public String finishString() {
		return startString(rangeStart(), rangeFinish());
	}

	public String rangeString() {
		return rangeString(rangeStart(), rangeFinish());
	}

	public GraphData getGraphData() {
		return graphData;
	}

	public ChartData getChartData() {
		return chartData;
	}

	public void update() {
		switch (update) {
			case 0:
				update = 1;
				synchronized(this) {
					notifyAll();
				}
				break;
			case 1:
				update = 2;
		}
	}

	public String getStatus() {
		return status;
	}

	public double getComplete() {
		return complete;
	}

	protected void resetStatus() {
		status = "Visualising " + size() + " listens " + rangeString();
		complete = 0;
	}

	public synchronized void run() {
		while(true) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
			while (update > 0) {
				updateGraph();
				updateChart();
				update -= 1;
			}
		}
	}

	private void updateGraph() {
		status = "Updating graph...";
		complete = 0.5;
		graphData = new GraphData(total, seeker.getResolution());
		resetStatus();
	}

	private void updateChart() {
		int start = rangeStart();
		int finish = rangeFinish();
		Map<String, Long> users, artists, albums, tracks;

		Map<ChartKey, Map<String, Long>> map;
		map = new HashMap<ChartKey, Map<String, Long>>();

		// In Ruby, this is map[ChartKey.User] = user = new Hash(0)
		map.put(ChartKey.User, users = new HashMap<String, Long>());
		map.put(ChartKey.Artist, artists = new HashMap<String, Long>());
		map.put(ChartKey.Album, albums = new HashMap<String, Long>());
		map.put(ChartKey.Track, tracks = new HashMap<String, Long>());

		long listens;
		String key;
		status = "Updating chart...";
		complete = 0;
		double offset = 1.0 / (selectedUsers.size() * selectedTracks.size());
		for (User user: selectedUsers) {
			for (Track track: selectedTracks) {
				listens = getListens(user, track, start, finish);
				// In Ruby, this is users[user.to_s] += listens ;_;
				users.put(key = user.toString(),
					(users.get(key) == null ? 0 : users.get(key)) + listens);
				artists.put(key = track.getArtist().toString(),
					(artists.get(key) == null ? 0 : artists.get(key)) + listens);
				albums.put(key = track.getAlbum().toString(),
					(albums.get(key) == null ? 0 : albums.get(key)) + listens);
				tracks.put(key = track.toString(),
					(tracks.get(key) == null ? 0 : tracks.get(key)) + listens);
				complete += offset;
			}
		}
		chartData = new ChartData(map);
		resetStatus();
	}

	private long getListens(User user, Track track, int start, int finish) {
		try {
			int offset = headerSize + ((userCount + artistCount + albumCount +
				trackCount + userCount * user.id() + track.id()) << 13);
			synchronized(file) {
				file.seek(offset + (finish << 3));
				long max = file.readLong();
				if (start == 0) {
					return max;
				}
				else {
					file.seek(offset + ((start - 1) << 3));
					return max - file.readLong();
				}
			}
		}
		catch (IOException e) {
			throw new InvalidFileFormatException(e);
		}
	}

	public void add(User user) {
		synchronized(total) {
			total.add(readListenVector(user));
			selectedUsers.add(user);
		}
		update();
	}

	public void add(Artist artist) {
		synchronized(total) {
			total.add(readListenVector(artist));
			for (Track track: getTracksFromAlbums(artist.getAlbums())) {
				selectedTracks.add(track);
			}
		}
		update();
	}

	public void add(Album album) {
		synchronized(total) {
			total.add(readListenVector(album));
			for (Track track: album.getTracks()) {
				selectedTracks.add(track);
			}
		}
		update();
	}

	public void add(Track track) {
		synchronized(total) {
			total.add(readListenVector(track));
			selectedTracks.add(track);
		}
		update();
	}

	public void remove(User user) {
		synchronized(total) {
			total.subtract(readListenVector(user));
			selectedUsers.remove(user);
		}
		update();
	}

	public void remove(Artist artist) {
		synchronized(total) {
			total.subtract(readListenVector(artist));
			for (Track track: getTracksFromAlbums(artist.getAlbums())) {
				selectedTracks.remove(track);
			}
		}
		update();
	}

	public void remove(Album album) {
		synchronized(total) {
			total.subtract(readListenVector(album));
			for (Track track: album.getTracks()) {
				selectedTracks.remove(track);
			}
		}
		update();
	}

	public void remove(Track track) {
		synchronized(total) {
			total.subtract(readListenVector(track));
			selectedTracks.remove(track);
		}
		update();
	}

	protected ListenVector readListenVector() {
		return readListenVector(headerSize);
	}

	protected ListenVector readListenVector(User user) {
		return readListenVector(headerSize + (user.id() << 13));
	}

	protected ListenVector readListenVector(Artist artist) {
		return readListenVector(headerSize + ((userCount + artist.id()) << 13));
	}

	protected ListenVector readListenVector(Album album) {
		return readListenVector(headerSize + ((userCount + artistCount +
			album.id()) << 13));
	}

	protected ListenVector readListenVector(Track track) {
		return readListenVector(headerSize + ((userCount + artistCount +
			albumCount + track.id()) << 13));
	}

	private ListenVector readListenVector(int offset) {
		try {
			byte[] bytes = new byte[8192];
			synchronized(file) {
				file.seek(offset);
				file.readFully(bytes);
			}
			return new ListenVector(bytes);
		}
		catch (IOException e) {
			throw new InvalidFileFormatException(e);
		}
	}

	public long size() {
		return total.between(rangeStart(), rangeFinish());
	}

	protected int rangeStart() {
		return (int)(1023.999999 * seeker.getPosition() *
			(1 - seeker.getResolution()));
	} 

	protected int rangeFinish() {
		return (int)(1023.999999 * ((seeker.getPosition() *
			(1 - seeker.getResolution())) + seeker.getResolution()));
	}
}
