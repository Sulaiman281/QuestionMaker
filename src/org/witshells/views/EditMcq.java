package org.witshells.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.witshells.App;
import org.witshells.models.Question;

public class EditMcq {

    private BorderPane mainRoot;
    private HBox heading;
    private VBox center;
    private HBox bottom;

    private TextField quiz_input;
    private TextField option1_input;
    private TextField option2_input;
    private TextField option3_input;
    private TextField answer_input;


    private int index = 0;

    public EditMcq(){
        initialize();
    }

    public void initialize(){
        layoutSetup();
        fieldSetup();
        buttonSetup();
        setFields(App.questions.get(index));
    }

    public void layoutSetup(){
        mainRoot = new BorderPane();
        heading = new HBox();
        heading.setStyle(
                "-fx-background-color: lightgreen;"
        );
        heading.setAlignment(Pos.CENTER);
        center = new VBox();
        center.setAlignment(Pos.CENTER_LEFT);
        center.setSpacing(10);

        bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setStyle(
                "-fx-background-color: lightgreen;"
        );

        mainRoot.setTop(heading);
        mainRoot.setCenter(center);
        mainRoot.setBottom(bottom);
    }
    public void fieldSetup(){
        Label heading_msg = new Label("Edit Your Question Below.");
        heading_msg.setFont(Font.font("Arial",24));

        heading.getChildren().add(heading_msg);

        quiz_input = new TextField();
        option1_input = new TextField();
        option2_input = new TextField();
        option3_input = new TextField();
        answer_input = new TextField();

        textFieldAttributes(quiz_input);
        textFieldAttributes(option1_input);
        textFieldAttributes(option2_input);
        textFieldAttributes(option3_input);
        textFieldAttributes(answer_input);

        center.getChildren().addAll(quiz_input,
                option1_input,
                option2_input,
                option3_input,
                answer_input
        );
    }
    public void textFieldAttributes(TextField tf){
        tf.setFont(Font.font("Arial",18));
        tf.setPrefHeight(50);
        tf.setMaxHeight(Region.USE_PREF_SIZE);
    }

    public void buttonSetup(){
        Button next = new Button("Next");
        next.setFont(Font.font("Arial",18));

        next.setOnAction(e->{
            if(index >= 0 && index < App.questions.size()){
                if(App.questions.size()-1 != index)
                    setFields(App.questions.get(++index));
            }
        });

        Button ok = new Button("Okay");
        ok.setFont(Font.font("Arial",18));
        ok.setOnAction(e->{
            modifyQuestion(App.questions.get(index));
        });

        Button previous = new Button("Previous");
        previous.setFont(Font.font("Arial",18));
        previous.setOnAction(e->{
            if(index >= 0 && index < App.questions.size()){
                if(0 != index)
                    setFields(App.questions.get(--index));
            }
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(
                previous,
                ok,
                next
        );
        Button submit_btn = new Button("Done!");
        submit_btn.setFont(Font.font("Arial",18));

        submit_btn.setOnAction(e->{
            App.stage.setScene(new Scene(new HomePage().getContent()));
        });
        bottom.getChildren().add(submit_btn);
        center.getChildren().add(hBox);
    }

    public void setFields(Question question){
        quiz_input.setText(question.getQuestion());
        option1_input.setText(question.getChoice1());
        option2_input.setText(question.getChoice2());
        option3_input.setText(question.getChoice3());
        answer_input.setText(question.getAnswer());
    }

    public void modifyQuestion(Question question){
        question.modify(
                quiz_input.getText(),
                option1_input.getText(),
                option2_input.getText(),
                option3_input.getText(),
                answer_input.getText()
        );
    }

    public BorderPane getContent(){
        return mainRoot;
    }
}
