package cymple.toolkit;

public class HBox extends Container {
	int usedLeft;
	int usedRight;

	public HBox() {
		super();
		this.usedLeft = 0;
		this.usedRight = 0;
	}

	public void addLeft(Widget child, int width) {
		children.add(child);
		child.setParent(this);
		child.setX(usedLeft);
		child.setY(0);
		child.setWidth(width);
		child.setHeight(getHeight());
		usedLeft += width;
	}

	public void addRight(Widget child, int width) {
		children.add(child);
		child.setParent(this);
		child.setX(getWidth() - usedRight - width);
		child.setY(0);
		child.setWidth(width);
		child.setHeight(getHeight());
		usedRight += width;
	}

	public void add(Widget widget) {
		this.addLeft(widget, getWidth() - usedLeft - usedRight);
	}
}
