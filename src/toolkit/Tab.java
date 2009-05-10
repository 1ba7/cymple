package cymple.toolkit;

public class Tab extends Widget {
	private TabContainer container;
	private Widget widget;
	private String title;

	public Tab(TabContainer container, Widget widget, String title) {
		this.container = container;
		this.widget = widget;
		this.title = title;
	}

	public boolean contains(int x, int y) {
		return super.contains(x, y) && super.contains(x + 5, y - 10);
	}

	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.fill(widget.visible() ? 0xff84fa1e : 0xff000000);
		canvas.rect(0, 10, getWidth() - 5, getHeight() - 10);
		if (mouse.over(this) && !widget.visible()) {
			canvas.fill(0x66FFFFFF);
			canvas.rect(0, 10, getWidth() - 5, getHeight() - 10);
		}
		canvas.fill(widget.visible() ? 0xFF000000 : 0xFFFFFFFF);
		canvas.textFont(boldFont());
		canvas.textAlign(canvas.CENTER);
		canvas.text(title, getWidth() / 2, getHeight() - 5);
		canvas.textAlign(canvas.LEFT);
	}

	public void onClick(Event e) {
		container.show(widget);
	}
}
