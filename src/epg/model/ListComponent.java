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
import epg.prompts.ListPrompt;
import java.math.BigDecimal;
import java.util.ArrayList;
import javafx.scene.control.IndexRange;
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
        super(FONT.Bree_Serif, 18);
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

        for (int i = 0; i < itemsJSON.size(); i++) {
            JsonObject item = itemsJSON.getJsonObject(i);
            JsonArray linksJSON = item.getJsonArray("links");
            ArrayList<Hyperlink> links = new ArrayList<Hyperlink>();
            for (int k = 0; k < linksJSON.size(); k++) {
                JsonObject b = linksJSON.getJsonObject(k);
                IndexRange c = new IndexRange(b.getInt("start"), b.getInt("end"));
                String txt = b.getString("txt");
                String url = b.getString("url");
                links.add(new Hyperlink(c, txt, url));
            }
            
            listItems.add(new Item(item.getString(JSON_TEXT), links));
        }
    }

    @Override
    public void editPrompt() {
        new ListPrompt(this);
    }

    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder()
                .add(JSON_TYPE, type.ordinal())
                .add(JSON_FONT_TYPE, this.font.ordinal())
                .add(JSON_FONT_SIZE, this.fontSize);

        JsonArrayBuilder items = Json.createArrayBuilder();
        for (Item item : listItems) {
            JsonObjectBuilder itemJSON = Json.createObjectBuilder()
                    .add(JSON_TEXT, item.itemtext);

            JsonArrayBuilder linksJSON = Json.createArrayBuilder();
            for (int i = 0; i < item.links.size(); i++) {
                JsonObject a = Json.createObjectBuilder()
                        .add("txt", item.links.get(i).txt)
                        .add("url", item.links.get(i).url)
                        .add("start", item.links.get(i).range.getStart())
                        .add("end", item.links.get(i).range.getEnd())
                        .build();
                linksJSON.add(a);
            }
            itemJSON.add("links", linksJSON);

            items.add(itemJSON);
        }

        comp.add(JSON_ITEMS, items);
        return comp.build();
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateListComponentHTML(this);
    }

    @Override
    public String getDisplayText() {
        return "A List Component.";
    }

}
