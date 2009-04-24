package cymple.data;
import cymple.common.*;

// Represents a user. Like all SelectItemData types, every user has a unique
// id which is an int value.
public class User implements SelectItemData {
	// The name of the user.
	private String name;
	// The user's unique id.
	private int id;

    // Constructs a user.
	protected User(String name, int id) {
		this.name = name;
		this.id = id;
	}

	// Returns the user's unique id.
	public int id() {
		return id;
	}

	// Returns the name attribute.
	public String toString() {
		return name;
	}
}
