package cymple.toolkit;

public abstract class Widget {
	private Container parent;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean highlight;
	private boolean mouseOver;
	private boolean mouseDown;

	public Widget() {
		this.parent = null;
	}

	public void update() {
		if (visible()) {
			if (highlight) {
				highlightedDraw();
			}
			else {
				draw();
			}
		}
	}

	abstract void draw();

	public boolean visible() {
		return parent.isChildVisible(this);
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected void setY(int y) {
		this.y = y;
	}

	protected void setParent(Container parent) {
		this.parent = parent;
	}

	protected void setWidth(int width) {
		this.width = width;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean contains(int x, int y) {
		return (x > this.x && x < (this.x + width)) && (y > this.y && y < (this.y + height));
	}

	public Application app() {
		return parent.app();
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public boolean isMouseDown() {
		return mouseDown;
	}

	protected void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	protected void setMouseDown(boolean mouseDown) {
		this.mouseDown = mouseDown;
	}

	public void onMouseOver(MouseEvent e) {}
	public void onMouseOff(MouseEvent e) {}
	public void onMouseDown(MouseEvent e) {}
	public void onMouseUp(MouseEvent e) {}
	public void onDrag(MouseEvent e) {}
	public void onClick(MouseEvent e) {}
	public void onScrollUp(MouseEvent e) {}
	public void onScrollDown(MouseEvent e) {}
}
