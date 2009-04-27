int timedelay = 3000, starttime;
PImage SplashScreen;
int xpos = 28, total = 0;
float number;
boolean start = false;

void setup(){
  size (800,600);
  SplashScreen = loadImage("SplashScreen.JPG");
  background(SplashScreen);
}

void draw(){
  //draw the splash screen until the "loading" bar is full.
  if(total <= 21){
    number = random(10);
    
    if(number < 1){
    fill(0,255,0);
    rect(xpos,390,32,32);
    total = total + 1;
    xpos = xpos+34;
    }
  }
  
  else{
    if (!start){
    starttime = millis();
    start = true;
    }
     else{
      if(millis() - starttime <= timedelay){
        redraw();
      }
    else{
      background(255);
      fill(0,0,255);
      rect(100,100,100,100);
    }
    }
  }
  }

