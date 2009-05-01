package cymple.toolkit;
import processing.core.PGraphics3D;

public class Buffer extends PGraphics3D implements Canvas {
	public Buffer(Application application) {
		setParent(application);
		setSize(application.getWidth(), application.getHeight());
	}
}
