class Bar extends Widget{

  int xpos, ypos, height, width;
  color barcolour;
  String label;
  int event;
  
  Bar(int x,int y, int w, int h, color w_color, int event){
    
    this.x = xpos;
    this.y = ypos;
    this.w = width;
    this.h = heigth;
    this.label = null;
    this.w_color = barcolor;
    this.event = event;
  }
  
  void draw(){
    stroke(o);
    fill(barcolor);
    rect(x,y,width,height);
  }
}

