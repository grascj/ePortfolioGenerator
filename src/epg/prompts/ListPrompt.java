/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import static epg.ProgramConstants.CSS_CONTAINER;
import static epg.ProgramConstants.CSS_OK_BUTTON;
import static epg.ProgramConstants.CSS_PROMPT_BUTTON;
import static epg.ProgramConstants.CSS_SLIDE;
import static epg.ProgramConstants.CSS_SLIDE_SELECTED;
import static epg.ProgramConstants.ICON_CHECK;
import static epg.ProgramConstants.ICON_MINUS;
import static epg.ProgramConstants.ICON_PAPERCLIP;
import static epg.ProgramConstants.ICON_PLUS;
import static epg.ProgramConstants.ICON_X;
import static epg.ProgramConstants.PATH_PROMPTSTYLESHEET;
import static epg.ProgramConstants.TT_LIST_ADD;
import static epg.ProgramConstants.TT_LIST_RM;
import static epg.ProgramConstants.TT_OK;
import epg.controller.ChangeController;
import epg.error.ErrorHandler;
import epg.model.Hyperlink;
import epg.model.Item;
import epg.model.ListComponent;
import epg.view.ViewHelper;
import static epg.view.ViewHelper.initChildButton;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
    ArrayList<Item> items;
    boolean ok;

    public class ItemBox extends VBox {

        TextArea itemText;
        Button addLinkButton;
        Item item;
        ArrayList<Hyperlink> links;
        HBox itembox;
        VBox linkcontainer;

        public ItemBox(Item item) {
            this.item = item;

            links = item.links;
            itemText = new TextArea(item.itemtext);
            itemText.prefRowCountProperty().add(1);
            itemText.setStyle("-fx-max-height:30px;-fx-max-width:Infinity;");
            addLinkButton = ViewHelper.initChildButton(CSS_PROMPT_BUTTON, ICON_PAPERCLIP, "Add a link.");

            itembox = new HBox();
            itembox.getChildren().addAll(addLinkButton, itemText);
            linkcontainer = new VBox();
            linkcontainer.getStyleClass().add("container_nospacing");

            addLinkButton.setOnAction(e -> {

            });
            this.setOnMouseClicked(e -> {
                selection = this.item;
            });
            
            itemText.setOnKeyReleased(e -> {
                this.item.itemtext = itemText.getText();
            });
            addLinkButton.setOnAction(e -> {
                makeHyperlink();
            });
            itemText.textProperty().addListener(e -> {
                checkLinkValidity();
            });
            itemText.focusedProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if(selection != item && newValue.equals(true))
                    {
                        selection = item;
                        populateItems();
                    }
                }
            });
            this.getChildren().addAll(itembox, linkcontainer);
            populateHyperlinks();

        }

        public void makeHyperlink() {
            if (!itemText.getSelectedText().trim().equals("")) {
                Hyperlink newlink = new Hyperlink(itemText.getSelection(), itemText.getSelectedText(), "");
                if (!checkOverlap(newlink)) {
                    links.add(newlink);
                    populateHyperlinks();
                }
            }
        }

        public void populateHyperlinks() {
            linkcontainer.getChildren().clear();
            for (Hyperlink link : links) {
                LinkBox a = new LinkBox(link);
                a.destroy.setOnAction(e -> {
                    links.remove(link);
                    populateHyperlinks();
                });
                a.getStyleClass().add(CSS_SLIDE);
                linkcontainer.getChildren().add(a);
            }
        }

        //true means overlap 
        //false means no overlap
        public boolean checkOverlap(Hyperlink newlink) {
            //start is in the substring
            //end is not
            for (Hyperlink link : links) {
                if (compareLinkOverlap(newlink, link)) {
                    ErrorHandler.processError("Link Error", "There cannot be overlapping Hyperlinks.");
                    return true;
                }
            }
            return false;
        }

        //true means overlap
        //false means no overlap
        public boolean compareLinkOverlap(Hyperlink a, Hyperlink b) {
            boolean flag = false;
            if (a.range.getStart() <= b.range.getEnd() - 1 && b.range.getStart() <= a.range.getEnd() - 1) {
                flag = true;
            }

            return flag;
        }

        public void checkLinkValidity() {
            boolean flag = false;
            ArrayList<Hyperlink> stuffToRemove = new ArrayList<>();

            for (Hyperlink link : links) {
                if (itemText.getText().contains(link.txt)) {
                    int a = itemText.getText().indexOf(link.txt);
                    link.range = new IndexRange(a, a + link.txt.length());
                } else {
                    stuffToRemove.add(link);
                    flag = true;
                }
            }

            links.removeAll(stuffToRemove);

            populateHyperlinks();

            if (flag) {
                ErrorHandler.processError("Link Error", "A Link was removed because the text no longer exists.");
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class LinkBox extends HBox {

        Label text;
        Label urlLabel;
        TextField urlField;
        Hyperlink link;
        public Button destroy;

        public LinkBox(Hyperlink link) {
            destroy = ViewHelper.initChildButton(CSS_PROMPT_BUTTON, ICON_X, "Remove this link");
            this.link = link;
            this.text = new Label("     " + this.link.txt);
            urlLabel = new Label("     Enter URL: ");
            urlField = new TextField(link.url);
            urlField.setOnKeyReleased(e -> {
                this.link.url = urlField.getText();
            });
            this.getChildren().addAll(destroy, this.text, urlLabel, urlField);
            this.setStyle("-fx-padding: 10px; -fx-alignment: center;");
        }
    }

    public ListPrompt(ListComponent comp) {
        this.setTitle("Add List");
        initModality(Modality.APPLICATION_MODAL);

        this.comp = comp;

        items = new ArrayList<Item>();
        for (Item item : comp.getListItems()) {
            items.add(item.copy());
        }

        ok = false;

        initUI();
        initHandlers();
        placeChildren();

        Scene promptBody = new Scene(uicontainer, 500, 600);
        promptBody.getStylesheets().add(PATH_PROMPTSTYLESHEET);
        this.setScene(promptBody);
        populateItems();
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
            comp.setListItems(items);
            ok = true;
            comp.setFont(fb.getFontType());
            comp.setFontSize(fb.getFontSize());
            ChangeController.wasChanged();
            this.hide();
        });

        addBtn.setOnAction(e -> {
            items.add(new Item("", new ArrayList<Hyperlink>()));
            populateItems();
        });
        rmBtn.setOnAction(e -> {
            items.remove(selection);
            selection = null;
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

    private void populateItems() {
        itemcontainer.getChildren().clear();
        for (Item item : items) {
            ItemBox ibox = new ItemBox(item);

            if (item == selection)//selected
            {   //special css
                ibox.getStyleClass().add(CSS_SLIDE_SELECTED);
            } else//not selected
            {
                ibox.getStyleClass().add(CSS_SLIDE);
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
