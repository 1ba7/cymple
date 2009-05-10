package cymple.toolkit;

public class TabContainer extends Container {
	private Widget visible;

	public void add(Widget widget) {
		super.add(widget);
		if (visible == null) visible = widget;
	}

	public void show(Widget widget) {
		visible = widget;
	}

	public boolean isChildVisible(Widget child) {
		return child == visible;
	}

	public void draw(Canvas canvas) {
		visible.draw(canvas);
	}
}
