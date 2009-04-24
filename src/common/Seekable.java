package cymple.common;

// Seekables are coupled with a Seeker widget in the toolkit. They have two
// fundamental properties: resolution and positon, which are usually
// controlled by a Seeker in the GUI. Both a double values between 0.0 and
// 1.0. Resolution is the fraction of the data that is displayable at any
// time, and position is the start of the displayable data.
public interface Seekable {
	// This means that the graph will show a fraction of the total dataset.
	public void setResolution(double resolution);
	// Sets the position at which the graph is drawn.
	public void setPosition(double position);
	// Updates any data structures that need updating as a result of a change
	// in position or resolution.
	public void update();
	// Returns a String representation of the start of the specified range.
	public String start();
	// Returns a String representation of the end of the specified range.
	public String finish();
}
