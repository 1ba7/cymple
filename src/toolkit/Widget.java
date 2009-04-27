package cymple.toolkit;

public abstract class Widget {
	protected Application app;
	private Container parent;
	private int x;
	private int y;
	private int width;
	private int height;

	public Widget() {
		this.app = null;
		this.parent = null;
	}

	public void update() {
		app.mouse().sendEvents(this);
		if (visible()) {
			draw();
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
		this.app = parent.getApp();
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
		return visible() && x >= this.x && x < (this.x + width) && y >= this.y && y < (this.y + height);
	}

	public Application getApp() {
		return app;
	}

	public void onMouseOver(MouseEvent e) {}
	public void onMouseOut(MouseEvent e) {}
	public void onMouseDown(MouseEvent e) {}
	public void onMouseUp(MouseEvent e) {}
	public void onClick(MouseEvent e) {}
	public void onDrag(MouseEvent e) {}
	public void onScrollUp(MouseEvent e) {}
	public void onScrollDown(MouseEvent e) {}
}
