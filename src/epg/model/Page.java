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
import epg.ProgramConstants.LAYOUT;
import java.util.ArrayList;


public class Page {
    
    
    //@TODO fonts?
    LAYOUT layout;
    COLOR colors;
    String bannerURL;
    String footerURL;
    String title;
    ArrayList<Component> components;
    
    //CONSTRUCTORS
    public Page()
    {
      layout = LAYOUT.NORMAL;
      colors = COLOR.NORMAL;
      bannerURL = null;
      footerURL = null;
      components = new ArrayList<Component>();
    }
    //@TODO add another constructor
    
    //GETTERS AND SETTERS
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
    
    
    //METHODS
    //@TODO implement Page methods
    public void deleteComponent(Component comp)
    {
        
    }
    
    public void addSlideShowComponent()
    {
        
    }
    
    public void addVideoComponent()
    {
        
    }
    
    public void addImageComponent()
    {
        
    }
    
    public void addTextComponent()
    {
        
    }
    
    
    
    
}
