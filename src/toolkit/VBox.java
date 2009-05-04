package cymple.toolkit;

public class VBox extends Container {
	private int internalHeight;
	private int defaultHeight;

	public VBox() {
		this(20);
	}

	public VBox(int defaultHeight) {
		super();
		this.internalHeight = 0;
		this.defaultHeight = defaultHeight;
	}

	public void add(Widget child) {
		this.add(child, defaultHeight);
	}

	public void add(Widget child, int widgetHeight) {
		children.add(child);
		child.setParent(this);
		child.setPosition(0, internalHeight);
		child.setSize(getWidth(), widgetHeight);
		internalHeight += widgetHeight;
	}

	public int getInternalHeight() {
		return internalHeight;
	}
}
