package cymple.toolkit;

public class TabWrapper extends VBox {
	private HBox tabs;
	private TabContainer container;

	public void setup() {
		tabs = new HBox();
		container = new TabContainer();
		this.add(tabs, 30);
		this.add(container, getHeight() - 30);
	}

	public void add(Widget widget, String title, int width) {
		if (tabs == null || container == null) {
			setup();
		}
		tabs.add(new Tab(container, widget, title), width);
		container.add(widget);
	}

	public void add(Widget widget, String title) {
		this.add(widget, title, 65);
	}

	// Horrible hack to get around the fact that mask doesn't work
	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.pushMatrix();
		canvas.translate(container.getX(), container.getY());
		container.draw(canvas);
		canvas.popMatrix();
		canvas.pushMatrix();
		canvas.translate(tabs.getX(), tabs.getY());
		canvas.fill(0xffcfcfcf);
		canvas.rect(0, 0, tabs.getWidth(), tabs.getHeight());
		tabs.draw(canvas);
		canvas.popMatrix();
	}

	public void show(Widget widget) {
		container.show(widget);
	}

	public void add(Widget widget) {
		this.add(widget, "Untitled");
	}
}
