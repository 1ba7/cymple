package cymple.common;

public interface Seekable {
	public void connect(Seeker seeker);
	public String startString();
	public String finishString();
	public String rangeString();
}
