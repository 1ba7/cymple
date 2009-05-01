package cymple.common;

public class ListenVector {
	private long[] listens;

	public ListenVector(byte[] bytes) {
		listens = new long[1024];
		for (int i = 0; i < 1024; i++) {
			listens[i] = ((bytes[i << 3] & 255L) << 56) +
			((bytes[(i << 3) + 1] & 255L) << 48) +
			((bytes[(i << 3) + 2] & 255L) << 40) +
			((bytes[(i << 3) + 3] & 255L) << 32) +
			((bytes[(i << 3) + 4] & 255L) << 24) +
			((bytes[(i << 3) + 5] & 255L) << 16) +
			((bytes[(i << 3) + 6] & 255L) << 8) + (bytes[(i << 3) + 7] & 255L);
		}
	}

	public void add(ListenVector other) {
		for (int i = 0; i < 1024; i++) {
			listens[i] += other.get(i);
		}
	}

	public void subtract(ListenVector other) {
		for (int i = 0; i < 1024; i++) {
			listens[i] -= other.get(i);
		}
	}

	public long between(int start, int finish) {
		return listens[finish > 1023 ? 1023: finish] -
			(start == 0 ? 0 : listens[start - 1]);
	}

	public long get(int n) {
		return listens[n];
	}
}
