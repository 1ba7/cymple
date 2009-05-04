package cymple.toolkit;

public class VScrollbar extends Widget {
	VScrollableContainer container;

	public VScrollbar(VScrollableContainer container) {
		this.container = container;
	}

	public void onDrag(Event e) {
		container.setPosition((double)e.getY() / getHeight());
	}

	public void onScroll(Event e) {
		container.onScroll(e);
	}

	public void draw(Canvas canvas) {
		int y;
		int height;
		if (container.yOffset() < 0) {
			y = 0;
		}
		else {
			y = (int)(getHeight() * container.getPosition() *
				(1 - container.getResolution()));
		}
		height = (int)((container.getResolution() < 0) ? getHeight() :
			getHeight() * container.getResolution());
		canvas.noStroke();
		canvas.fill(0xff000000);
		canvas.rect(0, 0, getWidth(), getHeight());
		canvas.fill(0x40ffffff);
		canvas.rect(0, y, getWidth(), height);
	}
}
