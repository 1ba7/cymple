// Represents a user. Like all ChartKey types, every user has a unique id
// which is a short value.
public class User implements SelectMenuItem, Comparable<User> {
	// The name of the user.
	private String name;
	// The user's unique id.
	private short id;

	// Returns the user's unique id.
	protected short id() {
	}

	// Sorts alphabetically.
	public int compareTo(User other) {
	}

	// Returns the name attribute.
	public String toString() {
	}

}
