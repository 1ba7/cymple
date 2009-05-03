package cymple.toolkit;

public class HBox extends Container {
	private int width;
	private int defaultWidth;

	public HBox() {
		this(20);
	}

	public HBox(int defaultWidth) {
		super();
		this.width = 0;
		this.defaultWidth = defaultWidth;
	}

	public void add(Widget child) {
		this.add(child, defaultWidth);
	}

	public void add(Widget child, int widgetWidth) {
		children.add(child);
		child.setParent(this);
		child.setPosition(width, 0);
		child.setSize(widgetWidth, getHeight());
		width += widgetWidth;
	}

	public void setSize(int width, int height) {
		super.setSize(this.width, height);
	}
}
