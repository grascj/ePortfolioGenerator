/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.COMPONENTS;
import static epg.ProgramConstants.DEFAULT_IMG;
import static epg.ProgramConstants.DEFAULT_IMG_NAME;
import epg.file.HTMLWorker;
import static epg.file.JsonCreator.JSON_CAPTION;
import static epg.file.JsonCreator.JSON_FILE;
import static epg.file.JsonCreator.JSON_FILE_URL;
import static epg.file.JsonCreator.JSON_FLOATER;
import static epg.file.JsonCreator.JSON_HEIGHT;
import static epg.file.JsonCreator.JSON_TYPE;
import static epg.file.JsonCreator.JSON_WIDTH;
import epg.prompts.ImagePrompt;
import java.io.File;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author cgmp
 */
public class ImageComponent extends Component {

    static COMPONENTS type = COMPONENTS.IMAGE;
    
    public enum FLOAT {left, right, none};
    
    
    FLOAT floater;
    String fileURL;
    String file;
    String caption;

    //@todo FLOAT LEFT OR RIGHT
    public ImageComponent(int width, int length, String imageURL, String file, String caption, FLOAT floater) {
        super(width, length);
        this.floater = floater;
        this.fileURL = imageURL;
        this.file = file;
        this.caption = caption;

    }

    public ImageComponent() {
        super(200, 200);
        floater = FLOAT.none;
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

    public FLOAT getFloater() {
        return floater;
    }

    public void setFloater(FLOAT floater) {
        this.floater = floater;
    }
   
    
    
    
    

    @Override
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public ArrayList<File> getMedia() {
        ArrayList<File> list = new ArrayList<File>();
        if (!fileURL.equals("")) {
            list.add(new File(fileURL));
        } else {
            file = DEFAULT_IMG_NAME;
            fileURL = DEFAULT_IMG;
            list.add(new File(DEFAULT_IMG));
        }
        return list;
    }

    public ImageComponent(JsonObject compJSON) {
        super(compJSON.getInt(JSON_WIDTH), compJSON.getInt(JSON_HEIGHT));
        caption = compJSON.getString(JSON_CAPTION);
        file = compJSON.getString(JSON_FILE);
        fileURL = compJSON.getString(JSON_FILE_URL);
        floater = FLOAT.values()[compJSON.getInt(JSON_FLOATER)];
    }

        @Override
    public void editPrompt()
    {
        new ImagePrompt(this);
    }
    
    
    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder()
                .add(JSON_TYPE, type.ordinal())
                .add(JSON_WIDTH, width)
                .add(JSON_HEIGHT, height)
                .add(JSON_CAPTION, caption)
                .add(JSON_FILE, file)
                .add(JSON_FILE_URL, fileURL)
                .add(JSON_FLOATER, floater.ordinal());

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
