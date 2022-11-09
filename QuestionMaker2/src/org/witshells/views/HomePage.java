package org.witshells.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import org.witshells.App;

public class HomePage {

    // creating layouts
    private BorderPane mainRoot;
    private HBox center;

    public HomePage(){
        initialize();
    }

    public void initialize(){
        layoutSetup();
        buttonSetup();
    }

    public void layoutSetup(){
        mainRoot = new BorderPane();
        center = new HBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);
        center.setStyle(
                "-fx-background-color: lightgreen;"
        );
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);

        Label heading = new Label("Quastion Bank Creator Programm");
        heading.setFont(Font.font("Arial",24));

        top.getChildren().add(heading);
        mainRoot.setTop(top);
        mainRoot.setCenter(center);
    }

    public void buttonSetup(){
        Button create = new Button("Create");
        create.setFont(Font.font("Arial",18));
        create.setOnAction(e->{
            App.stage.setScene(new Scene(new CreateMcq().getContent()));
        });

        Button edit = new Button("Edit");
        edit.setFont(Font.font("Arial",18));
        edit.setOnAction(e->{
            if(!App.questions.isEmpty())
                App.stage.setScene(new Scene(new EditMcq().getContent()));
        });

        Button view = new Button("View Quiz");
        view.setFont(Font.font("Arial",18));
        view.setOnAction(e->{
            if(!App.questions.isEmpty())
                App.stage.setScene(new Scene(new ViewQuestions().getContent()));
        });

        Button delete = new Button("Delete");
        delete.setFont(Font.font("Arial",18));
        delete.setOnAction(e->{
            // clear the mcq.
            if(!App.questions.isEmpty())
                App.stage.setScene(new Scene(new DeleteQuestion().getContent()));
        });
        center.getChildren().addAll(create,edit,view,delete);
    }

    public BorderPane getContent(){
        return mainRoot;
    }
}