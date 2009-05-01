package cymple.toolkit;

public class VScrollableContainer extends VBox {
	private int yOffset;
	private int defaultSize;

	public VScrollableContainer(int defaultSize) {
		super();
		this.yOffset = 0;
		this.defaultSize = defaultSize;
	}
	
	public void add(Widget child) {
		try {
			children.set(0, child);
		}
		catch (IndexOutOfBoundsException e) {
			children.add(child);
		}
		child.setParent(this);
		child.setX(0);
		child.setY(0);
		child.setWidth(getWidth());
		child.setHeight(getHeight());
	}

	public void addTop(Widget child, int height) {
		
	}

	public void addBottom(Widget child, int height) {
		
	}

	public int getInternalHeight() {
		int result = 0;
		for (Widget child: children) {
			result += child.getHeight();
		}
		return result;
	}

	public double getResolution() {
		double result = (double)(getHeight()) / getInternalHeight();
		if (result > 1) result = 1;
		return result;
	}

	public double getPosition() {
		return ((double)yOffset / getHeight()) / (1 - getResolution());
	}

	public int getY() {
		return super.getY() - yOffset;
	}
}
