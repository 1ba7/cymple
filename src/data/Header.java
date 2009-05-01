package cymple.data;
import cymple.common.SelectItemData;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Header {
	protected RandomAccessFile file;
	protected int headerSize;

	protected long minTime;
	protected long maxTime;

	protected int userCount;
	protected int artistCount;
	protected int albumCount;
	protected int trackCount;

	protected User[] users;
	protected Artist[] artists;
	protected Album[] albums;
	protected Track[] tracks;

	public Header(String filename) {
		try {
			file = new RandomAccessFile(filename, "r");
			if (file.readInt() != 0x20080427) {
				throw new InvalidFileFormatException();
			}
			
			headerSize = file.readInt();
			minTime = file.readLong();
			maxTime = file.readLong();

			userCount = file.readUnsignedShort();
			artistCount = file.readUnsignedShort();
			albumCount = file.readUnsignedShort();
			trackCount = file.readUnsignedShort();

			readUsers();
			readArtists();
		}
		catch (IOException e) {
			throw new InvalidFileFormatException(e);
		}
	}

	public void finalize() {
		try {
			file.close();
		}
		catch (IOException e) {}
	}

	private void readUsers() throws IOException {
		String name;
		int index;
		byte[] bytes;

		file.seek(32);
		bytes = new byte[file.readInt()];
		file.readFully(bytes);
		index = 2;
		users = new User[userCount];

		for (int i = 0; i < userCount; i++) {
			name = readString(bytes, index);
			users[i] = new User(name, i);
			index += 1 + name.length();
		}
	}

	private void readArtists() throws IOException {
		String name;
		Album[] albums;
		Track[] tracks;
		int index;
		int trackId = 0, albumId = 0;
		byte[] bytes;

		bytes = new byte[file.readInt()];
		file.readFully(bytes);
		index = 2;
		artists = new Artist[artistCount];
		this.albums = new Album[albumCount];
		this.tracks = new Track[trackCount];

		for (int i = 0; i < artistCount; i++) {
			name = readString(bytes, index);
			index += 1 + name.length();
			albums = new Album[readShort(bytes, index)];
			index += 2;

			artists[i] = new Artist(name, i, albums);
			for (int j = 0; j < albums.length; j++, albumId++) {
				name = readString(bytes, index);
				index += 1 + name.length();
				tracks = new Track[readShort(bytes, index)];
				index += 2;

				albums[j] = new Album(name, albumId, artists[i], tracks);
				this.albums[albumId] = albums[j];
				for (int k = 0; k < tracks.length; k++, trackId++) {
					name = readString(bytes, index);
					index += 1 + name.length();
					tracks[k] = new Track(name, trackId, albums[j]);
					this.tracks[trackId] = tracks[k];
				}
			}
		}
		this.albums = getAlbumsByArtists(artists);
		this.tracks = getTracksFromAlbums(this.albums);
	}

	private short readShort(byte[] bytes, int i) {
		return (short)(((bytes[i] & 255) << 8) + (bytes[i + 1] & 255));
	}

	private String readString(byte[] bytes, int index) {
		try {
			return new String(bytes, index + 1, bytes[index] & 255, "UTF8");
		}
		catch (java.io.UnsupportedEncodingException e) {
			throw new InvalidFileFormatException(e);
		}
	}

	public int userCount() {
		return userCount;
	}

	public int artistCount() {
		return artistCount;
	}

	public int albumCount() {
		return albumCount;
	}

	public int trackCount() {
		return trackCount;
	}

	public User[] getUsers() {
		return users;
	}

	public Artist[] getArtists() {
		return artists;
	}

	public Album[] getAlbumsByArtists(SelectItemData[] artists) {
		Album[] result;
		int size = 0, i;

		for (i = 0; i < artists.length; i++) {
			size += ((Artist)artists[i]).albumCount();
		}
		result = new Album[size];

		Album[] albums;
		size = 0;
		for (i = 0; i < artists.length; i++) {
			albums = ((Artist)artists[i]).getAlbums();
			System.arraycopy(albums, 0, result, size, albums.length);
			size += albums.length;
		}

		return result;
	}

	public Artist[] getArtistsByAlbums(SelectItemData[] albums) {
		Artist[] result;
		Artist lastArtist = null;
		int size = 0,  i;

		for (i = 0; i < albums.length; i++) {
			if (((Album)albums[i]).getArtist() != lastArtist) {
				lastArtist = ((Album)albums[i]).getArtist();
				size++;
			}
		}

		result = new Artist[size];
		size = 0;
		for (i = 0; i < albums.length; i++) {
			if (((Album)albums[i]).getArtist() != lastArtist) {
				lastArtist = ((Album)albums[i]).getArtist();
				result[size] = lastArtist;
				size++;
			}
		}

		return result;
	}

	public Track[] getTracksFromAlbums(SelectItemData[] albums) {
		Track[] result;
		int size = 0, i;

		for (i = 0; i < albums.length; i++) {
			size += ((Album)albums[i]).trackCount();
		}
		result = new Track[size];

		Track[] tracks;
		size = 0;
		for (i = 0; i < albums.length; i++) {
			tracks = ((Album)albums[i]).getTracks();
			System.arraycopy(tracks, 0, result, size, tracks.length);
			size += tracks.length;
		}

		return result;
	}

	protected int toIntTime(long time) {
		return (int)((time - minTime) * 1024.0 / (maxTime - minTime));
	}

	protected long toLongTime(int time) {
		return minTime + (long)(time * (maxTime - minTime) / 1024.0);
	}

	protected String startString(int start, int finish) {
		return startString(toLongTime(start), toLongTime(finish));
	}

	protected String finishString(int start, int finish) {
		return finishString(toLongTime(start), toLongTime(finish));
	}

	public String startString(long start, long finish) {
		return getFormat(start, finish).format(new Date(start));
	}

	public String finishString(long start, long finish) {
		return getFormat(start, finish).format(new Date(finish));
	}

	public String rangeString(long start, long finish) {
		return "from " + getFormat(start, finish).format(new Date(start)) +
			" to " + getFormat(start, finish).format(new Date(finish));
	}

	private SimpleDateFormat getFormat(long start, long finish) {
		long difference = finish - start;

		if (difference < 86400000L) {
			return new SimpleDateFormat("MMMM dd, kk:mm, yyyy");
		}
		else if (difference < 15552000000L) {
			return new SimpleDateFormat("MMMM dd, yyyy");
		}
		else {
			return new SimpleDateFormat("MMMM yyyy");
		}
	}
}
