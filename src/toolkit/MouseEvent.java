package cymple.toolkit;

public class MouseEvent {
	private Widget widget;
	private int x;
	private int y;
	private int wheelRotation;

	public MouseEvent(Widget widget, int x, int y, int wheelRotation) {
		this.widget = widget;
		this.x = x;
		this.y = y;
		this.wheelRotation = wheelRotation;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCenterX() {
		return widget.getX() + widget.getWidth() / 2;
	}

	public int getCenterY() {
		return widget.getY() + widget.getHeight() / 2;
	}

	public int getRelativeX() {
		return x - getCenterX();
	}

	public int getRelativeY() {
		return y - getCenterY();
	}

	public double getRelativeAngle() {
		return Math.atan2(getRelativeX(), getRelativeY());
	}

	public double getRelativeMagnitude() {
		return Math.sqrt(Math.pow(getRelativeX(), 2) + Math.pow(getRelativeY(), 2));
	}

	public int getCurrentX() {
		return widget.getApp().mouseX;
	}

	public int getCurrentY() {
		return widget.getApp().mouseY;
	}

	public int getDragX() {
		return x - getCurrentX();
	}

	public int getDragY() {
		return y - getCurrentY();
	}

	public double getDragAngle() {
		return Math.atan2(getDragY(), getDragX());
	}

	public double getDragMagnitude() {
		return Math.sqrt(Math.pow(getDragX(), 2) + Math.pow(getDragY(), 2));
	}

	public int getWheelRotation() {
		return wheelRotation;
	}
}
