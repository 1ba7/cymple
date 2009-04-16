// This interface is implemented by User, Artist, Album and Track, to be used
// in the select menu.
public interface SelectItemData {

	// Returns the id of this object.
	public short id();

	// Returns the String representation of this object.
	public String toString();

}
