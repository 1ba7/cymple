package cymple.toolkit;

public class ApplicationContainer extends Container {
	private Application app;

	public ApplicationContainer(Application app) {
		super();
		this.app = app;
		setX(0);
		setY(0);
		setWidth(app.getWidth());
		setHeight(app.getHeight());
	}

	public boolean visible() {
		return true;
	}

	public Application app() {
		return app;
	}
}
