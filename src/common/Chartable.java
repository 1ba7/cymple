package cymple.common;

// This interface is to be implemented by data structures representing
// chartable data.
public interface Chartable extends Seekable {
	// Sets the ChartKey for which the charts are calculated.
	public void setChartKey(ChartKey key);
	// Returns a ChartData object for the current dataset.
	public ChartData getChartData();
}
