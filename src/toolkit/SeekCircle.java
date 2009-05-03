package cymple.toolkit;
import cymple.common.Seekable;
import cymple.common.Seeker;

public class SeekCircle extends Widget implements Seeker {
	private Seekable seekable;
	private double position;
	private double resolution;

	public SeekCircle(Seekable seekable) {
		this.seekable = seekable;
		this.position = 0;
		this.resolution = 1.0 / 3;
		this.seekable.connect(this);
	}

	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.fill(0xFFF99000);
		canvas.rect(0, 0, getWidth(), getHeight());
		canvas.fill(0xFF000066);
		canvas.ellipseMode(canvas.CORNER);
		canvas.ellipse(0, 0, getWidth(), getHeight());
		canvas.fill(0x8000FF00);
		canvas.arc(0, 0, getWidth(), getHeight(),
			(float)(canvas.TWO_PI * position * (1 - resolution) - canvas.PI / 2),
			(float)(canvas.TWO_PI * (position * (1 - resolution) + resolution) - canvas.PI / 2));
	}

	public double getPosition() {
		return position;
	}

	public double getResolution() {
		return resolution;
	}

	public void onDrag(Event e) {
		position = e.getCurrentRelativeAngle() / (2 * Math.PI);
		position -= resolution / 2;
		if (position < 0) {
			position = 0;
		}
		if (position > (1 - resolution)) {
			position = 1 - resolution;
		}
		position /= (1 - resolution);
		seekable.update();
	}

	public void onScroll(Event e) {
		resolution += (e.getScroll() * 2 * Math.PI / 360);
		if (resolution > 1.0) resolution = 1.0;
		if (resolution < (1.0 / 1024)) resolution = 1.0 / 1024;
		seekable.update();
	}
}
