 class Container{

 // Bar opener;
  int xpos;
  int ypos;
  int width;
  int height= SCREENHEIGHT;
  boolean mouseover;
  color ContainerColour = color(200);
    
  Container(int x, int y, int w){
    width=w;
    xpos=x;
    ypos=y;
  }

void SetWidth(int newwidth){
  width=newwidth;  
}


/* Method to see if the mouse is inside the container */
boolean isMouseOver(int mouseX){
   if (mouseX >= xpos && mouseX <= xpos+width){
     mouseover = true;
   }else {
     mouseover = false;
   }
   return mouseover;
 } 
 
  void Expand(){
        width = width+1;
      }
  

  int getX(){
    return xpos;
  }
  
  int getY(){
    return ypos;
  }

  void draw(){
    fill(ContainerColour);
    rect(xpos, ypos, width, height);
    fill(0,0,255);
    rect(xpos+width, ypos, width/5, height);
    }
  }
