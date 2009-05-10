package cymple.toolkit;
import cymple.common.Seekable;

public class SeekCircleWrapper extends Container {
	private Seekable seekable;
	private SeekCircle seeker;

	public SeekCircleWrapper(Seekable seekable) {
		this.seekable = seekable;
		this.seeker = new SeekCircle(seekable);
	}

	public void setup() {
		super.add(seeker);
		seeker.setParent(this);
		seeker.setPosition(40, 10);
		seeker.setSize(getWidth() - 90, getHeight() - 80);
	}

	public void draw(Canvas canvas) {
		if (children.size() == 0) {
			setup();
		}
		canvas.noStroke();
		canvas.fill(0xffcfcfcf);
		canvas.rect(0, 0, getWidth(), getHeight());
		super.draw(canvas);
		canvas.pushMatrix();
		canvas.translate(0, getHeight() - 50);
		canvas.noStroke();
		canvas.fill(0xff000000);
		canvas.textFont(boldFont());
		canvas.textAlign(canvas.LEFT);
		canvas.text(seekable.getScale().startString(
			seeker.getPosition(), seeker.getResolution()), 15, 18);
		canvas.textAlign(canvas.RIGHT);
		canvas.text(seekable.getScale().finishString(
			seeker.getPosition(), seeker.getResolution()), getWidth() - 15, 18);
		canvas.textAlign(canvas.CENTER);
		canvas.text("Showing " + (int)(seeker.getResolution() * 100) + "%",
			getWidth() / 2, 36);
		canvas.textAlign(canvas.LEFT);
		canvas.popMatrix();
	}
}
