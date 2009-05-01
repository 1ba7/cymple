package cymple.toolkit;
import processing.core.PGraphics;

public abstract class Widget {
	private Application app;
	private Container parent;
	private int x;
	private int y;
	private int width;
	private int height;

	public Widget() {
		this.app = null;
		this.parent = null;
	}

	protected void setParent(Container parent) {
		this.parent = parent;
		this.app = parent.getApp();
	}

	protected void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void update() {
		app.mouse().sendEvents(this);
	}

	public void draw(Canvas canvas) {}

	public boolean visible() {
		return parent.isChildVisible(this);
	}

	public int getAbsoluteX() {
		return x + parent.getAbsoluteX();
	}

	public int getAbsoluteY() {
		return y + parent.getAbsoluteY();
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
		return visible() &&
			x >= getAbsoluteX() && x < (getAbsoluteX() + width) &&
			y >= getAbsoluteY() && y < (getAbsoluteY() + height) &&
			parent.contains(x, y);
	}

	public Application getApp() {
		return app;
	}

	public PFont defaultFont() {
		return app.defaultFont();
	}

	public PFont boldFont() {
		return app.boldFont();
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
