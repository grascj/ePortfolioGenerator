/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.file;

import epg.model.Component;
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
    public static final String O_DIV = "<div class=\\\"compdiv\\\">";
    public static final String E_DIV = "</div>";
    //IMAGES
    public static final String O_IMAGE = "<img";
    public static final String E_IMAGE = "></img>";
    //VIDEOS
    public static final String O_VIDEO = "<video";
    public static final String E_VIDEO = " controls></video>";
    public static final String VIDEOTYPE = " type=\\\"video/mp4\\\"";
    //LINK
    public static final String O_LINK = "<a href=\\\"";
    public static final String C_LINK = ">";
    public static final String E_LINK = "</a>";
    
    //useful stuff
    public static final String O_ID = " id=\\\"";
    public static final String C_ID = "\\\"";

    public static final String O_SRC = " src=\\\".";
    public static final String C_SRC = "\\\"";
    
    public static final String CLASS_COMP = " class=\\\"comp\\\"";
   
    
    //SIZE STUFF
    public static final String O_WIDTH = " width=\\\"";
    public static final String C_WIDTH = "\\\"";
    public static final String O_LENGTH = " length=\\\"";
    public static final String C_LENGTH = "\\\"";
    
    
    private HTMLWorker() {};

    
    //pagelink = a navbar element
    /* NAVBAR BUILDER */
    public static String navBarBuilderHTML(Page page) {
        String html
                = O_LINK + "../" +  page.getTitle() + "/" + page.getTitle() + ".html\\\"" +
                " class=\\\"pagelink\\\"" + C_LINK + page.getTitle() + E_LINK;  
        return html;
    }
    
    
    private static String makeSize(Component comp)
    {
        return O_WIDTH + comp.getWidth() + C_WIDTH + O_LENGTH + comp.getLength() + C_LENGTH;
    }
    
    private static String makeSrc(Component comp)
    {
        return O_SRC + PATH_MEDIA + comp.getFile() + C_SRC;
    }
    
    
    
    /* METHODS FOR COMPONENTS */
    public static String generateImageComponentHTML(ImageComponent comp) {
        String html
                = O_DIV
                + O_IMAGE + CLASS_COMP + makeSize(comp)+ makeSrc(comp) + E_IMAGE
                + E_DIV;

        return html;
    }

    public static String generateTextComponentHTML(TextComponent comp) {
        
        
        String html = O_DIV
                + "hmmm"
                + E_DIV;

        return html;
    }

    public static String generateSlideShowComponentHTML(SlideShowComponent comp) {
        String html
                = O_DIV + "\n"
                + ""
                + E_DIV + "\n";

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
