/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import epg.model.ImageComponent;
import epg.model.Page;
import epg.model.SlideShowComponent;
import epg.model.TextComponent;
import epg.model.VideoComponent;

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
    public static final String O_DIV = "<div span=\\\"compdiv\\\"";
    public static final String C_DIV = ">";
    public static final String E_DIV = "</div>";
    //IMAGES
    public static final String O_IMAGE = "<img";
    public static final String C_IMAGE = ">";
    public static final String E_IMAGE = "</img>";
    //VIDEOS
    public static final String O_VIDEO = "<video";
    public static final String C_VIDEO = "controls>";
    public static final String E_VIDEO = "</video>";
    public static final String VIDEOTYPE = " type=\\\"video/mp4\\\" ";
    //LINK
    public static final String O_LINK = "<a href=\\\"";
    public static final String C_LINK = ">";
    public static final String E_LINK = "</a>";
    
    //useful stuff
    public static final String O_ID = " id=\\\"";
    public static final String C_ID = "\\\"";

    public static final String O_SRC = " src=\\\".";
    public static final String C_SRC = "\\\"";
    
    public static final String SPAN_COMP = " span=\\\"comp\\\"";
   
    
    private HTMLWorker() {};

    
    //pagelink = a navbar element
    /* NAVBAR BUILDER */
    public static String navBarBuilderHTML(Page page) {
        String html
                = O_LINK + "../" +  page.getTitle() + "/" + page.getTitle() + ".html\\\"" +
                " span=\\\"pagelink\\\"" + C_LINK + page.getTitle() + E_LINK;  
        return html;
    }
    
    
    
    /* METHODS FOR COMPONENTS */
    public static String generateImageComponentHTML(ImageComponent comp) {
        String html
                = O_DIV + C_DIV + "\n"
                + O_IMAGE + SPAN_COMP + O_SRC + PATH_MEDIA + comp.getFile() + C_SRC + O_ID + comp + C_ID + C_IMAGE + E_IMAGE + "\n"  
                + E_DIV + "\n";

        return html;
    }

    public static String generateTextComponentHTML(TextComponent comp) {
        String html = O_DIV + C_DIV + "\n";

        html+=  E_DIV + "\n";

        return html;
    }

    public static String generateSlideShowComponentHTML(SlideShowComponent comp) {
        String html
                = O_DIV + C_DIV + "\n"
                + ""
                + E_DIV + "\n";

        return html;
    }

    public static String generateVideoComponentHTML(VideoComponent comp) {
        String html
                = O_DIV + C_DIV + "\n"
                + O_VIDEO + SPAN_COMP + O_SRC + PATH_MEDIA +  comp.getVideo() + C_SRC + VIDEOTYPE + C_VIDEO + O_ID + comp + C_ID + E_VIDEO + "\n"
                + E_DIV + "\n";

        return html;
    }

}
