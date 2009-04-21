class Widget {
  int x, y, width, height;
  String label; int event;
  color widgetColor, labelColor;
  PFont widgetFont;
  int strokecolor= 0;

Widget(int x,int y, int width, int height, String label, color w_color, PFont font, int event){
    this.x = x; 
    this.y = y;
    this.width = width; 
    this.height= height;
    this.label = label; 
    this.event = event; 
    this.widgetColor = w_color; 
    this.widgetFont = font;
    this.labelColor = 0;
   }

void draw(){
    stroke(strokecolor);
    fill(widgetColor);
    rect(x,y,width+10,height+10);
    fill(labelColor);
    text(label, x+10, y+height-10);
  }

  void setStroke(int c){
    strokecolor = c;
  }
  
  color getColor(){
    return this.widgetColor;
  }
int check(int mX, int mY){
    
  if(mX > x && mX < x+width && mY > y && mY < y+height){
        
    return event;        
     }else{
     return 0;
     }
  }

}
