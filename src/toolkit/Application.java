package cymple.toolkit;
import processing.core.PApplet;

public class Application extends PApplet {
	private ApplicationContainer container;
	private HBox hBox;
	private int width;
	private int height;

	public Application(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Application() {
		this(700, 500);
	}

	public void setup() {
		size(width, height);
		container = new ApplicationContainer(this);
		hBox = new HBox();
		container.add(hBox);
		hBox.add(new Widget() {
			int color = 0xFFFF0000;

			public void draw() {
				app().fill(color);
				rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			}

			public void onClick(int x, int y) {
				color = ((int)(Math.random() * (1 << 24))) | 0xFF000000;
			}
		}, 200);
		hBox.add(new Widget() {
			int color = 0xFFFF8000;

			public void draw() {
				app().fill(color);
				rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			}

			public void onClick(int x, int y) {
				color = ((int)(Math.random() * (1 << 24))) | 0xFF000000;
			}
		}, 300);
		hBox.add(new Widget() {
			int color = 0xFFFF8000;

			public void draw() {
				app().fill(color);
				rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			}

			public void mouseDown(int x, int y) {
				color = ((int)(Math.random() * (1 << 24))) | 0xFF000000;
			}
		});
	}

	public void draw() {
		container.update();
		container.mouseOn(mouseX, mouseY);
		if (mousePressed == true) {
			container.mouseDown(mouseX, mouseY);
		}
		else {
			container.mouseUp(mouseX, mouseY);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static void main(String[] args) {
		PApplet.main(new String[] {"--bgcolor=#FFFFFF", args[0]});
	}
}
