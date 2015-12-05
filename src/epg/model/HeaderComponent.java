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
import epg.prompts.HeaderPrompt;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author cgmp
 */
public class HeaderComponent extends TextComponent {
    static COMPONENTS type = COMPONENTS.HEADER;
    
    
    String text;

    public HeaderComponent(FONT font, int fontSize, String text) {
        super(font, fontSize);
        this.text = text;
    }

    public HeaderComponent() {
        super(FONT.Bree_Serif, 36);
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
    @Override
    public void editPrompt()
    {
        new HeaderPrompt(this);
    }
    
    
    public HeaderComponent(JsonObject compJSON)
    {
        super(FONT.values()[compJSON.getInt(JSON_FONT_TYPE)], compJSON.getInt(JSON_FONT_SIZE));
        this.text = compJSON.getString(JSON_TEXT); 
    }
    
    @Override
    public JsonObject jsonify() {
            JsonObjectBuilder comp = Json.createObjectBuilder()
                    .add(JSON_TYPE, type.ordinal())
                    .add(JSON_TEXT, text)
                    .add(JSON_FONT_TYPE, this.font.ordinal())
                    .add(JSON_FONT_SIZE, this.fontSize);
            
            
            
        return comp.build();
    }
    

    @Override
    public String htmlify() {
        return HTMLWorker.generateHeaderComponentHTML(this);
    }

    @Override
    public String getDisplayText() {
        return "A Header Component.";
    }

}
