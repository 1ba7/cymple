Class Button extends Widget{
  
  int buttonX, buttonY, width, height;
  color button_color;
  boolean check =false;
  
  
  Button(int x,int y, int w, int h, color c, boolean event){
    this.buttonX = x;
    this.buttonY = y;
    this.width = w;
    this.height = h;
    this.button_colour = c;
    this.check = event;
  }
  
}

