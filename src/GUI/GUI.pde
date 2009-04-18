 Button mybutton;
 color CurrentColour; 


void setup(){
  CurrentColour = color(200);
  mybutton = new Button(100, 380, 20, 100, CurrentColour, false);
  
    size(400, 400);
}

void draw(){
  
  mybutton.draw();
}

boolean event = mybutton.check(mouseX, mouseY);

if(event && mousePressed()){
 println("click");
  
}

  
  
