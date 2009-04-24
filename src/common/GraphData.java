package cymple.common;

// This is a data structure which can be used directly to draw a graph.
public class GraphData extends SeekableData {
	// An array of points that describe the graph.
	private double[] samples;
	// The highest possible point in the dataset at the current resolution.
	private int maximum;
	// Initiales a GraphData object.
	public GraphData(double position, double resolution, double[] samples, int maximum) {
		super(position, resolution);
		this.samples = samples;
		this.maximum = maximum;
	}

	// The sample rate at the time this was created.
	public int getSampleRate() {
		return samples.length;
	}

	// Returns the (n - 1)th sample.
	public double getSample(int n) {
		return samples[n];
	}

	// Returns the maximum possible point in the dataset. This should be
	// displayed on the Y axis of the graph.
	public int getMaximum() {
		return maximum;
	}
}
