package cymple.toolkit;

public class Event {
	private Widget widget;
	private int x;
	private int y;
	private int clickX;
	private int clickY;
	private int scroll;

	public Event(Widget widget, int x, int y, int clickX, int clickY, int scroll) {
		this.widget = widget;
		this.x = x;
		this.y = y;
		this.clickX = clickX;
		this.clickY = clickY;
		this.scroll = scroll;
	}

	public int getX() {
		return x - widget.getAbsoluteX();
	}

	public int getY() {
		return y - widget.getAbsoluteY();
	}

	public int getClickX() {
		return clickX - widget.getAbsoluteX();
	}

	public int getClickY() {
		return clickY - widget.getAbsoluteY();
	}

	public int getCenterX() {
		return widget.getWidth() / 2;
	}

	public int getCenterY() {
		return widget.getHeight() / 2;
	}

	public int getRelativeX() {
		return getX() - getCenterX();
	}

	public int getRelativeY() {
		return getY() - getCenterY();
	}

	public double getRelativeAngle() {
		return getAngle(getRelativeX(), getRelativeY());
	}

	public double getRelativeMagnitude() {
		return getMagnitude(getRelativeX(), getRelativeY());
	}

	public int getRelativeClickX() {
		return getClickX() - getCenterX();
	}

	public int getRelativeClickY() {
		return getClickY() - getCenterY();
	}

	public double getRelativeClickAngle() {
		return getAngle(getRelativeClickX(), getRelativeClickY());
	}

	public double getRelativeClickMagnitude() {
		return getMagnitude(getRelativeClickX(), getRelativeClickY());
	}

	public int getDragX() {
		return getClickX() - getX();
	}

	public int getDragY() {
		return getClickY() - getY();
	}

	public double getDragAngle() {
		return getAngle(getDragX(), getDragY());
	}

	public double getDragMagnitude() {
		return getMagnitude(getDragX(), getDragY());
	}

	private double getAngle(int x, int y) {
		double angle = Math.atan2(y, x) + Math.PI / 2;
		while (angle < 0) angle += Math.PI * 2;
		return angle % (Math.PI * 2);
	}

	private double getMagnitude(int x, int y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public int getScroll() {
		return scroll;
	}
}
