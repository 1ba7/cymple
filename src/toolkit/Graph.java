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
			canvas.fill(0xFFFFFFFF);
			canvas.rect(0, 0, getInternalWidth(), getInternalHeight());	
			int lateralDistance = (getInternalWidth()/graphData.size());

			for(int i =0; i < graphData.size(); i++){
					canvas.line(lateralDistance*i, 			//x1
					(1- graphData.sample(i)) *getHeight(),	//y1
					 lateralDistance*i+1, 					//x2
					(1- graphData.sample(i+1))*getHeight()) //y2		
			}
		}
	}
}
