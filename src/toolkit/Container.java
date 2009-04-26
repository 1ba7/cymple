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
		child.setX(getX());
		child.setY(getY());
		child.setWidth(getWidth());
		child.setHeight(getHeight());
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
	
	public void draw() {}
	
	public void mouseOn(int x, int y) {
		for (Widget child: children) {
			if (child.contains(x, y)) {
				child.mouseOn(x, y);
			}
			else {
				child.mouseOff(x, y);
			}
		}
	}
	
	public void mouseOff(int x, int y) {
		for (Widget child: children) {
			child.mouseOff(x, y);
		}
	}

	public void mouseDown(int x, int y) {
		for (Widget child: children) {
			if (child.contains(x, y)) {
				child.mouseDown(x, y);
			}
			else {
				child.mouseUp(x, y);
			}
		}
	}
	
	public void mouseUp(int x, int y) {
		for (Widget child: children) {
			child.mouseUp(x, y);
		}
	}
	
	public void onScrollUp(int x, int y) {
		for (Widget child: children) {
			if (child.contains(x, y)) {
				child.onScrollUp(x, y);
			}
		}
	}
	
	public void onScrollDown(int x, int y) {
		for (Widget child: children) {
			if (child.contains(x, y)) {
				child.onScrollDown(x, y);
			}
		}
	}
}
