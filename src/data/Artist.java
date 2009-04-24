package cymple.data;
import cymple.common.*;

// Represents an artist. Like all SelectItemData types, every artist has a
// unique id which is an int value.
public class Artist implements SelectItemData {
	// The name of the artist.
	private String name;
	// The artist's unique id.
	private int id;
	// An array of albums by this artist.
	private Album[] albums;

	// Constructs an Artist.
	protected Artist(String name, int id, Album[] albums) {
		this.name = name;
		this.id = id;
		this.albums = albums;
	}

	// Returns the name attribute.
	public String getName() {
		return name;
	}

	// Returns the artists's unique id.
	public int id() {
		return id;
	}

	// Returns the number of albums by this artist.
	public int albumCount() {
		return albums.length;
	}

	// Returns an array of albums by this artist.
	public Album[] getAlbums() {
		return albums;
	}

	// Returns the name attribute.
	public String toString() {
		return name;
	}
}
