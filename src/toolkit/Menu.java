package cymple.toolkit;
import cymple.common.SelectItemData;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Menu extends Widget {
	private Vector<MenuItem> items;

	public Menu() {
		super();
		items = new Vector<MenuItem>();
	}

	public int getInternalHeight() {
		return 36 * items.size();
	}

	public void add(SelectItemData item) {
		items.add(new MenuItem(item));
		onSelect(item);
		Collections.sort(items);
	}

	public void add(SelectItemData[] items) {
		for (SelectItemData item: items) {
			add(item);
		}
	}

	public void remove(SelectItemData item) {
		onUnselect(item);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(item)) {
				items.remove(i);
				break;
			}
		}
	}

	public void remove(SelectItemData[] items) {
		for (SelectItemData item: items) {
			remove(item);
		}
	}

	public void onClick(Event e) {
		MenuItem item = items.get(e.getY() / 36);
		item.toggle();
		if (item.isSelected()) onSelect(item.getItem());
		else onUnselect(item.getItem());
	}

	public void draw(Canvas canvas) {
		MenuItem item;
		canvas.noStroke();
		String[] split;
		String bold, normal;
		for (int i = 0; i < items.size(); i++) {
			item = items.get(i);
			split = item.getItem().toString().split(",");
			bold = split[0];
			normal = split.length > 1 ? split[1] : "";
			canvas.fill(item.isSelected() ? 0xff84fa1e : 0xff000000);
			canvas.rect(0, 36 * i, getWidth(), 36);
			canvas.fill(item.isSelected() ? 0xFF000000 : 0xFFFFFFFF);
			canvas.textFont(boldFont());
			canvas.text(bold, 5, 1 + 36 * i + (split.length > 1 ? 0 : 9), getWidth() - 10, 36);
			canvas.textFont(defaultFont());
			canvas.text(normal, 15, 1 + 36 * i + 18, getWidth() - 10, 36);
		}
	}

    public void onSelect(SelectItemData item) {}
	public void onUnselect(SelectItemData item) {}
}

class MenuItem implements Comparable<MenuItem> {
	private SelectItemData item;
	private boolean selected;

	public MenuItem(SelectItemData item) {
		this.item = item;
		this.selected = true;
	}

	public SelectItemData getItem() {
		return item;
	}

	public void toggle() {
		selected = !selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public int compareTo(MenuItem other) {
		return item.compareTo(other.getItem());
	}

	public boolean equals(MenuItem other) {
		return compareTo(other) == 0;
	}

	public boolean equals(SelectItemData other) {
		return item == other;
	}

	public int hashCode() {
		return item.toString().hashCode();
	}
}
