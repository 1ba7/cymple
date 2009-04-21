// Represents an track. Like all ChartKey types, every track has a unique id
// which is a short value.
public class Track implements SelectItemData, Comparable<Track> {
	// The title of the track.
	private String title;
	// The album's unique id.
	private short id;
	// The album that this track is from.
	private Album album;

	// Returns the title attribute.
	public String getTitle() {
	}

	// Returns the track's unique id.
	public short id() {
	}

	// Returns the album that this track is from.
	public Album getAlbum() {
	}

	// Returns the artist associated with this track.
	public Artist getArtist() {
	}

	// Sorts alphabetically.
	public int compareTo(Track other) {
	}

	// Returns "#{artist} -
	public String toString() {
	}

}
