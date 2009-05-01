package cymple.toolkit;
import processing.core.PApplet;
import processing.core.PFont;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Application extends PApplet implements Canvas, MouseWheelListener {
	private ApplicationContainer container;
	private PFont defaultFont;
	private PFont boldFont;
	private Mouse mouse;
	private int width;
	private int height;
	private int wheelRotation = 0;

	public Application(int width, int height) {
		this.width = width;
		this.height = height;
		addMouseWheelListener(this);
		this.buffer = createGraphics(width, height);
		this.defaultFont = loadFont("default.vlw");
		this.boldFont = loadFont("bold.vlw");
		this.mouse = new Mouse();
		this.container = new ApplicationContainer(this);
	}

	public void setup() {
		size(width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw() {
		container.draw(this);
	}

	public void update() {
	}

	public Mouse mouse() {
		return mouse;
	}

	public static void main(String[] args) {
		PApplet.main(new String[] {"--bgcolor=#FFFFFF", args[0]});
	}
}
