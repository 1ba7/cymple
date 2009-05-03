import cymple.data.*;
import cymple.toolkit.*;
import cymple.common.*;

class TestWidget extends Widget {
	public void draw(Canvas canvas) {
		canvas.noStroke();
		canvas.fill(0xFFFFFFFF);
		canvas.rect(0, 0, getWidth(), getHeight());
	}
}

public class Cymple extends Application {
	private ChartKey key;
	final private Data data;

	public Cymple() {
		this("../tmp/cymple.bin");
	}

	public Cymple(String path) {
		super(800, 600);
		this.data = new Data(path);
		this.key = ChartKey.Artist;
	}

	public ChartKey getKey() {
		return key;
	}

	public void setKey(ChartKey key) {
		this.key = key;
	}

	public void setup() {
		super.setup();
		VBox vbox = new VBox();
		Statusbar statusbar = new Statusbar(data);
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		container.add(vbox);
		vbox.add(hbox1, 200);
		vbox.add(hbox2, 180);
		vbox.add(hbox3, 200);
		vbox.add(statusbar, 20);
		hbox1.add(new SeekCircle(data), 200);
		hbox2.add(new TestWidget() {
			public void onClick(Event e) {
				switch(getKey().ordinal()) {
					case 0:
						setKey(ChartKey.Artist);
						break;
					case 1:
						setKey(ChartKey.Album);
						break;
					case 2:
						setKey(ChartKey.Track);
						break;
					case 3:
						setKey(ChartKey.User);
						break;
				}
			}
		}, 200);
		hbox3.add(new TestWidget(), 200);
		hbox1.add(new TestWidget(), 400);
		hbox2.add(new TestWidget() {
			public void draw(Canvas canvas) {
				super.draw(canvas);
				if (data.getChartData() == null) {
					data.update();
				}
				else {
					ChartData cd = data.getChartData();
					for (int i = 0; i < 10; i++) {
						canvas.fill(0x80000099 + (i % 2 == 0 ? 0 : 0x333333));
						canvas.rect(0, 18 * i, (float)(getWidth() * cd.getRelative(getKey(), i)), 18);
						canvas.fill(0xFF000000);
						canvas.textFont(defaultFont());
						canvas.text(cd.getName(getKey(), i) + ": " + data.getScale().number(cd.getAbsolute(getKey(), i)), 5, 18 * i + 14);
					}
				}
			}
		}, 400);
		hbox3.add(new TestWidget(), 400);
		hbox1.add(new TestWidget(), 200);
		hbox2.add(new TestWidget(), 200);
		hbox3.add(new TestWidget(), 200);
	}

	public static void main(String args[]) {
		Application.main(new String[] {"Cymple"});
	}
}
