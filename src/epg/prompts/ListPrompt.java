/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.CSS_PROMPT_BUTTON;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_MINUS;
import static epg.ProgramConstants.ICON_PLUS;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_LIST_ADD;
import static epg.ProgramConstants.TT_LIST_RM;
import static epg.ProgramConstants.TT_OK;
import epg.model.Item;
import epg.model.ListComponent;
import static epg.view.ViewHelper.initChildButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class ListPrompt extends Stage {

    FontBox fb;
    BorderPane uicontainer;
    ScrollPane itemScroll;
    VBox itemcontainer;
    Button okayBtn;

    HBox listbuttoncontainer;
    Button addBtn;
    Button rmBtn;
    VBox body;

    Item selection;

    //data
    ListComponent comp;
    boolean ok;

    public class ItemBox extends HBox {
        
        TextField itemText;
        Item item;

        public ItemBox(Item item) {
            this.item = item;
            itemText = new TextField(item.itemtext);
            itemText.setOnMouseClicked(e -> {
                selection = this.item;
            });
            itemText.setOnKeyReleased(e -> {
                this.item.itemtext = itemText.getText();
            });
            this.getChildren().add(itemText);
        }
    }

    public ListPrompt(ListComponent comp) {
        this.setTitle("Add List");
        initModality(Modality.APPLICATION_MODAL);

        this.comp = comp;
        ok = false;

        initUI();
        initHandlers();
        placeChildren();

        Scene promptBody = new Scene(uicontainer, 300, 300);
        promptBody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptBody);
        this.showAndWait();

    }

    private void initUI() {
        fb = new FontBox(comp.getFont(), comp.getFontSize());
        fb.getStyleClass().add(CSS_CONTAINER);
        fb.setStyle("-fx-border-width:0 0 10px 0; -fx-border-color: transparent;");
        uicontainer = new BorderPane();
        itemcontainer = new VBox();
        itemScroll = new ScrollPane(itemcontainer);
        okayBtn = initChildButton(CSS_OK_BUTTON, ICON_CHECK, TT_OK);
        listbuttoncontainer = new HBox();
        addBtn = initChildButton(CSS_PROMPT_BUTTON, ICON_PLUS, TT_LIST_ADD);
        rmBtn = initChildButton(CSS_PROMPT_BUTTON, ICON_MINUS, TT_LIST_RM);
        body = new VBox();

        
        
        uicontainer.getStyleClass().add(CSS_CONTAINER);
        itemcontainer.getStyleClass().add("container_nospacing");
        itemScroll.getStyleClass().add(CSS_CONTAINER);
        listbuttoncontainer.getStyleClass().add(CSS_CONTAINER);
    }

    private void initHandlers() {
        okayBtn.setOnAction(e -> {
            this.hide();
            ok = true;
        });

        addBtn.setOnAction(e -> {
            comp.getListItems().add(new Item(""));
            populateItems();
        });
        rmBtn.setOnAction(e -> {
            comp.getListItems().remove(selection);
            populateItems();
        });

    }

    private void placeChildren() {
        listbuttoncontainer.getChildren().addAll(addBtn, rmBtn);
        uicontainer.setTop(fb);
        body.getChildren().addAll(listbuttoncontainer, itemScroll);

        uicontainer.setCenter(body);
        uicontainer.setBottom(okayBtn);
    }

    public boolean isOk() {
        return ok;
    }

    public void populateItems() {
        itemcontainer.getChildren().clear();
        for (Item item : comp.getListItems()) {
            ItemBox ibox = new ItemBox(item);

            if (item == selection)//selected
            {   //special css

            } else//not selected
            {
                //reg css
            }
            ibox.setOnMouseClicked(e -> {
                selection = ibox.item;
                populateItems();
            });
            ibox.getStyleClass().add(CSS_CONTAINER);
            itemcontainer.getChildren().add(ibox);
        }
    }
}
