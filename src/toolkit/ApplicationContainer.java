package cymple.toolkit;

public class ApplicationContainer extends Container {
	public ApplicationContainer(Application app) {
		super();
		this.app = app;
		setPosition(0, 0);
		setSize(app.getWidth(), app.getHeight());
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public boolean visible() {
		return true;
	}

	public boolean contains(int x, int y) {
		return true;
	}
}
