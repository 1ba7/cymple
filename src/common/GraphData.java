package cymple.common;

public class GraphData {
	private double[] samples;
	private long maximum = 0;

	public GraphData(ListenVector listens, double resolution) {
		// Units per samples
		int ups = (int)(32 * resolution < 1 ? 1 : 32 * resolution);
		samples = new double[1024 / ups];
		long absolute;
		for (int i = 0; i < samples.length;) {
			absolute = listens.between(i++ * ups, i * ups);
			maximum = absolute > maximum ? absolute : maximum;
		}
		for (int i = 0; i < samples.length;) {
			samples[i] = listens.between(i++ * ups, i * ups) / (double)maximum;
		}
	}

	public double sample(int n) {
		return samples[n];
	}

	public long maximum() {
		return maximum;
	}

	public int size() {
		return samples.length;
	}
}
