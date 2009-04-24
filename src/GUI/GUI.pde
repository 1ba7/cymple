PFont stdFont;
final int EVENT_NOBUTTON=0;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_BUTTON3=3;
ArrayList myWidgets;
Container TestBox;
int Boxwidth=20;
boolean BoxOpen=false;
color BoxColor;
void setup(){
  
  myWidgets = new ArrayList();
  stdFont=loadFont("ComicSansMS-20.vlw"); 
  textFont(stdFont);
  
  
  myWidgets.add(new Widget(100, 100, 100, 40,"RED", color(255,0,0),stdFont,EVENT_BUTTON1));
  myWidgets.add(new Widget(100, 200, 100, 40,"GREEN", color(0,255,0),stdFont, EVENT_BUTTON2));
  myWidgets.add(new Widget(100, 300, 100, 40,"BLUE", color(0,0,255),stdFont, EVENT_BUTTON3));
  
  size(SCREENWIDTH, SCREENHEIGHT);
  background(255);
}

void draw(){
  background(255);
  TestBox = new Container(0,0,Boxwidth);

  if(TestBox.isMouseOver(mouseX)){
      if(Boxwidth <= 249){
      Boxwidth = Boxwidth +25;
    }
    BoxOpen=true;
  } else {
    Boxwidth = 20;
  }
  TestBox.draw();

//    if(BoxOpen){   
      for (int i = 0; i < myWidgets.size(); i++) { 
   
    Widget singleWidget = (Widget) myWidgets.get(i);
    singleWidget.draw();
      }     

 void mousePressed(){
  

   int event;
  
  for (int i = 0; i < myWidgets.size(); i++) {   
  
    Widget singleWidget = (Widget) myWidgets.get(i);
    event = singleWidget.check(mouseX,mouseY);
    singleWidget.setStroke(0);
    
       switch(event) {             
         case EVENT_BUTTON1:
         singleWidget.setStroke(255);
          BoxColor= color(255,0,0);
          System.out.println("Button 1 pressed");      
           break; 
         case EVENT_BUTTON2:
         singleWidget.setStroke(255);
            BoxColor= color(0,255,0);
            System.out.println("Button 2 pressed");
           break;
         case EVENT_BUTTON3:
         singleWidget.setStroke(255);
          BoxColor= color(0,0,255);
          System.out.println("Button 3 pressed");
          break;
         
       }  
   }
}
//}else{
//        Widget singleWidget = null;
//        singleWidget.draw();
//      }
   }   

