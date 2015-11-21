/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.ProgramConstants.COLOR;
import epg.ProgramConstants.LAYOUT;
import epg.model.Component;
import epg.model.ImageComponent;
import epg.model.Page;
import epg.model.VideoComponent;
import epg.prompts.ImagePrompt;
import epg.prompts.TextAndImageDialog;
import epg.prompts.VideoPrompt;
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
        TextAndImageDialog prompt = new TextAndImageDialog(pe.getPrimaryStage(), pe.getPage().getFooterURL(), pe.getPage().getFooter(), pe.getPage().getFooterText());
        if(prompt.isOk())
        {
        String[] data = prompt.getSelection();
        Page current = pe.getPage();
        current.setFooterURL(data[0]);
        current.setFooter(data[1]);
        current.setFooterText(data[2]);
        }
    }

    public void handleBannerChange() {
        TextAndImageDialog prompt = new TextAndImageDialog(pe.getPrimaryStage(), pe.getPage().getBannerURL(), pe.getPage().getBanner(), pe.getPage().getBannerText());
        if(prompt.isOk())
        {
        String[] data = prompt.getSelection();
        Page current = pe.getPage();
        current.setBannerURL(data[0]);
        current.setBanner(data[1]);
        current.setBannerText(data[2]);
        }
    }

    public void handleTitleChange(String text) {
        pe.getPage().setTitle(text);
        pe.updateTitle();
    }

    public void handleImageComp() {
        ImageComponent comp = new ImageComponent();
        ImagePrompt popup = new ImagePrompt(pe.getPrimaryStage(), comp);
        if(popup.isOk())
        {
            pe.getPage().getComponents().add(comp);
            pe.updatePage();    
        }
    }

    public void handleTextComp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleSlideComp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleVideoComp() {
        VideoComponent comp = new VideoComponent();
        VideoPrompt popup = new VideoPrompt(pe.getPrimaryStage(), comp);
        if(popup.isOk())
        {
            pe.getPage().getComponents().add(comp);
            pe.updatePage();
        }
    }

    public void handleRemoveComp(Component comp) {
        pe.getPage().getComponents().remove(comp);
        pe.updatePage();
    }
    
    
    
    
}
