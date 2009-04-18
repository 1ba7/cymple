// Represents an artist. Like all ChartKey types, every artist has a unique
// id which is a short value.
public class Artist implements SelectItemData, Comparable<Artist> {
	// The name of the artist.
	private String name;
	// The artist's unique id.
	private short id;
	// An array of albums by this artist.
	private Album[] albums;

	// Returns the name attribute.
	public String getName() {
	}

	// Returns the artists's unique id.
	public short id() {
	}

	// Returns the number of albums by this artist.
	public int albumsSize() {
	}

	// Returns an array of albums by this artist.
	public Album[] getAlbums() {
	}

	// Sorts alphabetically.
	public int compareTo(Artist other) {
	}

	// Returns the name attribute.
	public String toString() {
	}

}
