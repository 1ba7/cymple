package cymple.toolkit;
import processing.core.PImage;

public class VScrollableContainer extends Container {
	private Widget child;
	volatile private double position;

	public VScrollableContainer() {
		super();
	}

	public void add(Widget child) {
		super.add(child);
		this.child = child;
	}

	public void onScroll(Event e) {
		setPosition(position + e.getScroll() / 50.0);
	}

	public void draw(Canvas canvas) {
		canvas.mask(child.getMask(canvas));
		canvas.pushMatrix();
		canvas.translate(0, -yOffset());
		child.draw(canvas);
		canvas.popMatrix();
		canvas.unmask();
	}

	public int yOffset() {
		return (int)(child.getInternalHeight() * position * (1 - getResolution()));
	}

	protected void setPosition(double position) {
		if (position < 0) position = 0;
		if (position > 1) position = 1;
		this.position = position;
	}

	protected double getPosition() {
		return position;
	}

	protected double getResolution() {
		return getHeight() / (double)child.getInternalHeight();
	}
}
