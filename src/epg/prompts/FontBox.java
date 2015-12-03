/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_PROMPT_BUTTON;
import epg.ProgramConstants.FONT;
import epg.error.ErrorHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author cgmp
 */
public class FontBox extends HBox {

    //data

    FONT font;
    int fontSize;

    //ui
    ComboBox typeBox;
    TextField sizeField;

    public FontBox(FONT font, int fontSize) {
        this.font = font;
        typeBox = new ComboBox();
        typeBox.getItems().addAll("Fjalla One", "Bree Serif", "Muli", "Vollkorn", "Iconsolata");
        typeBox.getSelectionModel().select(font.ordinal());
        this.fontSize = fontSize;
        sizeField = new TextField("" + this.fontSize);
        sizeField.addEventFilter(KeyEvent.KEY_TYPED, ErrorHandler.getNumberKeyEventHandler());
        sizeField.setMaxWidth(40);

        typeBox.getStyleClass().add(CSS_PROMPT_BUTTON);

        this.getChildren().addAll(typeBox, sizeField);
    }

    public FONT getFontType() {
        return FONT.values()[typeBox.getSelectionModel().getSelectedIndex()];
    }

    public int getFontSize() {
        if (sizeField.getText().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(sizeField.getText());
        }
    }

}
