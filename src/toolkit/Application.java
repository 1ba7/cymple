package cymple.toolkit;
import processing.core.PApplet;
import processing.core.PFont;
import java.awt.event.MouseWheelListener;

public class Application extends PApplet implements Canvas, MouseWheelListener {
	protected ApplicationContainer container;
	private PFont defaultFont;
	private PFont boldFont;
	private Mouse mouse;
	private int width;
	private int height;

	public Application(int width, int height) {
		addMouseWheelListener(this);
		this.width = width;
		this.height = height;
		this.defaultFont = loadFont("default.vlw");
		this.boldFont = loadFont("bold.vlw");
		this.container = new ApplicationContainer(this);
		this.mouse = new Mouse(container);
		container.mouse = mouse;
	}

	public void setup() {
		frameRate(20);
		size(width, height);
	}

	public void draw() {
		container.draw(this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public PFont defaultFont() {
		return defaultFont;
	}

	public PFont boldFont() {
		return boldFont;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void mousePressed(java.awt.event.MouseEvent e) {
		super.mousePressed(e);
		mouse.mousePressed(e);
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
		super.mouseReleased(e);
		mouse.mouseReleased(e);
	}

	public void mouseMoved(java.awt.event.MouseEvent e) {
		super.mouseMoved(e);
		mouse.mouseMoved(e);
	}

	public void mouseDragged(java.awt.event.MouseEvent e) {
		super.mouseDragged(e);
		mouse.mouseMoved(e);
	}

	public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
		mouse.mouseScrolled(e);
	}

	public static void main(String[] args) {
		PApplet.main(new String[] {"--bgcolor=#FFFFFF", args[0]});
	}
}
