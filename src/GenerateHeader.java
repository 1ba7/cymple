import java.io.*;
import java.util.*;
import java.text.*;

public class GenerateHeader {
	public static BufferedReader input;
	public static long start;
	public static long finish;
	public static Set<String> users;
	public static Map<String, Map<String, Set<String>>> artists;

	public static void main(String args[]) throws Exception {
		try {
			input = new BufferedReader(new FileReader(args[0]));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			input = new BufferedReader(new FileReader("dataset.csv"));
		}
		String output;
		try {
			output = args[1];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			output = "header.bin";
		}

		start = 0;
		finish = 0;
		users = new TreeSet<String>();
		artists = new TreeMap<String, Map<String, Set<String>>>();

		String line, user, artist, album, track;
		long time;
		while ((line = input.readLine()) != null) {
			String[] tokens = line.trim().split(",");
			user = tokens[0].intern();
			artist = tokens[1].intern();
			album = tokens[2].intern();
			track = tokens[3].intern();
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH);
			time = sdf.parse(tokens[4]).getTime();

			users.add(user);
			if (artists.get(artist) == null) {
				artists.put(artist, new TreeMap<String, Set<String>>());
			}
			if (artists.get(artist).get(album) == null) {
				artists.get(artist).put(album, new TreeSet<String>());
			}
			artists.get(artist).get(album).add(track);
			if (time < start || start == 0) {
				start = time;
			}
			if (time > finish) {
				finish = time;
			}
		}
		input.close();
		write(output);
	}

	public static void write(String filename) throws Exception {
		DataOutputStream output = new DataOutputStream(new FileOutputStream(filename));
		int headerSize = headerSize();
		byte[] bytes = new byte[headerSize];
		writeInt(bytes, 0, 0x20080427);
		writeInt(bytes, 4, headerSize);
		writeLong(bytes, 8, start);
		writeLong(bytes, 16, finish);
		writeShort(bytes, 24, (short)users.size());
		writeShort(bytes, 26, (short)artists.size());
		int albumCount = 0, trackCount = 0;
		for (String artist: artists.keySet()) {
			for (String album: artists.get(artist).keySet()) {
				albumCount++;
				trackCount += artists.get(artist).get(album).size();
			}
		}
		writeShort(bytes, 28, (short)albumCount);
		writeShort(bytes, 30, (short)trackCount);
		int usersSize, artistsSize;
		usersSize = usersSize();
		artistsSize = artistsSize();
		writeInt(bytes, 32, usersSize + 2);
		writeShort(bytes, 36, (short)users.size());
		System.arraycopy(usersBytes(), 0, bytes, 38, usersSize);
		writeInt(bytes, usersSize + 38, artistsSize + 2);
		writeShort(bytes, usersSize + 42, (short)artists.size());
		System.arraycopy(artistsBytes(), 0, bytes, 44 + usersSize, artistsSize);
		output.write(bytes);
		output.close();
	}

	public static int headerSize() throws Exception {
		return 44 + usersSize() + artistsSize();
	}

	public static int usersSize() throws Exception {
		int result = 0;
		for (String user: users) {
			result += toBytes(user).length;
		}
		return result;
	}

	public static int artistsSize() throws Exception {
		int result = 0;
		for (String artist: artists.keySet()) {
			result += toBytes(artist).length + 2 + albumsSize(artist);
		}
		return result;		
	}

	public static int albumsSize(String artist) throws Exception {
		int result = 0;
		for (String album: artists.get(artist).keySet()) {
			result += toBytes(album).length + 2 + tracksSize(artist, album);
		}
		return result;
	}

	public static int tracksSize(String artist, String album) throws Exception {
		int result = 0;
		for (String track: artists.get(artist).get(album)) {
			result += toBytes(track).length;
		}
		return result;
	}

	public static byte[] usersBytes() throws Exception {
		byte[] result = new byte[usersSize()];
		byte[] string;
		int size = 0;
		for (String user: users) {
			string = toBytes(user);
			System.arraycopy(string, 0, result, size, string.length);
			size += string.length;
		}
		return result;
	}

	public static byte[] artistsBytes() throws Exception {
		byte[] result = new byte[artistsSize()];
		byte[] string;
		int size = 0;
		for (String artist: artists.keySet()) {
			string = toBytes(artist);
			System.arraycopy(string, 0, result, size, string.length);
			size += string.length;
			string = albumsBytes(artist);
			writeShort(result, size, (short)artists.get(artist).size());
			System.arraycopy(string, 0, result, size + 2, string.length);
			size += string.length + 2;
		}
		return result;
	}

	public static byte[] albumsBytes(String artist) throws Exception {
		byte[] result = new byte[albumsSize(artist)];
		byte[] string;
		int size = 0;
		for (String album: artists.get(artist).keySet()) {
			string = toBytes(album);
			System.arraycopy(string, 0, result, size, string.length);
			size += string.length;
			string = tracksBytes(artist, album);
			writeShort(result, size, (short)artists.get(artist).get(album).size());
			System.arraycopy(string, 0, result, size + 2, string.length);
			size += string.length + 2;
		}
		return result;
	}

	public static byte[] tracksBytes(String artist, String album) throws Exception {
		byte[] result = new byte[tracksSize(artist, album)];
		byte[] string;
		int size = 0;
		for (String track: artists.get(artist).get(album)) {
			string = toBytes(track);
			System.arraycopy(string, 0, result, size, string.length);
			size += string.length;
		}
		return result;
	}

	public static byte[] toBytes(String string) throws Exception {
		byte[] bytes = string.getBytes("UTF8");
		byte[] result = new byte[1 + bytes.length];
		result[0] = (byte)bytes.length;
		System.arraycopy(bytes, 0, result, 1, bytes.length);
		return result;
	}

	public static void writeShort(byte[] bytes, int i, short n) {
		bytes[i++] = (byte)(n >> 8);
		bytes[i] = (byte)(n);
	}

	public static void writeInt(byte[] bytes, int i, int n) {
		bytes[i++] = (byte)(n >> 24);
		bytes[i++] = (byte)(n >> 16);
		bytes[i++] = (byte)(n >> 8);
		bytes[i++] = (byte)(n);
	}

	public static void writeLong(byte[] bytes, int i, long n) {
		bytes[i++] = (byte)(n >> 56);
		bytes[i++] = (byte)(n >> 48);
		bytes[i++] = (byte)(n >> 40);
		bytes[i++] = (byte)(n >> 32);
		bytes[i++] = (byte)(n >> 24);
		bytes[i++] = (byte)(n >> 16);
		bytes[i++] = (byte)(n >> 8);
		bytes[i++] = (byte)(n);
	}
}
