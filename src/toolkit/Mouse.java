package cymple.toolkit;

public class Mouse {
	private boolean mouseDown;
	private boolean oldMouseDown;
	private int clickX;
	private int clickY;
	private int x;
	private int y;
	private int oldX;
	private int oldY;
	private int wheelRotation;

	protected Mouse() {
		this.mouseDown = false;
		this.oldMouseDown = false;
		this.clickX = 0;
		this.clickY = 0;
		this.x = 0;
		this.y = 0;
		this.oldX = 0;
		this.oldY = 0;
		this.wheelRotation = 0;
	}

	protected void update(boolean mouseDown, int x, int y, int wheelRotation) {
		if (mouseDown && !oldMouseDown) {
			this.clickX = x;
			this.clickY = y;
		}
		this.oldMouseDown = this.mouseDown;
		this.mouseDown = mouseDown;
		this.oldX = this.x;
		this.oldY = this.y;
		this.x = x;
		this.y = y;
		this.wheelRotation = wheelRotation;
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

	public boolean onScrollUp(Widget widget) {
		return wheelRotation < 0 && widget.contains(x, y);
	}

	public boolean onScrollDown(Widget widget) {
		return wheelRotation > 0 && widget.contains(x, y);
	}

	public boolean isMouseOver(Widget widget) {
		return widget.contains(x, y);
	}

	public boolean isMouseDown(Widget widget) {
		return widget.contains(x, y) && mouseDown;
	}

	public boolean nonClickEvents(Widget widget) {
		return onMouseOver(widget) || onMouseOut(widget) || onMouseDown(widget)
			|| onMouseUp(widget) || onScrollUp(widget) || onScrollDown(widget);
	}

	public boolean clickEvents(Widget widget) {
		return onClick(widget) || onDrag(widget);
	}

	public boolean activity(Widget widget) {
		return nonClickEvents(widget) || clickEvents(widget);
	}

	public void sendEvents(Widget widget) {
		if (nonClickEvents(widget)) {
			MouseEvent e = new MouseEvent(widget, x, y, Math.abs(wheelRotation));
			if (onMouseOver(widget)) {widget.onMouseOver(e);}
			if (onMouseOut(widget)) {widget.onMouseOut(e);}
			if (onMouseDown(widget)) {widget.onMouseDown(e);}
			if (onMouseUp(widget)) {widget.onMouseUp(e);}
			if (onScrollDown(widget)) {widget.onScrollDown(e);}
			if (onScrollUp(widget)) {widget.onScrollUp(e);}
		}
		if (clickEvents(widget)) {
			MouseEvent e = new MouseEvent(widget, clickX, clickY, Math.abs(wheelRotation));
			if (onClick(widget)) {widget.onClick(e);}
			if (onDrag(widget)) {widget.onDrag(e);}
		}
	}
}
