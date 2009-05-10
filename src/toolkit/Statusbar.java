package cymple.toolkit;
import cymple.common.Status;

public class Statusbar extends Widget {
	private Status source;

	public Statusbar(Status source) {
		this.source = source;
	}

	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.fill(0xFFcfcfcf);
		canvas.rect(0, 0, getWidth(), getHeight());
		canvas.fill(0xFF84fa1e);
		canvas.rect(0, 0, (int)(source.getComplete() * getWidth()), getHeight());
		canvas.textFont(defaultFont());
		canvas.fill(0xFF000000);
		canvas.text(source.getStatus(), 15, 16);
		canvas.stroke(0xFF000000);
		canvas.strokeWeight(1);
		canvas.line(0, 0, getWidth(), 0);
		canvas.noStroke();
	}
}
