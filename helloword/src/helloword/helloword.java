package helloword;


import java.util.HashMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class helloword extends Application {
	
	HashMap hm = new HashMap();

	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("VAttack");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label gm = new Label("Game Nmae");
        grid.add(gm, 0, 1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        Button btn1 = new Button("Load Game");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 2, 1);

        Text word = new Text("Word");
        grid.add(word, 0, 2);
        Text sc = new Text("Shortcuts");
        grid.add(sc, 1, 2);
        
        TextField w1 = new TextField();
        grid.add(w1, 0, 3);
        TextField sc1 = new TextField();
        grid.add(sc1, 1, 3);
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		w1.setText("attack");
        		sc1.setText("a");
        	}
        });

        Button btn = new Button("Create Game");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                String w1s = w1.getText();
                System.out.println(w1s);
                String sc1s = sc1.getText();
                System.out.println(sc1s);
                hm.put(w1s, sc1s);
            }
        });
        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
 public static void main(String[] args) {
        launch(args);
    }

}