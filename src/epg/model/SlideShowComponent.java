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
import epg.file.JsonCreator;
import static epg.file.JsonCreator.JSON_HEIGHT;
import static epg.file.JsonCreator.JSON_SLIDESHOW;
import static epg.file.JsonCreator.JSON_TYPE;
import static epg.file.JsonCreator.JSON_WIDTH;
import epg.prompts.SlideShowPrompt;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author cgmp
 */
public class SlideShowComponent extends Component {

    static COMPONENTS type = COMPONENTS.SLIDESHOW;

    int numSlideShow;
    SlideShow slideshow;

    public SlideShowComponent(int width, int length, SlideShow slideshow, int numSlideShow) {
        super(width, length);
        this.slideshow = slideshow;
        this.numSlideShow = numSlideShow;
    }

    public SlideShowComponent(int numSlideShow) {
        super(400, 400);
        this.numSlideShow = numSlideShow;
        slideshow = new SlideShow();
        slideshow.title = "";
    }

    public SlideShow getslideshow() {
        return slideshow;
    }

    public void setslideshow(SlideShow ss) {
        this.slideshow = ss;
    }

    @Override
    public String getFile() {
        return null;
    }

    @Override
    public ArrayList<File> getMedia() {
        ArrayList<File> list = new ArrayList<File>();

        for (Slide s : slideshow.getSlides()) {
            if (!s.imageURL.equals("")) {
                list.add(new File(s.imageURL));
            } else {
                s.image = DEFAULT_IMG_NAME;
                s.imageURL = DEFAULT_IMG;
                list.add(new File(DEFAULT_IMG));
            }
        }

        return list;
    }

    public SlideShowComponent(JsonObject compJSON) {
        super(compJSON.getInt(JSON_WIDTH), compJSON.getInt(JSON_HEIGHT));
        slideshow = JsonCreator.loadSlideShow(compJSON.getJsonObject(JSON_SLIDESHOW));
        numSlideShow = compJSON.getInt("numSlideShow");

    }

    @Override
    public void editPrompt() {
        new SlideShowPrompt(this);
    }

    @Override
    public JsonObject jsonify() {
        JsonObjectBuilder comp = Json.createObjectBuilder()
                .add(JSON_TYPE, type.ordinal())
                .add(JSON_WIDTH, width)
                .add(JSON_HEIGHT, height)
                .add("numSlideShow", numSlideShow)
                .add(JSON_SLIDESHOW, JsonCreator.saveSlideShow(slideshow));

        return comp.build();
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateSlideShowComponentHTML(this, this.numSlideShow);
    }

    @Override
    public String getDisplayText() {
        return "A Slide Show which is: " + width + "px wide and " + height + "px tall. The title is: " + slideshow.getTitle();
    }

}
