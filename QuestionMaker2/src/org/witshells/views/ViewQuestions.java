package org.witshells.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.witshells.App;
import org.witshells.models.Question;

import java.util.Random;

public class ViewQuestions {

    private BorderPane mainRoot;
    private VBox center;
    private HBox bottom;

    // label for question.
    private Label question;
    // 4 radio buttons 3 for choices 1 for answer.
    private RadioButton option1, option2, option3, option4;

    // 4 Strings where we will store options to randomize.
    private String[] options = new String[4];

    private int index = 0;

    public ViewQuestions(){
        initialize();
    }

    public void initialize(){
        layoutSetup();
        optionSetup();
        buttonSetup();
        randomize(App.questions.get(index));
    }

    public void layoutSetup(){
        mainRoot = new BorderPane();
        HBox heading = new HBox();
        heading.setStyle(
                "-fx-background-color: lightgreen;"
        );
        heading.setAlignment(Pos.CENTER);
        Label heading_msg = new Label("Solve the Problem.");
        heading_msg.setFont(Font.font("Arial",24));

        heading.getChildren().add(heading_msg);

        center = new VBox();
        center.setAlignment(Pos.CENTER_LEFT);
        center.setSpacing(20);
        bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setStyle(
                "-fx-background-color: lightgreen;"
        );

        mainRoot.setTop(heading);
        mainRoot.setCenter(center);
        mainRoot.setBottom(bottom);
    }
    public void optionSetup(){
        question = new Label();
        question.setFont(Font.font("Arial",24));
        option1 = new RadioButton();
        option1.setFont(Font.font("Arial",18));
        option2 = new RadioButton();
        option2.setFont(Font.font("Arial",18));

        option3 = new RadioButton();
        option3.setFont(Font.font("Arial",18));

        option4 = new RadioButton();
        option4.setFont(Font.font("Arial",18));

        optionEvent(option1,1);
        optionEvent(option2,2);
        optionEvent(option3,3);
        optionEvent(option4,4);
        center.getChildren().addAll(question,option1,option2,option3,option4);

    }
    public void optionEvent(RadioButton o,int x){
        o.setOnAction(e->{
            if(App.questions.get(index).getAnswer().equals(o.getText())){
                colorEffects(o,"green");
            }else{
                colorEffects(o,"red");
            }
        });
        o.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(t1){
                resetSelection(x);
            }
        });
    }

    public void resetSelection(int x){
        option1.setSelected(1 == x);
        option2.setSelected(2 == x);
        option3.setSelected(3 == x);
        option4.setSelected(4 == x);
    }
    public void colorEffects(RadioButton o, String color){
        o.setStyle(
                "-fx-border-color: "+color
        );
    }
    public void buttonSetup(){
        Button next = new Button("Next");
        next.setFont(Font.font("Arial",18));

        next.setOnAction(e->{
            if(index >= 0 && index < App.questions.size()){
                if(App.questions.size()-1 != index)
                    randomize(App.questions.get(++index));
            }
        });

        Button previous = new Button("Previous");
        previous.setFont(Font.font("Arial",18));
        previous.setOnAction(e->{
            if(index >= 0 && index < App.questions.size()){
                if(0 != index)
                    randomize(App.questions.get(--index));
            }
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(previous,next);

        center.getChildren().add(hBox);

        Button submit_btn = new Button("Done");
        submit_btn.setFont(Font.font("Arial",18));

        submit_btn.setOnAction(e->{
            App.stage.setScene(new Scene(new HomePage().getContent()));
        });
        bottom.getChildren().add(submit_btn);
    }

    public void randomize(Question question){

        this.question.setText(question.getQuestion());

        int randomInt = new Random().nextInt(4);

        options[0] = randomInt == 0 ? question.getAnswer() : question.getChoice1();
        options[1] = randomInt == 1 ? question.getAnswer() : randomInt == 0 ? question.getChoice1() : question.getChoice2();
        options[2] = randomInt == 2 ? question.getAnswer() : randomInt == 1 ? question.getChoice2() : question.getChoice3();
        options[3] = randomInt == 3 ? question.getAnswer() : question.getChoice3();

        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);
        option4.setText(options[3]);
    }

    public BorderPane getContent(){
        return mainRoot;
    }
}
