/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import epg.ProgramConstants.TEXT_TYPE;
import epg.model.HeaderComponent;
import epg.model.ListComponent;
import epg.model.ParagraphComponent;
import epg.model.TextComponent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class TextPrompt extends Stage {

    //data
    TextComponent comp;

    //ui
    BorderPane uipositioner;
    Label pickLabel;
    HBox buttoncontainer;
    Button paraBtn;
    Button listBtn;
    Button headerBtn;

    //FLAG
    boolean ok;
    TEXT_TYPE tt;

    public TextPrompt() {
        ok = false;

        initUI();
        initHandlers();
        placeChildren();
        //launch the other prompt depending on button pressed

        this.showAndWait();
    }

    public void initUI() {
        uipositioner = new BorderPane();
        pickLabel = new Label("Choose what type of Text Component:");
        buttoncontainer = new HBox();
        paraBtn = new Button();
        listBtn = new Button();
        headerBtn = new Button();
    }

    public void initHandlers() {
        paraBtn.setOnAction(e -> {
            this.hide();
            comp = new ParagraphComponent();
            ok = true;
            tt = TEXT_TYPE.PARAGRAPH;
        });

        listBtn.setOnAction(e -> {
            this.hide();
            comp = new ListComponent();
            ok = true;
            tt = TEXT_TYPE.LIST;
        });

        headerBtn.setOnAction(e -> {
            this.hide();
            comp = new HeaderComponent();
            ok = true;
            tt = TEXT_TYPE.HEADER;
        });
    }

    public void placeChildren() {
        buttoncontainer.getChildren().addAll(paraBtn, listBtn, headerBtn);

        uipositioner.setTop(pickLabel);
        uipositioner.setCenter(buttoncontainer);

        Scene promptbody = new Scene(uipositioner);
        this.setScene(promptbody);
    }

    public TextComponent getComp() {
        return comp;
    }

    public TEXT_TYPE getTextType() {
        return tt;
    }

    //@todo prompts for a paragraph list or header
    public boolean isOk() {
        return ok;
    }
}
