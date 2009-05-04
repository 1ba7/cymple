package cymple.data;
import cymple.common.*;

public class User implements SelectItemData {
	private String name;
	private int id;

	protected User(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public int id() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
