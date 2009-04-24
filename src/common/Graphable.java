package cymple.common;

// This interface is to be implemented by data structures representing
// graphable data.
public interface Graphable extends Seekable {
	// Sets the number of samples taken to a GraphData.
	public void setSampleRate(int samples);
	// Returns a GraphData object for the current dataset.
	public GraphData getGraphData();
}
