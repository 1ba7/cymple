package cymple.common;

// This is a class inherited by GraphData and ChartData.
public class SeekableData {
	// The position at the time this data was created.
	private double position;
	// The resolution at the time this data was created.
	private double resolution;

	// Constructs a SeekalbeData with the given arguments.
	public SeekableData(double position, double resolution) {
		this.position = position;
		this.resolution = resolution;
	}

	// The resolution at the time this data was created.
	public double getResolution() {
		return resolution;
	}

	// The position at the time this data was created.
	public double getPosition() {
		return position;
	}
}
