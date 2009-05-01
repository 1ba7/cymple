package cymple.toolkit;
import java.util.ArrayList;

public abstract class Container extends Widget {
	protected ArrayList<Widget> children;
	
	public Container() {
		super();
		children = new ArrayList<Widget>();
	}
	
	public void add(Widget child) {
		try {
			children.set(0, child);
		}
		catch (IndexOutOfBoundsException e) {
			children.add(child);
		}
		child.setParent(this);
		child.setPosition(0, 0);
		child.setSize(getWidth(), getHeight());
	}
	
	public boolean isChildVisible(Widget child) {
		return visible();
	}
	
	public void update() {
		super.update();
		for (Widget child: children) {
			child.update();
		}
	}
	
	public void draw(Canvas canvas) {
		for (Widget child: children) {
			canvas.pushMatrix();
			canvas.transform(child.getX(), child.getY());
			child.draw(canvas);
			canvas.popMatrix();
		}
	}
}
