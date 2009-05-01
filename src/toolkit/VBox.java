package cymple.toolkit;

public class VBox extends Container {
	int usedTop;
	int usedBottom;

	public VBox() {
		super();
		this.usedTop = 0;
		this.usedBottom = 0;
	}

	public void addTop(Widget child, int height) {
		children.add(child);
		child.setParent(this);
		child.setX(0);
		child.setY(usedTop);
		child.setWidth(getWidth());
		child.setHeight(height);
		usedTop += height;
	}

	public void addBottom(Widget child, int height) {
		children.add(child);
		child.setParent(this);
		child.setX(0);
		child.setY(getHeight() - usedBottom - height);
		child.setWidth(getWidth());
		child.setHeight(height);
		usedBottom += height;
	}

	public void add(Widget widget) {
		this.addTop(widget, getHeight() - usedTop - usedBottom);
	}
}
