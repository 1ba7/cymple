package cymple.toolkit;
import cymple.common.Seekable;

public class Seeker extends Widget {
	private Seekable seekable;
	private double position;
	private double resolution;

	public Seeker(Seekable seekable) {
		this.seekable = seekable;
		this.position = 0;
		this.resolution = 1.0 / 3;
		this.seekable.setPosition(position);
		this.seekable.setResolution(resolution);
		this.seekable.update();
	}

	public void update() {
		super.update();
		seekable.setPosition(position);
		seekable.setResolution(resolution);
	}

	public void draw() {
		app.noStroke();
		app.fill(0xFFFFFFFF);
		app.rect(getX(), getY(), getWidth(), getHeight());
		app.fill(0xFF000066);
		app.ellipse((getX() + getWidth() / 2), (getY() + getHeight()) / 2,
			getWidth(), getHeight());
		app.fill(0x8000FF00);
		app.arc((getX() + getWidth() / 2), (getY() + getHeight()) / 2,
			getWidth(), getHeight(),
			(float)(app.TWO_PI * position * (1 - resolution) - app.PI / 2),
			(float)(app.TWO_PI * (position * (1 - resolution) + resolution) - app.PI / 2));
	}

	public void onDrag(MouseEvent e) {
		position = e.getCurrentRelativeAngle() / (2 * Math.PI);
		position -= resolution / 2;
		if (position < 0) {
			position = 0;
		}
		if (position > (1 - resolution)) {
			position = 1 - resolution;
		}
		position /= (1 - resolution);
	}

	public void onScrollDown(MouseEvent e) {
		resolution += (e.getWheelRotation() * app.TWO_PI / 360);
		if (resolution > 1.0) resolution = 1.0;
	}

	public void onScrollUp(MouseEvent e) {
		resolution -= (e.getWheelRotation() * app.TWO_PI / 360);
		if (resolution < 0.0001) resolution = 0.0001;
	}
}
