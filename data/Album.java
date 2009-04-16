// Represents an album. Like all ChartKey types, every album has a unique id
// which is a short value.
public class Album implements SelectItemData, Comparable<Album> {
	// The title of the album.
	private String title;
	// The album's unique id.
	private short id;
	// The artist associated with this album.
	private Artist artist;
	// An array of tracks from this album.
	private Track[] tracks;

	// Returns the title attribute.
	public String getTitle() {
	}

	// Returns the album's unique id.
	public short id() {
	}

	// Returns the artist associated with this album.
	public Artist getArtist() {
	}

	// Returns the number of tracks from this album.
	public int tracksSize() {
	}

	// Returns an array of albums by this artist.
	public Track[] getTracks() {
	}

	// Sorts alphabetically.
	public int compareTo(Album other) {
	}

	// Returns "#{artist} -
	public String toString() {
	}

}
