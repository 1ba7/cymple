package cymple.toolkit;

public class HBox extends Container {
	private int internalWidth;
	private int defaultWidth;

	public HBox() {
		this(20);
	}

	public HBox(int defaultWidth) {
		super();
		this.internalWidth = 0;
		this.defaultWidth = defaultWidth;
	}

	public void add(Widget child) {
		this.add(child, defaultWidth);
	}

	public void add(Widget child, int widgetWidth) {
		children.add(child);
		child.setParent(this);
		child.setPosition(internalWidth, 0);
		child.setSize(widgetWidth, getHeight());
		internalWidth += widgetWidth;
	}
}
