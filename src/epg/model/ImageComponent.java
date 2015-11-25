/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.COMPONENTS;
import epg.file.HTMLWorker;
import static epg.file.JsonCreator.JSON_CAPTION;
import static epg.file.JsonCreator.JSON_FILE;
import static epg.file.JsonCreator.JSON_FILE_URL;
import static epg.file.JsonCreator.JSON_HEIGHT;
import static epg.file.JsonCreator.JSON_TYPE;
import static epg.file.JsonCreator.JSON_WIDTH;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author cgmp
 */
public class ImageComponent extends Component {
    static COMPONENTS type = COMPONENTS.IMAGE;
    
    
    String fileURL;
    String file;
    String caption;

    //@todo FLOAT LEFT OR RIGHT
    public ImageComponent(int width, int length, String imageURL, String file, String caption) {
        super(width, length);
        this.fileURL = imageURL;
        this.file = file;
        this.caption = caption;

    }

    public ImageComponent() {
        super(200, 200);
        caption = "";
        file = "";
        fileURL = "";
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setImageURL(String imageURL) {
        this.fileURL = imageURL;
    }

    @Override
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    
    public ImageComponent(JsonObject compJSON)
    {
        super(compJSON.getInt(JSON_WIDTH), compJSON.getInt(JSON_HEIGHT));
        caption = compJSON.getString(JSON_CAPTION);
        file = compJSON.getString(JSON_FILE);
        fileURL = compJSON.getString(JSON_FILE_URL);        
    }
    
    
    
    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder()
                .add(JSON_TYPE, type.ordinal())
                .add(JSON_WIDTH, width)
                .add(JSON_HEIGHT, height)
                .add(JSON_CAPTION, caption)
                .add(JSON_FILE, file)
                .add(JSON_FILE_URL, fileURL);
        
        return comp.build();
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateImageComponentHTML(this);
    }

    @Override
    public String getDisplayText() {
        return "An Image which is: " + width + "px wide and " + height + "px tall. The image file is:" + file;
    }

}
