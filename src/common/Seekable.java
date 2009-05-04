package cymple.common;

public interface Seekable {
	public void connect(Seeker seeker);
	public void update();
	public Scale getScale();
}
