package cymple.toolkit;
import processing.core.PApplet;
import processing.core.PFont;

public class Application extends PApplet {
	protected ApplicationContainer container;
	private int width;
	private int height;
	private int wheelRotation;
	private EventDispatcher dispatcher;
	private PFont font;

	public Application(int width, int height) {
		this.width = width;
		this.height = height;
		this.container = new ApplicationContainer(this);
		this.wheelRotation = 0;
		this.dispatcher = new EventDispatcher();
		this.font = loadFont("default.vlw");
	}

	public Application() {
		this(700, 500);
	}

	public void setup() {
		addMouseWheelListener(new java.awt.event.MouseWheelListener() { 
			public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) { 
				wheelRotation += e.getWheelRotation();
			}
		});
		size(width, height);
	}

	public void draw() {
		dispatcher.update(mousePressed, mouseX, mouseY, wheelRotation);
		container.update();
		wheelRotation = 0;
	}

	public EventDispatcher mouse() {
		return dispatcher;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public PFont defaultFont() {
		return font;
	}

	public static void main(String[] args) {
		PApplet.main(new String[] {"--bgcolor=#FFFFFF", args[0]});
	}
}
