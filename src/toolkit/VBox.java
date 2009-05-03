package cymple.toolkit;

public class VBox extends Container {
	private int height;
	private int defaultHeight;

	public VBox() {
		this(20);
	}

	public VBox(int defaultHeight) {
		super();
		this.height = 0;
		this.defaultHeight = defaultHeight;
	}

	public void add(Widget child) {
		this.add(child, defaultHeight);
	}

	public void add(Widget child, int widgetHeight) {
		children.add(child);
		child.setParent(this);
		child.setPosition(0, height);
		child.setSize(getWidth(), widgetHeight);
		height += widgetHeight;
	}

	public void setSize(int width, int height) {
		super.setSize(width, this.height);
	}
}
