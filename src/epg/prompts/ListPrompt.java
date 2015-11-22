/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.prompts;

import epg.model.ListComponent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cgmp
 */
public class ListPrompt extends Stage{

    FontBox fb;
    BorderPane uicontainer;
    ScrollPane itemScroll;
    VBox itemcontainer;
    Button okayBtn;
    
    HBox listbuttoncontainer;
    Button addBtn;
    Button rmBtn;
    VBox body;
    
    
    String selection;
    
    
    //data
    ListComponent comp;
    boolean ok;
    
    
    public class ItemBox extends HBox
    {
        TextField itemText;
        String text;
        public ItemBox(String text)
        {
            this.text = text;
            itemText = new TextField(text);
            itemText.setOnKeyReleased(e->{this.text = itemText.getText();});
            this.getChildren().add(itemText);
        }
    }
    
   
    
    public ListPrompt(ListComponent comp) {
        this.comp = comp;
        ok = false;
        
        initUI();
        initHandlers();
        placeChildren();
        
        Scene promptBody = new Scene(uicontainer);
        this.setScene(promptBody);
        this.showAndWait();
                
    }
    
    
    private void initUI()
    {
        fb = new FontBox(comp.getFont(),comp.getFontSize());
        uicontainer = new BorderPane();
        itemcontainer = new VBox();
        itemScroll = new ScrollPane(itemcontainer);
        okayBtn = new Button();
        listbuttoncontainer = new HBox();
        addBtn = new Button("add");
        rmBtn = new Button("rm");
        body = new VBox();
        
    }
    
    private void initHandlers()
    {
        okayBtn.setOnAction(e->{
            this.hide();
            ok = true;
        });
        
        addBtn.setOnAction(e->{comp.getListItems().add("");populateItems();});
        rmBtn.setOnAction(e->{comp.getListItems().remove(selection); populateItems();});
        
    }
    
    private void placeChildren()
    {
        listbuttoncontainer.getChildren().addAll(addBtn, rmBtn);
        uicontainer.setTop(fb);
        body.getChildren().addAll(listbuttoncontainer, itemScroll);
        
        uicontainer.setCenter(body);
        uicontainer.setBottom(okayBtn);
    }
    
    public boolean isOk()
    {
        return ok;
    }
    
    
    public void populateItems()
    {
        itemcontainer.getChildren().clear();
        for(String item : comp.getListItems())
        {
            ItemBox ibox = new ItemBox(item);
            
            if(item == selection)//selected
            {   //special css
             
            }else//not selected
            {
                //reg css
            }
            ibox.setOnMouseClicked(e->{selection = ibox.text; populateItems();});
            
            itemcontainer.getChildren().add(ibox);
        }
    }
}
