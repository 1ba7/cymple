package cymple.toolkit;
import processing.core.PFont;
import processing.core.PGraphics;

public abstract class Widget {
	protected Application app;
	protected Mouse mouse;
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
		this.mouse = app.getMouse();
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
		mouse.sendEvents(this);
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

	public void onMouseOver(Event e) {}
	public void onMouseOut(Event e) {}
	public void onMouseDown(Event e) {}
	public void onMouseUp(Event e) {}
	public void onClick(Event e) {}
	public void onDrag(Event e) {}
	public void onScroll(Event e) {}
}
