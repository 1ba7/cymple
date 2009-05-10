package cymple.data;
import cymple.common.*;

public class Track implements SelectItemData {
	private String title;
	private int id;
	private Album album;

	protected Track(String title, int id, Album album) {
		this.title = title;
		this.id = id;
		this.album = album;
	}

	public String getTitle() {
		return title;
	}

	public int id() {
		return id;
	}

	public Album getAlbum() {
		return album;
	}

	public Artist getArtist() {
		return album.getArtist();
	}

	public String toString() {
		return getArtist().getName() + "," + title;
	}

	public int compareTo(SelectItemData other) {
		return id == other.id() ? 0 : (id > other.id() ? 1 : -1);
	}
}
