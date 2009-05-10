package cymple.toolkit;
import cymple.common.Graphable;
import cymple.common.GraphData;

public class Graph extends Widget {
	private Graphable graphable;
	private GraphData graphData;

	public Graph(Graphable graphable) {
		super();
		this.graphable = graphable;
	}

	public void draw(Canvas canvas) {
		if (graphable.getGraphData() == null) {
			graphable.update();
		}
		else if (graphData != graphable.getGraphData()) {
			graphData = graphable.getGraphData();
		}
		else {
			canvas.noStroke();
			canvas.fill(0xffcfcfcf);
			canvas.rect(0, 0, getWidth(), getHeight());
			canvas.fill(0xff84fa1e);
			double offset = (double)getWidth() / (graphData.size() - 1);
			int x1, x2 = 0, y1, y2 = (int)((1 - graphData.sample(0)) * getHeight());
			canvas.pushMatrix();
			canvas.translate(0, 50);
			for (int i = 1; i < graphData.size(); i++) {
				x1 = x2;
				y1 = y2;
				x2 = (int)(offset * i);
				y2 = (int)((1 - graphData.sample(i)) * getHeight());
				canvas.beginShape();
				canvas.vertex(x1, y1);
				canvas.vertex(x2, y2);
				canvas.vertex(x2, getHeight());
				canvas.vertex(x1, getHeight());
				canvas.endShape(canvas.CLOSE);
				canvas.stroke(0xff000000);
				canvas.strokeWeight(1);
				canvas.beginShape(canvas.LINES);
				canvas.vertex(x1, y1);
				canvas.vertex(x2, y2);
				canvas.endShape(canvas.CLOSE);
				canvas.noStroke();
			}
			x2 = 0;
			y2 = (int)((1 - graphData.sample(0)) * getHeight());
			for (int i = 1; i < graphData.size(); i++) {
				x1 = x2;
				y1 = y2;
				x2 = (int)(offset * i);
				y2 = (int)((1 - graphData.sample(i)) * getHeight());
				canvas.stroke(0xff000000);
				canvas.line(x1, 0, x1, getHeight());
				canvas.line(x2, 0, x2, getHeight());
			}
			double position, resolution;
			position = graphData.getSeeker().getPosition();
			resolution = graphData.getSeeker().getResolution();
			canvas.fill(0xff000000);
			canvas.popMatrix();
			canvas.textAlign(canvas.LEFT);
			canvas.textFont(boldFont());
			canvas.text(graphable.getScale().startString(position, resolution), 5, 25);
			canvas.textAlign(canvas.CENTER);
			canvas.textFont(boldFont());
			canvas.text(graphable.getScale().finishString(position, resolution / 2), getWidth() / 2, 25);
			canvas.textAlign(canvas.RIGHT);
			canvas.textFont(boldFont());
			canvas.text(graphable.getScale().finishString(position, resolution), getWidth() - 5, 25);
			canvas.textAlign(canvas.LEFT);
		}
	}
}
