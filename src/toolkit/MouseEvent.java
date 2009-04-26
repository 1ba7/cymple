package cymple.toolkit;

public class MouseEvent {
	private Widget widget;
	private int currentX;
	private int currentY;
	private int startX;
	private int startY;

	public MouseEvent(int x, int y) {
		this.currentX = x;
		this.currentY = y;
	}

	protected void setStart(int x, int y) {
		this.startX = x;
		this.startY = y;
	}

	protected void setWidget(Widget widget) {
		this.widget = widget;
	}
}
