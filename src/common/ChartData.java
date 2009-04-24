package cymple.common;

// This is a data structure which can be used directly to draw a chart.
public class ChartData extends SeekableData {
	// The chartKey for this chart.
	private ChartKey key;
	// The names of the entries in the chart.
	private String[] names;
	// The fractions of the total width of the chart that the entries will
	// take up.
	private double[] relatives;
	// Absolutes, i.e., the absolute number of plays each entry as.
	private int[] absolutes;

	// Initiales a GraphData object.
	public ChartData(double position, double resolution, ChartKey key, String[] names, int[] absolutes) {
		super(position, resolution);
		int i, maximum = 0, swapValue;
		String swapKey;

		this.key = key;
		this.names = names;
		this.absolutes = absolutes;
		relatives = new double[absolutes.length];

		for (i = 0; i < absolutes.length; i++) {
			if (absolutes[i] > maximum) {
				maximum = absolutes[i];
			}
			for (int j = i; j > 0 && absolutes[j - 1] < absolutes[j]; j--) {
				swapValue = absolutes[j - 1];
				swapKey = names[j - 1];
				absolutes[j - 1] = absolutes[j];
				names[j - 1] = names[j];
				absolutes[j] = swapValue;
				names[j] = swapKey;
			}
		}

		for (i = 0; i < absolutes.length; i++) {
			relatives[i] = ((double)absolutes[i]) / maximum;
		}
	}

	// The ChartKey for this chart.
	public ChartKey getChartKey() {
		return key;
	}

	// Returns the name of the (n - 1)th entry.
	public String getName(int n) {
		return names[n];
	}

	// Returns the relative magnitude of the (n - 1)th entry.
	public double getRelative(int n) {
		return relatives[n];
	}

	// Returns the absolute magnitude of the (n - 1)th entry.
	public int getAbsolute(int n) {
		return absolutes[n];
	}

	// Returns the number of entries in the chart.
	public int size() {
		return absolutes.length;
	}
}
