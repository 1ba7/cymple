package cymple.common;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class ChartData {
	private Map<ChartKey, String[]> names;
	private Map<ChartKey, long[]> absolutes;
	private Map<ChartKey, double[]> relatives;

	public ChartData(Map<ChartKey, Map<String, Long>> map) {
		names = new HashMap<ChartKey, String[]>();
		absolutes = new HashMap<ChartKey, long[]>();
		relatives = new HashMap<ChartKey, double[]>();
		for (ChartKey key: map.keySet()) {
			int size = map.get(key).size();
			names.put(key, convert(map.get(key).keySet()));
			absolutes.put(key, new long[size]);
			long maximum = 0;
			long[] listensArray = convert(map.get(key).values());
			for (int i = 0; i < size; i++) {
				absolutes.get(key)[i] = listensArray[i];
				if (listensArray[i] > maximum) {
					maximum = listensArray[i];
				}
			}
			sort(absolutes.get(key), names.get(key));
			relatives.put(key, new double[size]);
			for (int i = 0; i < size; i++) {
				relatives.get(key)[i] = (double)absolutes.get(key)[i] / maximum;
			}
		}
	}

	public String getName(ChartKey key, int n) {
		return names.get(key)[size(key) - n - 1];
	}

	public double getRelative(ChartKey key, int n) {
		return relatives.get(key)[size(key) - n - 1];
	}

	public long getAbsolute(ChartKey key, int n) {
		return absolutes.get(key)[size(key) - n - 1];
	}

	public int size(ChartKey key) {
		return absolutes.get(key).length;
	}

	private long[] convert(Collection<Long> longs) {
		long[] result = new long[longs.size()];
		int i = 0;
		for (Long l: longs) {
			result[i] = (long)l;
			i++;
		}
		return result;
	}

	private String[] convert(Collection<String> strings) {
		String[] result = new String[strings.size()];
		int i = 0;
		for (String string: strings) {
			result[i] = string;
			i++;
		}
		return result;
	}

	private void sort(long absolutes[], String names[]) {
		sort(absolutes, names, 0, absolutes.length);
	}

	// Sorting stuff, taken from the source code of Arrays.sort
	// I tried to make it less obfuscated, but I don't think I succeeded much
	// The reasoning for this is that I can sort two arrays at once
	// i.e., absolutes and names (which is sorted based on absolutes)
	private void sort(long absolutes[], String names[], int offset, int length) {
		int m = offset + (length >> 1);
		if (length > 7) {
			int l = offset;
			int n = offset + length - 1;
			if (length > 40) {
				int s = length >> 3;
				l = median(absolutes, l, l + s, l + (s << 1));
				m = median(absolutes, m - s, m, m + s);
				n = median(absolutes, n - (s << 1), n - s, n);
			}
			m = median(absolutes, l, m, n);
		}
		long v = absolutes[m];
   
		int a = offset;
		int b = a;
		int c = offset + length - 1;
		int d = c;
		while (true) {
			for (;b <= c && absolutes[b] <= v;b++) {
				if (absolutes[b] == v) {
					swap(absolutes, names, a++, b);
				}
			}
			for (;c >= b && absolutes[c] >= v;c--) {
				if (absolutes[c] == v) {
					swap(absolutes, names, c, d--);
				}
			}
			if (b > c) {
				break;
			}
			swap(absolutes, names, b++, c--);
		}
   
		int s, n = offset + length;
		s = Math.min(a - offset, b - a);
		swapAll(absolutes, names, offset, b - s, s);
		s = Math.min(d - c, n - d - 1);
		swapAll(absolutes, names, b, n - s, s);

		if ((s = b - a) > 1) {
			sort(absolutes, names, offset, s);
		}
		if ((s = d - c) > 1) {
			sort(absolutes, names, n - s, s);
		}
	}

	private void swap(long[] absolutes, String[] names, int i, int j) {
		long swapValue = absolutes[j];
		String swapKey = names[j];
		absolutes[j] = absolutes[i];
		names[j] = names[i];
		absolutes[i] = swapValue;
		names[i] = swapKey;
	}

	private int median(long[] x, int a, int b, int c) {
		return (x[a] < x[b] ?
			(x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
			(x[b] > x[c] ? b : x[a] > x[c] ? c : a));
	}

	private void swapAll(long[] absolutes, String[] names, int a, int b, int n) {
		for (int i = 0; i < n; i++, a++, b++)
			swap(absolutes, names, a, b);
	}
}
