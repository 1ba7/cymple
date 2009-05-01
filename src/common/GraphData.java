package cymple.common;

public class GraphData {
	private double[] samples;
	private long maximum = 0;

	public GraphData(ListenVector listens, double resolution) {
		samples = new double[(int)(32 / resolution)];
		long maybe;
		double offset = 1024 / 32 * resolution;
		for (int i = 0; i < samples.length;) {
			maybe = listens.between((int)(i++ * offset), (int)(i * offset));
			if (maybe > maximum) {
				maximum = maybe;
			}
		}
		for (int i = 0; i < samples.length;) {
			maybe = listens.between((int)(i++ * offset), (int)(i * offset));
			samples[i] = (double)maybe / maximum;
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
