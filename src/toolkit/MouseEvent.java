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
		return getAngle(getRelativeX(), getRelativeY());
	}

	public double getRelativeMagnitude() {
		return getMagnitude(getRelativeX(), getRelativeY());
	}

	public int getCurrentX() {
		return widget.getApp().mouseX;
	}

	public int getCurrentY() {
		return widget.getApp().mouseY;
	}

	public int getCurrentRelativeX() {
		return getCurrentX() - getCenterX();
	}

	public int getCurrentRelativeY() {
		return getCurrentY() - getCenterY();
	}

	public double getCurrentRelativeAngle() {
		return getAngle(getCurrentRelativeX(), getCurrentRelativeY());
	}

	public double getCurrentRelativeMagnitude() {
		return getMagnitude(getCurrentRelativeX(), getCurrentRelativeY());
	}

	public int getDragX() {
		return x - getCurrentX();
	}

	public int getDragY() {
		return y - getCurrentY();
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

	public int getWheelRotation() {
		return wheelRotation;
	}
}
