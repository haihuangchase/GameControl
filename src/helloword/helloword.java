package helloword;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helloword.txt.KeyUtil;
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
	
	private HashMap hm = new HashMap();

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

        Label gm = new Label("Game Name");
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

        List<TextField> wl = new ArrayList<>();
        List<TextField> sl = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            TextField w = new TextField();
            grid.add(w, 0, 3+i);
            TextField s = new TextField();
            grid.add(s, 1, 3+i);
            wl.add(w);
            sl.add(s);
        }
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        	    Map<String, String> keyMap = KeyUtil.load(userTextField.getText());
        	    int i = 0;
        	    for (String skill: keyMap.keySet()) {
        	        wl.get(i).setText(skill);
        	        sl.get(i).setText(keyMap.get(skill));
        	        i++;
                }
        	}
        });

        Button btn = new Button("Create Game");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 7);
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                for (int i = 0; i < wl.size(); i++) {
                    String ws = wl.get(i).getText();
                    String scs = sl.get(i).getText();
                    hm.put(ws, scs);
                }

                KeyUtil.save(userTextField.getText(), hm);
                Voice voice = new Voice();
                KeyUtil keyUtil = new KeyUtil(userTextField.getText());
                while (true) {
                    String skill = voice.recognize();
                    keyUtil.pressKey(skill);
                }
            }
        });
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
 public static void main(String[] args) {
        launch(args);
    }

}
