package cymple.toolkit;
import cymple.common.Status;

public class Statusbar extends Widget {
	private Status source;

	public Statusbar(Status source) {
		this.source = source;
	}

	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.fill(0xFFEFEBE7);
		canvas.rect(0, 0, getWidth(), getHeight());
		canvas.fill(0x400099FF);
		canvas.rect(0, 0, (int)(source.getComplete() * getWidth()), getHeight());
		canvas.textFont(defaultFont());
		canvas.fill(0xFF000000);
		canvas.text(source.getStatus(), 15, 15);
	}
}
