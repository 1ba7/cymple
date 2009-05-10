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
		canvas.fill(0xff000000);
		canvas.ellipseMode(canvas.CORNER);
		canvas.ellipse(0, 0, getWidth(), getHeight());
		canvas.fill(0xff84fa1e);
		canvas.arc(0, 0, getWidth(), getHeight(),
			(float)(canvas.TWO_PI * position * (1 - resolution) - canvas.PI / 2),
			(float)(canvas.TWO_PI * (position * (1 - resolution) + resolution) - canvas.PI / 2));
		canvas.stroke(0xff000000);
		canvas.strokeWeight(1);
		canvas.noFill();
		canvas.ellipseMode(canvas.CORNER);
		canvas.ellipse(0, 0, getWidth(), getHeight());
		canvas.noStroke();
	}

	public double getPosition() {
		return position;
	}

	public double getResolution() {
		return resolution;
	}

	public void onDrag(Event e) {
		position = e.getRelativeAngle() / (2 * Math.PI);
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
		resolution -= e.getScroll() / 32.0;
		resolution = (double)Math.round(resolution * 32) / 32;
		if (resolution > 1.0) resolution = 1.0;
		if (resolution < 1.0 / 32) resolution = 1.0 / 32;
		seekable.update();
	}
}
