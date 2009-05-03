package cymple.toolkit;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Container extends Widget {
	protected Collection<Widget> children;
	
	public Container() {
		super();
		children = new ArrayList<Widget>();
	}
	
	public void add(Widget child) {
		children.add(child);
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
			canvas.translate(child.getX(), child.getY());
			child.draw(canvas);
			canvas.popMatrix();
		}
	}
}
