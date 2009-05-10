package cymple.common;

public class GraphData {
	private double[] samples;
	private Seeker seeker;
	private long maximum = 0;

	public GraphData(ListenVector listens, Seeker seeker) {
		this.seeker = seeker;
		double position, resolution;
		synchronized(seeker) {
			position = seeker.getPosition();
			resolution = seeker.getResolution();
		}
		position *= (1 - resolution);
		int ups = (int)(resolution * 32);
		samples = new double[32];
		long absolute;
		for (int i = 0; i < samples.length;) {
			absolute = listens.between(i++ * ups, i * ups);
			maximum = absolute > maximum ? absolute : maximum;
		}
		int offset = (int)(1024 * position);
		for (int i = 0; i < samples.length;) {
			samples[i] = listens.between(offset + i++ * ups, offset + i * ups)
				/ (double)maximum;
		}
	}

	public double sample(int n) {
		return samples[n];
	}

	public long maximum() {
		return maximum;
	}

	public Seeker getSeeker() {
		return seeker;
	}

	public int size() {
		return samples.length;
	}
}
