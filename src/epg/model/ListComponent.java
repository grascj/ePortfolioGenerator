/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.COMPONENTS;
import epg.ProgramConstants.FONT;
import epg.file.HTMLWorker;
import static epg.file.JsonCreator.JSON_FONT_SIZE;
import static epg.file.JsonCreator.JSON_FONT_TYPE;
import static epg.file.JsonCreator.JSON_ITEMS;
import static epg.file.JsonCreator.JSON_TEXT;
import static epg.file.JsonCreator.JSON_TYPE;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author cgmp
 */
public class ListComponent extends TextComponent {

    static COMPONENTS type = COMPONENTS.LIST;

    ArrayList<Item> listItems;

    public ListComponent(FONT font, int fontSize, ArrayList<Item> listItems) {
        super(font, fontSize);
        this.listItems = listItems;
    }

    public ListComponent() {
        super(FONT.Bree_Serif, 12);
        listItems = new ArrayList<Item>();
    }

    public ArrayList<Item> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Item> listItems) {
        this.listItems = listItems;
    }

    
    
    public ListComponent(JsonObject compJSON) {
        super(FONT.values()[compJSON.getInt(JSON_FONT_TYPE)], compJSON.getInt(JSON_FONT_SIZE));
        
        listItems = new ArrayList<Item>();
        
        JsonArray itemsJSON = compJSON.getJsonArray(JSON_ITEMS);
        
        for(int i = 0; i < itemsJSON.size(); i++)
        {
            listItems.add(new Item(itemsJSON.getString(i)));
        }
                    
        
    }

    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder()
                .add(JSON_TYPE, type.ordinal())
                .add(JSON_FONT_TYPE, this.font.ordinal())
                .add(JSON_FONT_SIZE, this.fontSize);
                
        
        JsonArrayBuilder items = Json.createArrayBuilder();
        for(Item item : listItems)
        {
            JsonObject itemJSON = Json.createObjectBuilder()
                    .add(JSON_TEXT, item.itemtext).build();
            items.add(itemJSON);
        }
        
        comp.add(JSON_ITEMS, items);
        return comp.build();
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateListComponentHTML(this);
    }

}
