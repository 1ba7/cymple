package cymple.toolkit;

public class VScrollableWrapper extends HBox {
	private VScrollableContainer container;
	private VScrollbar scrollbar;

	public void add(Widget child) {
		container = new VScrollableContainer();
		super.add(container, getWidth() - 20);
		container.add(child);
		scrollbar = new VScrollbar(container);
		super.add(scrollbar, 20);
	}
}
