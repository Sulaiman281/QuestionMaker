package org.witshells.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.witshells.App;
import org.witshells.models.Question;

public class DeleteQuestion {
    private BorderPane mainRoot;
    private HBox heading;
    private VBox center;
    private HBox bottom;

    // labels
    private Label heading_msg, question, choice1,choice2,choice3,answer;

    private int index = 0;

    public DeleteQuestion(){
        initialize();
    }

    public void initialize(){
        layoutSetup();
        setLabels();
        buttonSetup();

        setQuestion(App.questions.get(index));
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

    public void setLabels(){
        heading_msg = new Label("Delete Question?");
        heading_msg.setFont(Font.font("Arial",24));

        question = new Label();
        question.setFont(Font.font("Arial",20));

        choice1 = new Label();
        choice1.setFont(Font.font("Arial",18));
        choice2 = new Label();
        choice2.setFont(Font.font("Arial",18));
        choice3 = new Label();
        choice3.setFont(Font.font("Arial",18));
        answer = new Label();
        answer.setFont(Font.font("Arial",18));

        center.getChildren().addAll(question,choice1,choice2,choice3,answer);
        heading.getChildren().add(heading_msg);
    }

    public void setQuestion(Question question){
        this.question.setText(question.getQuestion());
        this.choice1.setText(question.getChoice1());
        this.choice2.setText(question.getChoice2());
        this.choice3.setText(question.getChoice3());
        this.answer.setText(question.getAnswer());
    }

    public void buttonSetup(){
        Button next = new Button("Next");
        next.setFont(Font.font("Arial",18));

        next.setOnAction(e->{
            if(index >= 0 && index < App.questions.size()){
                if(App.questions.size()-1 != index)
                    setQuestion(App.questions.get(++index));
            }
        });

        Button previous = new Button("Previous");
        previous.setFont(Font.font("Arial",18));
        previous.setOnAction(e->{
            if(index >= 0 && index < App.questions.size()){
                if(0 != index)
                    setQuestion(App.questions.get(--index));
            }
        });

        Button delete = new Button("Delete");
        delete.setFont(Font.font("Arial",18));
        delete.setOnAction(e->{
            App.questions.remove(index);
            if(App.questions.isEmpty()){
                App.stage.setScene(new Scene(new HomePage().getContent()));
            }else if(index == 0 && App.questions.size() > 2){
                index = 1;
                setQuestion(App.questions.get(index));
            }else{
                setQuestion(App.questions.get(--index));
            }
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(
                previous,
                delete,
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
    public BorderPane getContent(){
        return mainRoot;
    }
}
