import cymple.data.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class GenerateBody extends Header {
	private long[] totalListens;
	private long[][] userListens;
	private long[][] artistListens;
	private long[][] albumListens;
	private long[][] trackListens;
	private long[][] userTrackListens;
	private Map<String, User> userMap;
	private Map<String, Artist> artistMap;
	private Map<Artist, Map<String, Album>> albumMap;
	private Map<Artist, Map<Album, Map<String, Track>>> trackMap;

	public GenerateBody(String filename) throws Exception {
		super(filename);
		totalListens = new long[1024];
		userListens = new long[userCount][];
		for (int i = 0; i < userListens.length; i++) {
			userListens[i] = new long[1024];
		}
		artistListens = new long[artistCount][];
		for (int i = 0; i < artistListens.length; i++) {
			artistListens[i] = new long[1024];
		}
		albumListens = new long[albumCount][];
		for (int i = 0; i < albumListens.length; i++) {
			albumListens[i] = new long[1024];
		}
		trackListens = new long[trackCount][];
		for (int i = 0; i < trackListens.length; i++) {
			trackListens[i] = new long[1024];
		}
		userTrackListens = new long[userCount * trackCount][];
		for (int i = 0; i < userTrackListens.length; i++) {
			userTrackListens[i] = new long[1024];
		}
		makeMaps();
	}

	private void makeMaps() {
		userMap = new HashMap<String, User>();
		for (User user: users) {
			userMap.put(user.getName(), user);
		}
		artistMap = new HashMap<String, Artist>();
		albumMap = new HashMap<Artist, Map<String, Album>>();
		trackMap = new HashMap<Artist, Map<Album, Map<String, Track>>>();
		for (Artist artist: artists) {
			artistMap.put(artist.getName(), artist);
			albumMap.put(artist, new HashMap<String, Album>());
			trackMap.put(artist, new HashMap<Album, Map<String, Track>>());
			for (Album album: albums) {
				albumMap.get(artist).put(album.getTitle(), album);
				trackMap.get(artist).put(album, new HashMap<String, Track>());
				for (Track track: tracks) {
					trackMap.get(artist).get(album).put(track.getTitle(), track);
				}
			}
		}
	}

	public void readCSV(String filename) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		String line;
		String[] tokens;
		User user;
		Artist artist;
		Album album;
		Track track;
		int time;
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH);
		while ((line = input.readLine()) != null) {
			tokens = line.trim().split(",");
			user = userMap.get(tokens[0]);
			artist = artistMap.get(tokens[1]);
			album = albumMap.get(artist).get(tokens[2]);
			track = trackMap.get(artist).get(album).get(tokens[3]);
			time = scale.toIntTime(sdf.parse(tokens[4]).getTime());
			userTrackListens[user.id() * trackCount + track.id()][time] += 1;
		}
	}

	public void fillRandomly() {
		for (int i = 0; i < userTrackListens.length; i++) {
			for (int j = 0; j < 1024; j++) {
				userTrackListens[i][j] = (long)(Math.random() * (1L << 53) / 
					userTrackListens.length);
			}
		}
	}

	public void normalize() {
		for (int i = 0; i < userTrackListens.length; i++) {
			for (int j = 1; j < 1024; j++) {
				userTrackListens[i][j] += userTrackListens[i][j - 1];
			}
		}
		long[] user, track, album, artist;
		for (int i = 0; i < userTrackListens.length; i++) {
			user = userListens[i / trackCount];
			track = trackListens[i % trackCount];
			album = albumListens[tracks[i % trackCount].getAlbum().id()];
			artist = artistListens[tracks[i % trackCount].getArtist().id()];
			for (int j = 0; j < 1024; j++) {
				user[j] += userTrackListens[i][j];
				track[j] += userTrackListens[i][j];
				album[j] += userTrackListens[i][j];
				album[j] += userTrackListens[i][j];
				totalListens[j] += userTrackListens[i][j];
			}
		}
	}

	public void write(String filename) throws Exception {
		FileOutputStream output = new FileOutputStream(filename);
		file.seek(0);
		byte[] bytes = new byte[headerSize];
		file.read(bytes);
		output.write(bytes);
		output.write(asBytes(totalListens));
		for (int i = 0; i < userListens.length; i++) {
			output.write(asBytes(userListens[i]));
		}
		for (int i = 0; i < artistListens.length; i++) {
			output.write(asBytes(artistListens[i]));
		}
		for (int i = 0; i < albumListens.length; i++) {
			output.write(asBytes(albumListens[i]));
		}
		for (int i = 0; i < trackListens.length; i++) {
			output.write(asBytes(trackListens[i]));
		}
		for (int i = 0; i < userTrackListens.length; i++) {
			output.write(asBytes(userTrackListens[i]));
		}
		output.close();
		System.out.println("Finished, wrote " + totalListens[1023] + " listens.");
	}

	public static void main(String args[]) throws Exception {
		String header = null, output = null, csv = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--csv")) {
				csv = args[++i];
				continue;
			}
			if (header == null) {
				header = args[i];
				continue;
			}
			if (output == null) {
				output = args[i];
				continue;
			}
		}
		if (header == null) {
			header = "header.bin";
		}
		if (output == null) {
			output = "cymple.bin";
		}
		System.out.println("Allocating memory...");
		GenerateBody body = new GenerateBody(header);
		if (csv != null) {
			System.out.println("Reading data...");
			body.readCSV(csv);
		}
		else {
			System.out.println("Generating data...");
			body.fillRandomly();
		}
		System.out.println("Normalizing data...");
		body.normalize();
		System.out.println("Writing data...");
		body.write(output);
	}

	public byte[] asBytes(long[] longs) {
		byte[] bytes = new byte[longs.length * 8];
		long n;
		for (int i = 0; i < bytes.length;) {
			n = longs[i / 8];
			bytes[i++] = (byte)(n >> 56);
			bytes[i++] = (byte)(n >> 48);
			bytes[i++] = (byte)(n >> 40);
			bytes[i++] = (byte)(n >> 32);
			bytes[i++] = (byte)(n >> 24);
			bytes[i++] = (byte)(n >> 16);
			bytes[i++] = (byte)(n >> 8);
			bytes[i++] = (byte)(n);
		}
		return bytes;
	}
}
