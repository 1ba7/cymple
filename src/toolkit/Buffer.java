package cymple.toolkit;
import processing.core.PGraphics3D;

public class Buffer extends PGraphics3D implements Canvas {
	private int[] mask;

	public Buffer(Application application, int width, int height) {
		setParent(application);
		setSize(width, height);
	}

	public Buffer(Application application) {
		this(application, application.getWidth(), application.getHeight());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void unmask() {
		if (mask == null) {
			mask = new int[width * height];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					mask[width * i + j] = 1;
				}
			}
		}
	}
}
