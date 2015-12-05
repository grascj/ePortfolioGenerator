/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import epg.model.Component;
import epg.model.HeaderComponent;
import epg.model.Hyperlink;
import epg.model.ImageComponent;
import epg.model.Item;
import epg.model.ListComponent;
import epg.model.Page;
import epg.model.ParagraphComponent;
import epg.model.SlideShowComponent;
import epg.model.VideoComponent;
import static javafx.scene.input.KeyCode.E;

/**
 *
 * @author cgmp
 */
public class HTMLWorker {

    // O-denotes open
    // C-denotes close
    // E-denotes end
    public static final String PATH_MEDIA = "/media/";

    //DIVS
    public static final String O_DIV = "<div class=\"compdiv\">";
    public static final String E_DIV = "</div>";

    //IMAGES
    public static final String O_IMAGE = "<img";
    public static final String E_IMAGE = "></img>";
    public static final String O_FLOAT = " style=\"float:";
    public static final String E_FLOAT = ";\"";

    //VIDEOS
    public static final String O_VIDEO = "<video";
    public static final String E_VIDEO = " controls></video>";
    public static final String VIDEOTYPE = " type=\"video/mp4\"";

    //LINK
    public static final String O_LINK = "<a href=\"";
    public static final String C_LINK = ">";
    public static final String E_LINK = "</a>";

    //HEADER
    public static final String O_HEADER = "<h1";
    public static final String C_HEADER = ">";
    public static final String E_HEADER = "</h1>";

    //LIST 
    public static final String O_LIST = "<ul";
    public static final String C_LIST = ">";
    public static final String E_LIST = "</ul>";
    public static final String O_ITEM = "<li>";
    public static final String E_ITEM = "</li>";

    //PARAGRAPHS
    public static final String O_PARA = "<p";
    public static final String C_PARA = ">";
    public static final String E_PARA = "</p>";

    //fonts
    public static final String O_FONT_SIZE = " style=\"font-size:";
    public static final String E_FONT_SIZE = "px;\"";
    public static final String O_FONT_TYPE = " class=\"comp ";
    public static final String E_FONT_TYPE = "\"";

    //useful stuff
    public static final String O_ID = " id=\"";
    public static final String C_ID = "\"";

    public static final String O_SRC = " src=\".";
    public static final String C_SRC = "\"";

    public static final String CLASS_COMP = " class=\"comp\"";

    //SIZE STUFF
    public static final String O_WIDTH = " width=\"";
    public static final String C_WIDTH = "\"";
    public static final String O_HEIGHT = " height=\"";
    public static final String C_HEIGHT = "\"";

    private HTMLWorker() {
    }

    //pagelink = a navbar element
    /* NAVBAR BUILDER */
    public static String navBarBuilderHTML(Page page, boolean isCurrent) {
        String html;

        if (isCurrent) {
            html = O_LINK + "../" + page.getTitle() + "/index.html\""
                    + " class=\"pagelink selectedpagelink\"" + C_LINK + page.getTitle() + E_LINK;
        } else {
            html = O_LINK + "../" + page.getTitle() + "/index.html\""
                    + " class=\"pagelink\"" + C_LINK + page.getTitle() + E_LINK;
        }

        return html;
    }

    private static String makeSize(Component comp) {
        return O_WIDTH + comp.getWidth() + C_WIDTH + O_HEIGHT + comp.getLength() + C_HEIGHT;
    }

    private static String makeSrc(Component comp) {
        return O_SRC + PATH_MEDIA + comp.getFile() + C_SRC;
    }

    /* METHODS FOR COMPONENTS */
    public static String generateImageComponentHTML(ImageComponent comp) {
        String html
                = O_DIV
                + O_IMAGE + CLASS_COMP + makeSize(comp) + makeSrc(comp) + O_FLOAT + comp.getFloater().toString() + E_FLOAT + E_IMAGE
                + E_DIV;
        return html;
    }

    public static String generateHeaderComponentHTML(HeaderComponent comp) {
        String html
                = O_DIV + "\n"
                + O_HEADER + O_FONT_TYPE + comp.getFont().toString() + " header" + E_FONT_TYPE + O_FONT_SIZE + comp.getFontSize() + E_FONT_SIZE + C_HEADER + comp.getText() + E_HEADER + "\n"
                + E_DIV + "\n";
        return html;
    }

    public static String generateListComponentHTML(ListComponent comp) {
        String html = O_DIV + "\n"
                + O_LIST + O_FONT_TYPE + comp.getFont().toString() + " list" + E_FONT_TYPE + O_FONT_SIZE + comp.getFontSize() + E_FONT_SIZE + C_LIST + "\n";

        String o_e = "</a>";

        for (Item item : comp.getListItems()) {
            html += O_ITEM;

            String text = item.itemtext;
            for (Hyperlink link : item.links) {
                String o_s = "<a href=\"" + link.url + "\" class=\"" + comp.getFont().toString() + ";\" style=\"font-size:" + comp.getFontSize() + "px;\" >";
                text = text.substring(0, link.range.getStart()) + o_s + link.txt + o_e + text.substring(link.range.getEnd());
            }

            html += text + E_ITEM + "\n";
        }
        html += E_LIST + "\n"
                + E_DIV + "\n";
        return html;
    }

    public static String generateParagraphComponentHTML(ParagraphComponent comp) {
        String text = comp.getText();
        String o_e = "</a>";

        for (Hyperlink link : comp.getLinks()) {
            String o_s = "<a href=\"" + link.url + "\" class=\"" + comp.getFont().toString() + ";\" style=\"font-size:" + comp.getFontSize() + "px;\" >";
            text = text.substring(0, link.range.getStart()) + o_s + link.txt + o_e + text.substring(link.range.getEnd());
        }

        String html = O_DIV + "\n"
                + O_PARA + O_FONT_TYPE + comp.getFont().toString() + " paragraph" + E_FONT_TYPE + O_FONT_SIZE + comp.getFontSize() + E_FONT_SIZE + C_PARA + text + E_PARA + "\n"
                + E_DIV;

        return html;
    }

    //need to make a json to correspond to it
    public static String generateSlideShowComponentHTML(SlideShowComponent comp, int numSlideShow) {
        //60% of total height for the image 
        String html = O_DIV
                + "<div class=\"slideshowcontainer\" style=\"width:" + comp.getWidth() + "px;\">"
                + "<h1 class=\"slideshowtitle\">" + comp.getslideshow().getTitle() + "</h1>"
                + "<img class=\"slideshowimage\" id=\"" + numSlideShow + "img\"></img>"
                + "<p class=\"slideshowcaption\" id=\"" + numSlideShow + "caption\"></p>"
                + "<div class=\"slideshowbuttoncontainer\">"
                + "<img class=\"prevButton slideshowbutton\" src=\"../data/button_images/previous.png\" onclick=\"previousButton(" + numSlideShow + ")\"></img>"
                + "<img class=\"playButton slideshowbutton\" id=\"" + numSlideShow + "button\" src=\"../data/button_images/play.png\" onclick=\"playSlideShow(" + numSlideShow + ")\"></img>"
                + "<img class=\"nextButton slideshowbutton\" src=\"../data/button_images/next.png\" onclick=\"nextButton(" + numSlideShow + ")\"></img>"
                + E_DIV
                + E_DIV;

        return html;
    }

    public static String generateVideoComponentHTML(VideoComponent comp) {
        String html
                = O_DIV
                + O_VIDEO + CLASS_COMP + VIDEOTYPE + makeSize(comp) + makeSrc(comp) + E_VIDEO
                + E_DIV;

        return html;
    }

}
