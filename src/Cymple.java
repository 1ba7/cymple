import cymple.data.*;
import cymple.toolkit.*;
import cymple.common.*;

public class Cymple extends Application {
	final private Chart chart;
	final private Data data;

	public Cymple() {
		this("../tmp/cymple.bin");
	}

	public Cymple(String path) {
		super(800, 600);
		this.data = new Data(path);
		this.chart = new Chart(data) {
			public void onClick(Event e) {
				switch(this.getKey().ordinal()) {
					case 0:
						this.setKey(ChartKey.Artist);
						break;
					case 1:
						this.setKey(ChartKey.Album);
						break;
					case 2:
						this.setKey(ChartKey.Track);
						break;
					case 3:
						this.setKey(ChartKey.User);
						break;
				}
			}
		};
	}

	public void setup() {
		super.setup();
		VBox vbox = new VBox();
		Statusbar statusbar = new Statusbar(data);
		HBox hbox = new HBox();
		container.add(vbox);
		vbox.add(hbox, 580);
		vbox.add(statusbar, 20);
		hbox.add(new SeekCircle(data), 200);
		VScrollableWrapper vsw = new VScrollableWrapper();
		hbox.add(vsw, 600);
		vsw.add(chart);
	}

	public static void main(String args[]) {
		Application.main(new String[] {"Cymple"});
	}
}
