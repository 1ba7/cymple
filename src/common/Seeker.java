package cymple.common;

public interface Seeker {
	public Seekable getSeekable();
	public double getPosition();
	public double getResolution();
	public String rangeToString();
}
