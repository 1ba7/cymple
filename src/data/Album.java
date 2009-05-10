package cymple.data;
import cymple.common.*;

public class Album implements SelectItemData {
	private String title;
	private int id;
	private Artist artist;
	private Track[] tracks;

	protected Album(String title, int id, Artist artist, Track[] tracks) {
		this.title = title;
		this.id = id;
		this.artist = artist;
		this.tracks = tracks;
	}

	public String getTitle() {
		return title;
	}

	public int id() {
		return id;
	}

	public Artist getArtist() {
		return artist;
	}

	public int trackCount() {
		return tracks.length;
	}

	public Track[] getTracks() {
		return tracks;
	}

	public String toString() {
		return getArtist().getName() + "," + title;
	}

	public int compareTo(SelectItemData other) {
		return id == other.id() ? 0 : (id > other.id() ? 1 : -1);
	}
}
