package cymple.toolkit;

public class Mouse {
	private ApplicationContainer container;
	private boolean mouseDown;
	private boolean oldMouseDown;
	private int clickX;
	private int clickY;
	private int x;
	private int y;
	private int oldX;
	private int oldY;
	private int scroll;

	protected Mouse(ApplicationContainer container) {
		this.container = container;
		this.mouseDown = false;
		this.oldMouseDown = false;
		this.clickX = 0;
		this.clickY = 0;
		this.x = 0;
		this.y = 0;
		this.oldX = 0;
		this.oldY = 0;
		this.scroll = 0;
	}

	private void updatePosition(java.awt.event.MouseEvent e) {
		scroll = 0;
		oldX = x;
		oldY = y;
		x = e.getX();
		y = e.getY();
		oldMouseDown = mouseDown;
	}

	protected synchronized void mousePressed(java.awt.event.MouseEvent e) {
		updatePosition(e);
		mouseDown = true;
		clickX = x;
		clickY = y;
		container.update();
	}

	protected synchronized void mouseReleased(java.awt.event.MouseEvent e) {
		updatePosition(e);
		mouseDown = false;
		container.update();
	}

	protected synchronized void mouseMoved(java.awt.event.MouseEvent e) {
		updatePosition(e);
		container.update();
	}

	protected synchronized void mouseScrolled(java.awt.event.MouseWheelEvent e) {
		updatePosition(e);
		scroll = e.getWheelRotation();
		container.update();
	}

	public boolean onMouseOver(Widget widget) {
		return widget.contains(x, y) && !widget.contains(oldX, oldY);
	}

	public boolean onMouseOut(Widget widget) {
		return !widget.contains(x, y) && widget.contains(oldX, oldY);
	}

	public boolean onMouseDown(Widget widget) {
		return mouseDown && !oldMouseDown && widget.contains(x, y);
	}

	public boolean onMouseUp(Widget widget) {
		return !mouseDown && oldMouseDown && widget.contains(x, y);
	}

	public boolean onClick(Widget widget) {
		return !mouseDown && oldMouseDown && widget.contains(clickX, clickY);
	}

	public boolean onDrag(Widget widget) {
		return mouseDown && widget.contains(clickX, clickY);
	}

	public boolean onScroll(Widget widget) {
		return scroll != 0 && widget.contains(x, y);
	}

	public boolean over(Widget widget) {
		return widget.contains(x, y);
	}

	public boolean down(Widget widget) {
		return widget.contains(x, y) && mouseDown;
	}

	public boolean activity(Widget widget) {
		return onMouseOver(widget) || onMouseOut(widget) || onMouseDown(widget)
			|| onMouseUp(widget) || onScroll(widget) || onClick(widget)
			|| onDrag(widget);
	}

	public synchronized void sendEvents(Widget widget) {
		Event e = new Event(widget, x, y, clickX, clickY, scroll);
		if (onMouseOver(widget)) widget.onMouseOver(e);
		if (onMouseOut(widget)) widget.onMouseOut(e);
		if (onMouseDown(widget)) widget.onMouseDown(e);
		if (onMouseUp(widget)) widget.onMouseUp(e);
		if (onClick(widget)) widget.onClick(e);
		if (onDrag(widget)) widget.onDrag(e);
		if (onScroll(widget)) widget.onScroll(e);
	}
}
