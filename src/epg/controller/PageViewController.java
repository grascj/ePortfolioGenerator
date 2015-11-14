/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.ProgramConstants.COLOR;
import epg.ProgramConstants.LAYOUT;
import epg.model.Component;
import epg.view.PageEditor;

/**
 *
 * @author cgmp
 */
public class PageViewController {
    
    PageEditor pe;
    
    
    public PageViewController(PageEditor pe)
    {
        this.pe = pe;
    }

    public void handleColorChange(int index) {
        pe.getPage().setColors(COLOR.values()[index]);
    }

    public void handleLayoutChange(int index) {
        pe.getPage().setLayout(LAYOUT.values()[index]);
    }

    public void handleFooterChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleBannerChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleTitleChange(String text) {
        pe.getPage().setTitle(text);
    }

    public void handleImageComp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleTextComp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleSlideComp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleVideoComp() {

    }

    public void handleRemoveComp(Component comp) {
        pe.getPage().getComponents().remove(comp);
        pe.updatePage();
    }
    
    
    
    
}
