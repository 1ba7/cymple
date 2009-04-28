package cymple.toolkit;
import cymple.common.StatusSource;

public class Statusbar extends Widget {
	private StatusSource source;

	public Statusbar(StatusSource source) {
		this.source = source;
	}

	public void draw() {
		app.noStroke();
		app.fill(0xFFEFEBE7);
		app.rect(getX(), getY(), getWidth(), getHeight());
		app.fill(0x400099FF);
		app.rect(getX(), getY(), (int)(source.getComplete() * getWidth()), getHeight());
		app.textFont(app.defaultFont());
		app.fill(0xFF000000);
		app.text(source.getStatus(), getX() + 15, getY() + 15);
	}
}
