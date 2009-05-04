package cymple.common;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Scale {
	private long start;
	private long finish;

	public Scale(long start, long finish) {
		this.start = start;
		this.finish = finish;
	}

	public int asInt(double position) {
		return (int)(1024 * position);
	}

	public long asLong(double position) {
		return (long)(start + (double)(finish - start) * position);
	}

	public int startInt() {
		return asInt(0);
	}

	public int startInt(double position, double resolution) {
		return asInt(position * (1 - resolution));
	}

	public long startLong() {
		return asLong(0);
	}

	public long startLong(double position, double resolution) {
		return asLong(position * (1 - resolution));
	}

	public int finishInt() {
		return asInt(1);
	}

	public int finishInt(double position, double resolution) {
		return asInt(position * (1 - resolution) + resolution);
	}

	public long finishLong() {
		return asLong(1);
	}

	public long finishLong(double position, double resolution) {
		return asLong(position * (1 - resolution) + resolution);
	}

	public String startString() {
		return startString(start, finish);
	}

	public String startString(int start, int finish) {
		return startString(toLongTime(start), toLongTime(finish));
	}

	public String startString(long start, long finish) {
		return format(start, finish).format(new Date(start));
	}

	public String startString(double position, double resolution) {
		return startString(startLong(position, resolution),
			finishLong(position, resolution));
	}

	public String finishString() {
		return finishString(start, finish);
	}

	public String finishString(int start, int finish) {
		return finishString(toLongTime(start), toLongTime(finish));
	}

	public String finishString(long start, long finish) {
		return format(start, finish).format(new Date(finish));
	}

	public String finishString(double position, double resolution) {
		return finishString(startLong(position, resolution),
			finishLong(position, resolution));
	}

	public String rangeString() {
		return "from " + startString() + " to " + finishString();
	}

	public String rangeString(int start, int finish) {
		return "from " + startString(start, finish) + " to " +
			finishString(start, finish);
	}

	public String rangeString(long start, long finish) {
		return "from " + startString(start, finish) + " to " +
			finishString(start, finish);
	}

	public String rangeString(double position, double resolution) {
		return "from " + startString(position, resolution) + " to " +
			finishString(position, resolution);
	}

	public int toIntTime(long time) {
		return asInt((double)(time - start) / (finish - start));
	}

	public long toLongTime(int time) {
		return asLong(time / 1024);
	}

	public String number(long number) {
		return (Math.log10(number) > 8 ? new DecimalFormat("0.###E0") :
			new DecimalFormat("#,##0")).format(number).toLowerCase();
	}

	private SimpleDateFormat format(long start, long finish) {
		long difference = finish - start;

		if (difference < 86400000L) {
			return new SimpleDateFormat("MMMM dd, kk:mm, yyyy");
		}
		else if (difference < 15552000000L) {
			return new SimpleDateFormat("MMMM dd, yyyy");
		}
		else {
			return new SimpleDateFormat("MMMM yyyy");
		}
	}
}
