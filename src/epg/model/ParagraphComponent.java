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
import static epg.file.JsonCreator.JSON_TEXT;
import static epg.file.JsonCreator.JSON_TYPE;
import epg.prompts.ParagraphPrompt;
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
public class ParagraphComponent extends TextComponent {

    static COMPONENTS type = COMPONENTS.PARAGRAPH;

    ArrayList<Hyperlink> links;
    String text;

    public ParagraphComponent(FONT font, int fontSize, ArrayList<Hyperlink> links, String text) {
        super(font, fontSize);
        this.links = links;
        this.text = text;
    }

    public ParagraphComponent() {
        super(FONT.Bree_Serif, 18);
        this.links = new ArrayList<Hyperlink>();
        this.text = "";
    }

    public ArrayList<Hyperlink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Hyperlink> links) {
        this.links = links;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ParagraphComponent(JsonObject compJSON) {
        super(FONT.values()[compJSON.getInt(JSON_FONT_TYPE)], compJSON.getInt(JSON_FONT_SIZE));
        this.text = compJSON.getString(JSON_TEXT);

        links = new ArrayList<>();
        JsonArray a = compJSON.getJsonArray("links");
        for (int i = 0; i < a.size(); i++) {
            JsonObject b = a.getJsonObject(i);
            IndexRange c = new IndexRange(b.getInt("start"), b.getInt("end"));
            String txt = b.getString("txt");
            String url = b.getString("url");
            links.add(new Hyperlink(c, txt, url));
        }
    }

    @Override
    public void editPrompt() {
        new ParagraphPrompt(this);
    }

    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder()
                .add(JSON_TYPE, type.ordinal())
                .add(JSON_FONT_TYPE, this.font.ordinal())
                .add(JSON_FONT_SIZE, this.fontSize);
        comp.add("text", text);
        JsonArrayBuilder linkcollection = Json.createArrayBuilder();
        for (Hyperlink link : links) {
            JsonObject a = Json.createObjectBuilder()
                    .add("txt", link.txt)
                    .add("url", link.url)
                    .add("start", link.range.getStart())
                    .add("end", link.range.getEnd())
                    .build();
            linkcollection.add(a);
        }
        comp.add("links", linkcollection);
        return comp.build();
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateParagraphComponentHTML(this);
    }

}
