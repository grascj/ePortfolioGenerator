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
    
    
    FONT pagefont;
    LAYOUT layout;
    COLOR colors;
    String bannerURL;
    String banner;
    String footer;
    String title;
    
    ArrayList<Component> components;
    ArrayList<SlideShow> slideshows;

    



//CONSTRUCTORS
    public Page(FONT pagefont, LAYOUT layout, COLOR colors, String title, String bannerURL, String footer, ArrayList<Component> components, ArrayList<SlideShow> slideshows)
    {
      this.pagefont = pagefont;
      this.layout = layout;
      this.colors = colors;
      this.title =title;
      this.bannerURL =bannerURL;
      this.footer =footer;
      this.components =components;
      this.slideshows =slideshows;
    }
    
    
    
    public Page()
    {
      pagefont = FONT.Bree_Serif;
      layout = LAYOUT.lownav;
      colors = COLOR.SBUred;
      title = "Untitled Page";
      bannerURL = null;
      footer = "";
      components = new ArrayList<Component>();
      slideshows = new ArrayList<SlideShow>();
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


    
    
    
    
    public String toString()
    {
        return title;
    }

    public void setFont(FONT value) {
        pagefont = value;
    }

    public FONT getFont() {
        return pagefont;
    }
    
    
    
}
