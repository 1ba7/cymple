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
		canvas.fill(0xff000000);
		canvas.rect(0, 0, getWidth(), getHeight());
		canvas.stroke(0xffffff);
		canvas.strokeWeight(1);
		canvas.fill(0xff84fa1e);
		canvas.rect(0, y, getWidth(), height);
		canvas.noStroke();
	}
}
