package cymple.toolkit;

public class ApplicationContainer extends Container {
	public ApplicationContainer(Application app) {
		super();
		this.app = app;
		setPosition(0, 0);
		setSize(app.getWidth(), app.getHeight());
	}

	public int getAbsoluteX() {
		return 0;
	}

	public int getAbsoluteY() {
		return 0;
	}

	public int getVisibleX() {
		return 0;
	}

	public int getVisibleY() {
		return 0;
	}

	public boolean visible() {
		return true;
	}

	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.fill(0xFFFFFFFF);
		canvas.rect(0, 0, getWidth(), getHeight());
		super.draw(canvas);
	}

	public boolean contains(int x, int y) {
		return true;
	}
}
