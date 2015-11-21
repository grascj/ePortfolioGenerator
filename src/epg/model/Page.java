/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;


/**
 *
 * @author cgmp
 */

import epg.ProgramConstants.COLOR;
import epg.ProgramConstants.FONT;
import epg.ProgramConstants.LAYOUT;
import java.util.ArrayList;


public class Page {
    
    
    //@TODO fonts?
    FONT pagefont;
    LAYOUT layout;
    COLOR colors;
    String bannerURL;
    String banner;
    String footerURL;
    String footer;
    String title;
    
    String bannerText;
    String footerText;
            
            
    ArrayList<Component> components;
    ArrayList<SlideShow> slideshows;
    
    //CONSTRUCTORS
    public Page()
    {
      layout = LAYOUT.lownav;
      colors = COLOR.SBUred;
      title = "Untitled Page";
      bannerURL = null;
      footerURL = null;
      components = new ArrayList<Component>();
    }
    
    public ArrayList<SlideShow> getSlideshows() {
        return slideshows;
    }

    //GETTERS AND SETTERS
    public void setSlideshows(ArrayList<SlideShow> slideshows) {
        this.slideshows = slideshows;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
    
    public LAYOUT getLayout() {
        return layout;
    }

    public void setLayout(LAYOUT layout) {
        this.layout = layout;
    }

    public COLOR getColors() {
        return colors;
    }

    public void setColors(COLOR colors) {
        this.colors = colors;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public String getFooterURL() {
        return footerURL;
    }

    public void setFooterURL(String footerURL) {
        this.footerURL = footerURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }
    
    
    
    
    
    public String toString()
    {
        return title;
    }
    
    
    
}
