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
import org.witshells.filehandling.FileHandling;
import org.witshells.models.Question;

public class CreateMcq {

    private BorderPane mainRoot;
    private HBox heading;
    private VBox center;
    private HBox bottom;

    private TextField quiz_input;
    private TextField option1_input;
    private TextField option2_input;
    private TextField option3_input;
    private TextField answer_input;

    public CreateMcq(){
        initialize();
    }

    public void initialize(){
        layoutSetup();
        fieldSetup();
        buttonSetup();
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
        Label heading_msg = new Label("Add Your Question Below.");
        heading_msg.setFont(Font.font("Arial",24));

        heading.getChildren().add(heading_msg);

        quiz_input = new TextField();
        quiz_input.setPromptText("Question here");
        option1_input = new TextField();
        option1_input.setPromptText("option 1");
        option2_input = new TextField();
        option2_input.setPromptText("option 2");
        option3_input = new TextField();
        option3_input.setPromptText("option 3");
        answer_input = new TextField();
        answer_input.setPromptText("answer");


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
        Button add_btn = new Button();
        add_btn.setText("Add");
        add_btn.setFont(Font.font("Arial",18));

        add_btn.setOnAction(e->{
            if(quiz_input.getText().isEmpty() ||
            option1_input.getText().isEmpty() ||
                    option2_input.getText().isEmpty() ||
                    option3_input.getText().isEmpty() ||
                    answer_input.getText().isEmpty()) return;

            App.questions.add(new Question(
                    quiz_input.getText(),
                    option1_input.getText(),
                    option2_input.getText(),
                    option3_input.getText(),
                    answer_input.getText()
            ));

            quiz_input.setText("");
            option1_input.setText("");
            option2_input.setText("");
            option3_input.setText("");
            answer_input.setText("");
        });

        center.getChildren().add(add_btn);

        Button submit_btn = new Button("Done");
        submit_btn.setFont(Font.font("Arial",18));

        submit_btn.setOnAction(e->{
            App.stage.setScene(new Scene(new HomePage().getContent()));
            System.out.println(App.questions.toString());
            // write the file when user is done.
            new FileHandling().fileWriting();
        });
        bottom.getChildren().add(submit_btn);
    }

    public BorderPane getContent(){
        return mainRoot;
    }
}
