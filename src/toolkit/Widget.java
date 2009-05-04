package cymple.toolkit;
import processing.core.PFont;
import processing.core.PGraphics;

public abstract class Widget {
	protected Application app;
	protected Mouse mouse;
	protected Container parent;
	private int x;
	private int y;
	private int width;
	private int height;
	private int[] mask;

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
	/*	return parent.isChildVisible(this)
			&& getAbsoluteX() + getInternalWidth() >= getVisibleX()
			&& getAbsoluteX() + getInternalWidth() < getVisibleX() + getWidth()
			&& getAbsoluteY() + getInternalHeight() >= getVisibleY()
			&& getAbsoluteY() + getInternalHeight() < getVisibleY() + getHeight();*/
		return true;
	}

	public int getAbsoluteX() {
		return x + parent.getAbsoluteX() - parent.xOffset();
	}

	public int getAbsoluteY() {
		return y + parent.getAbsoluteY() - parent.yOffset();
	}

	public int getVisibleX() {
		return x + parent.getVisibleX();
	}

	public int getVisibleY() {
		return y + parent.getVisibleY();
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

	public int getInternalWidth() {
		return getWidth();
	}

	public int getInternalHeight() {
		return getHeight();
	}

	public boolean contains(int x, int y) {
		return visible()
			&& x >= getAbsoluteX() && x < (getAbsoluteX() + getInternalWidth())
			&& y >= getAbsoluteY() && y < (getAbsoluteY() + getInternalHeight())
			&& x >= getVisibleX() && x < (getVisibleX() + getWidth())
			&& y >= getVisibleY() && y < (getVisibleY() + getHeight());
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

	public int[] getMask(Canvas canvas) {
		if (mask == null) {
			mask = new int[canvas.getWidth() * canvas.getHeight()];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					mask[canvas.getWidth() * (getVisibleY() + i) + getVisibleX()
						 + j] = 1;
				}
			}
		}
		return mask;
	}

	public void onMouseOver(Event e) {}
	public void onMouseOut(Event e) {}
	public void onMouseDown(Event e) {}
	public void onMouseUp(Event e) {}
	public void onClick(Event e) {}
	public void onDrag(Event e) {}
	public void onScroll(Event e) {}
}
