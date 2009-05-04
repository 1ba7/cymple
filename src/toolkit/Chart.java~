package cymple.toolkit;
import cymple.common.Chartable;
import cymple.common.ChartKey;
import cymple.common.ChartData;

public class Chart extends Widget {
	private ChartKey key;
	private Chartable chartable;
	private ChartData chartData;

	public Chart(Chartable chartable) {
		super();
		this.chartable = chartable;
		this.key = ChartKey.standard;
	}

	public int getInternalHeight() {
		return chartData == null ? getHeight() : 18 * chartData.size(key);
	}

	public ChartKey getKey() {
		return key;
	}

	public void setKey(ChartKey key) {
		this.key = key;
	}

	public void draw(Canvas canvas) {
		if (chartable.getChartData() == null) {
			chartable.update();
		}
		else if (chartData != chartable.getChartData()) {
			chartData = chartable.getChartData();
		}
		else {
			canvas.noStroke();
			canvas.fill(0xFFFFFFFF);
			canvas.rect(0, 0, getInternalWidth(), getInternalHeight());
			for (int i = 0; i < chartData.size(key); i++) {
				canvas.fill(0x80000099 + (i % 2 == 0 ? 0 : 0x333333));
				canvas.rect(0, 18 * i, (float)(getWidth() *
					chartData.getRelative(key, i)), 18);
				canvas.fill(0xFF000000);
				canvas.textFont(defaultFont());
				canvas.text(chartData.getName(key, i) + ": " +
					chartable.getScale().number(chartData.getAbsolute(key, i)),
					5, 18 * i + 14);
			}
		}
	}
}
