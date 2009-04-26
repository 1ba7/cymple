package cymple.toolkit;

public class HBox extends Container {
	int usedWidth;

	public HBox() {
		super();
		usedWidth = 0;
	}

	public void add(Widget child, int width) {
		children.add(child);
		child.setParent(this);
		child.setX(usedWidth);
		child.setY(getY());
		child.setWidth(width);
		child.setHeight(getHeight());
		usedWidth += width;
	}

	public void add(Widget widget) {
		this.add(widget, getWidth() - usedWidth);
	}
}
