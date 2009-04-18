// This interface is to be implemented by data structures containing
// chronological data that can be Graphed and Charted.
public interface Visualisable {

	// This returns the data point (e.g., the number of plays in this time)
	// between the two given times.
	public int between(Time start, Time finish);

	// Returns the earliest point in the data.
	public Time minTime();

	// Returns the latest point in the data.
	public Time maxTime();

	// This is the data that is used to draw the chart, sorted by value.
	public TreeMap<String, Integer> chartData(Time start, Time finish, ChartKey key);

}
