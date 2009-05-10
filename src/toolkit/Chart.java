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
		return chartData == null ? getHeight() : 36 * chartData.size(key);
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
			canvas.fill(0xFFcfcfcf);
			canvas.rect(0, 0, getInternalWidth(), getInternalHeight());
			String[] split;
			String bold, normal;
			long absolute;
			double relative;
			int half = getWidth() / 2;
			for (int i = 0; i < chartData.size(key); i++) {
				split = chartData.getName(key, i).split(",");
				bold = split[0];
				normal = split.length > 1 ? split[1] : "";
				relative = chartData.getRelative(key, i);
				absolute = chartData.getAbsolute(key, i);
				canvas.fill(0xff84fa1e);
				canvas.rect(0, 36 * i, half, 36);
				canvas.rect(half, 36 * i, (int)(half * relative), 36);
				if (i % 2 == 1) {
					canvas.fill(0x33000000);
					canvas.rect(0, 36 * i, half, 36);
					canvas.rect(half, 36 * i, (int)(half * relative), 36);
				}
				canvas.fill(0xFF000000);
				canvas.textFont(boldFont());
				canvas.text(bold, 5, 1 + 36 * i + (split.length > 1 ? 0 : 9), half - 20, 36);
				canvas.textFont(defaultFont());
				canvas.text(normal, 15, 1 + 36 * i + 18, half - 20, 36);
				canvas.textFont(boldFont());
				canvas.textAlign(canvas.RIGHT);
				canvas.text(chartable.getScale().number(absolute),
					(int)(half + half * relative) - 5, 36 * i + 23);
				canvas.textAlign(canvas.LEFT);
			}
		}
	}
}
