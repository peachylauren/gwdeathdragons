import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.scene.paint.Color; 
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import java.util.*;


public class GW_DeathDragonsGUI extends Application
{

   VBox promptstuff;
   VBox vbox;
   Label infotext;
   Button choice1;
   Button choice2;
   GW_DeathDragons gwdd;
   HBox hboxbutt;
   Label space2;
   Label title;
   Label spacer;
   Label space;
   Button finish;

   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage stage)
   {
      
      title = new Label("death, destruction, and ... dragons?\n\n");
      title.getStyleClass().add("title");
      title.setTextAlignment(TextAlignment.CENTER);
      
      Label prompt1 = new Label("Since the discovery of the magical properties of dragon eggs, their value to society has skyrocketed, but now people are claiming that their homes are being burned up by feral dragons and that if the situation isn't dealt with, it could be disastrous.  The King says its nonsense... He can be trusted, right?\n ".toUpperCase());
      prompt1.getStyleClass().add("prompt");
      prompt1.setTextAlignment(TextAlignment.CENTER);
      
      spacer = new Label("    \n\n     ");
      spacer.getStyleClass().add("spacer");
      
      Label spacer1 = new Label("         ");
      spacer1.getStyleClass().add("spacer");
      
      Button startbutt = new Button("start");
      startbutt.getStyleClass().add("choice-button");
      startbutt.setOnAction(new StartButtonHandler());
      startbutt.setPadding(new Insets(10));
      
      promptstuff = new VBox(prompt1, spacer1, startbutt);
      promptstuff.setAlignment(Pos.CENTER);
      
      vbox = new VBox(title, promptstuff, spacer);
      vbox.getStyleClass().add("back");
      vbox.setAlignment(Pos.CENTER);
      
      Scene scene = new Scene(vbox,800,1000); //create scene
      
      scene.getStylesheets().add("GUI_style.css");
      
      stage.setScene(scene); //set scene
     
      stage.setTitle("Memory Game"); //set title
      
      stage.show(); //show window
   }
   
   class StartButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
      
         vbox.getChildren().removeAll(promptstuff,spacer);
         gwdd = new GW_DeathDragons();
         gwdd.visitSeerORno();
         
         String templabel = gwdd.getLabel();
         infotext = new Label(templabel.toUpperCase());
         infotext.getStyleClass().add("prompt");
         infotext.setTextAlignment(TextAlignment.CENTER);
         
         String c1 = gwdd.getButton1Text();
         choice1 = new Button(c1);
         choice1.getStyleClass().add("choice-button");
         choice1.setOnAction(new ChoiceButtonHandler());
         choice1.setPadding(new Insets(10));
         
         String c2 = gwdd.getButton2Text();
         choice2 = new Button(c2);
         choice2.getStyleClass().add("choice-button");
         choice2.setOnAction(new ChoiceButtonHandler());
         choice2.setPadding(new Insets(10));
         
         space = new Label("  \n   ");
         space.getStyleClass().add("spacer");
         
         space2 = new Label(" \n\n\n\n  ");
         space2.getStyleClass().add("spacer");
         
         hboxbutt = new HBox(choice1, choice2);
         hboxbutt.setSpacing(100);
         hboxbutt.setAlignment(Pos.CENTER);
         
         vbox.getChildren().addAll(infotext,space,hboxbutt,space2);
         
      }
   }
   
   class ChoiceButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
      
         if(event.getSource().equals(choice1)){
            gwdd.goChoice(1);
         } else {
            gwdd.goChoice(2);
         }
         
         if(gwdd.getEnd() == 1){
            endSequence();
         }
         
         String templabel = gwdd.getLabel();
         infotext.setText(templabel.toUpperCase());
         
         String c1 = gwdd.getButton1Text(); 
         choice1.setText(c1);
         
         String c2 = gwdd.getButton2Text();
         choice2.setText(c2);
      
      }
   }
   
   void endSequence(){
      
         String templabel = gwdd.getLabel();
         infotext.setText(templabel.toUpperCase());
         
         vbox.getChildren().removeAll(hboxbutt,space2);
         
         finish = new Button("finish");
         finish.getStyleClass().add("choice-button");
         finish.setOnAction(new FinishButtonHandler());
         finish.setPadding(new Insets(10));

         vbox.getChildren().addAll(finish,space2);
         
    }
    
   class FinishButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
      
         vbox.getChildren().remove(finish);
         title.setText("surprise!\n\n");
         infotext.setText("the dragons were a metaphor for climate change all along\n\n".toUpperCase());
         
         Label action = new Label("So what can you do to help stop dragons?\n\nMaking individual changes are great, but we also need widespread change: \n\nRally! in your community to create changes where you are. \n\nVote! for candidates that have climate change as a priority. \n\nHope! for a future where our home is protected.".toUpperCase());
         action.getStyleClass().add("prompt");
         action.setTextAlignment(TextAlignment.CENTER);
         vbox.getChildren().removeAll(space,space2);
         vbox.getChildren().addAll(action,space);
      }
    }

}
