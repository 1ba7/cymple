import cymple.data.*;
import cymple.toolkit.*;
import cymple.common.*;

class TestWidget extends Widget {
	public void draw() {
		app.noStroke();
		app.fill(0xFFFFFFFF);
		app.rect(getX(), getY(), getWidth(), getHeight());
	}
}

public class Cymple extends Application {
	private Data data;

	public Cymple() {
		super(800, 600);
	}

	public void setup() {
		super.setup();
		final Data data = new Data("../tmp/cymple.bin");
		frameRate(30);
		VBox vbox = new VBox();
		Statusbar statusbar = new Statusbar(data);
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		container.add(vbox);
		vbox.addBottom(statusbar, 20);
		vbox.addTop(hbox1, 200);
		vbox.addBottom(hbox3, 200);
		vbox.add(hbox2);
		hbox1.addLeft(new Seeker(data), 200);
		hbox2.addLeft(new TestWidget() {
			public void onClick(MouseEvent e) {
				switch(data.getChartKey().ordinal()) {
					case 0:
						data.setChartKey(ChartKey.Artist);
						break;
					case 1:
						data.setChartKey(ChartKey.Album);
						break;
					case 2:
						data.setChartKey(ChartKey.Track);
						break;
					case 3:
						data.setChartKey(ChartKey.User);
						break;
				}
			}
		}, 200);
		hbox3.addLeft(new TestWidget(), 200);
		hbox1.addRight(new TestWidget(), 200);
		hbox2.addRight(new TestWidget(), 200);
		hbox3.addRight(new TestWidget(), 200);
		hbox1.add(new TestWidget());
		hbox2.add(new TestWidget() {
			public void onClick(MouseEvent e) {
				data.update();
			}

			public void draw() {
				super.draw();
				ChartData cd = data.getChartData();
				for (int i = 0; i < 12; i++) {
					app.fill(0x80000099 + (i % 2 == 0 ? 0 : 0x333333));
					app.rect(getX(), getY() + (18 * i) - 14, (float)(getWidth() * cd.getRelative(i)), 18);
					app.fill(0xFF000000);
					app.textFont(app.defaultFont());
					app.text(cd.getName(i) + ": " + cd.getAbsolute(i), getX(), getY() + 18 * i);
				}
			}
		});
		hbox3.add(new TestWidget());
	}

	public static void main(String args[]) {
		Application.main(new String[] {"Cymple"});
	}
}
