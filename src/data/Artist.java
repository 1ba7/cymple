package cymple.data;
import cymple.common.*;

public class Artist implements SelectItemData {
	private String name;
	private int id;
	private Album[] albums;

	protected Artist(String name, int id, Album[] albums) {
		this.name = name;
		this.id = id;
		this.albums = albums;
	}

	public String getName() {
		return name;
	}

	public int id() {
		return id;
	}

	public int albumCount() {
		return albums.length;
	}

	public Album[] getAlbums() {
		return albums;
	}

	public String toString() {
		return name;
	}

	public int compareTo(SelectItemData other) {
		return id == other.id() ? 0 : (id > other.id() ? 1 : -1);
	}
}
