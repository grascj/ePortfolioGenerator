/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_OK;
import epg.model.HeaderComponent;
import static epg.view.ViewHelper.initChildButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class HeaderPrompt extends Stage {

    BorderPane uicontainer;

    FontBox fb;
    VBox headercontainer;
    Label headerLabel;
    TextField headerText;
    HeaderComponent comp;
    Button okayBtn;

    boolean ok;

    public HeaderPrompt(HeaderComponent comp) {
        initModality(Modality.APPLICATION_MODAL);

        ok = false;
        this.comp = comp;

        fb = new FontBox(comp.getFont(), comp.getFontSize());

        uicontainer = new BorderPane();
        headercontainer = new VBox();
        headerLabel = new Label("Enter your header:");
        headerText = new TextField(this.comp.getText());
        okayBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        okayBtn.setOnAction(e -> {
            ok = true;
            this.comp.setText(headerText.getText());
            this.comp.setFont(fb.getFontType());
            this.comp.setFontSize(fb.getFontSize());
            this.hide();
        });
        headercontainer.getChildren().addAll(headerLabel, headerText);
        headercontainer.getStyleClass().add(CSS_CONTAINER);
        fb.getStyleClass().add(CSS_CONTAINER);

        uicontainer.setTop(fb);
        uicontainer.setCenter(headercontainer);
        uicontainer.setBottom(okayBtn);
        uicontainer.getStyleClass().add(CSS_CONTAINER);

        Scene promptbody = new Scene(uicontainer, 300, 200);
        promptbody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptbody);
        this.showAndWait();

    }

    public boolean isOk() {
        return ok;
    }

}
