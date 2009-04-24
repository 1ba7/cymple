package cymple.data;
import cymple.common.*;

// Represents an track. Like all SelectItemData types, every track has a
// unique id which is an int value.
public class Track implements SelectItemData {
	// The title of the track.
	private String title;
	// The album's unique id.
	private int id;
	// The album that this track is from.
	private Album album;

	// Constructs a Track.
	protected Track(String title, int id, Album album) {
		this.title = title;
		this.id = id;
		this.album = album;
	}

	// Returns the title attribute.
	public String getTitle() {
		return title;
	}

	// Returns the track's unique id.
	public int id() {
		return id;
	}

	// Returns the album that this track is from.
	public Album getAlbum() {
		return album;
	}

	// Returns the artist associated with this track.
	public Artist getArtist() {
		return album.getArtist();
	}

	// Returns "#{artist} - #{track}"
	public String toString() {
		return getArtist().getName() + " — " + title;
	}
}
