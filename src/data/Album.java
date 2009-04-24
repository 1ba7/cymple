package cymple.data;
import cymple.common.*;

// Represents an album. Like all SelectItemData types, every album has a
// unique id which is an int value.
public class Album implements SelectItemData {
	// The title of the album.
	private String title;
	// The album's unique id.
	private int id;
	// The artist associated with this album.
	private Artist artist;
	// An array of tracks from this album.
	private Track[] tracks;

	// Constructs an Album.
	protected Album(String title, int id, Artist artist, Track[] tracks) {
		this.title = title;
		this.id = id;
		this.artist = artist;
		this.tracks = tracks;
	}

	// Returns the title attribute.
	public String getTitle() {
		return title;
	}

	// Returns the album's unique id.
	public int id() {
		return id;
	}

	// Returns the artist associated with this album.
	public Artist getArtist() {
		return artist;
	}

	// Returns the number of tracks from this album.
	public int trackCount() {
		return tracks.length;
	}

	// Returns an array of albums by this artist.
	public Track[] getTracks() {
		return tracks;
	}

	// Returns "#{artist} -
	public String toString() {
		return getArtist().getName() + " — " + title;
	}
}
