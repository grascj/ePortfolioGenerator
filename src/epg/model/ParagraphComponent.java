/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.COMPONENTS;
import epg.ProgramConstants.FONT;
import static epg.file.JsonCreator.JSON_FONT_SIZE;
import static epg.file.JsonCreator.JSON_FONT_TYPE;
import static epg.file.JsonCreator.JSON_TEXT;
import epg.prompts.ParagraphPrompt;
import java.util.ArrayList;
import javax.json.Json;
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
        super(FONT.Bree_Serif, 12);
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
    }
    
        @Override
    public void editPrompt()
    {
        new ParagraphPrompt(this);
    }

    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder();

        return comp.build();
    }

    @Override
    public String htmlify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
