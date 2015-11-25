/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.ProgramConstants.COLOR;
import epg.ProgramConstants.COMPONENTS;
import epg.ProgramConstants.FONT;
import epg.ProgramConstants.LAYOUT;
import epg.model.Component;
import epg.model.HeaderComponent;
import epg.model.ImageComponent;
import epg.model.ListComponent;
import epg.model.Page;
import epg.model.ParagraphComponent;
import epg.model.SlideShowComponent;
import epg.model.VideoComponent;
import epg.prompts.BannerPrompt;
import epg.prompts.FooterPrompt;
import epg.prompts.HeaderPrompt;
import epg.prompts.ImagePrompt;
import epg.prompts.ListPrompt;
import epg.prompts.ParagraphPrompt;
import epg.prompts.SlideShowPrompt;
import epg.prompts.TextPrompt;
import epg.prompts.VideoPrompt;
import epg.view.PageEditor;

/**
 *
 * @author cgmp
 */
public class PageViewController {

    PageEditor pe;

    public PageViewController(PageEditor pe) {
        this.pe = pe;
    }

    public void handleColorChange(int index) {
        pe.getPage().setColors(COLOR.values()[index]);
    }

    public void handleLayoutChange(int index) {
        pe.getPage().setLayout(LAYOUT.values()[index]);
    }

    public void handleFontChange(int index) {
        pe.getPage().setFont(FONT.values()[index]);
    }

    public void handleFooterChange() {
        FooterPrompt prompt = new FooterPrompt(pe.getPage().getFooter());
        if (prompt.isOk()) {
            pe.getPage().setFooter(prompt.getData());
        }
    }

    public void handleBannerChange() {
        BannerPrompt prompt = new BannerPrompt(pe.getPrimaryStage(), pe.getPage().getBannerURL(), pe.getPage().getBanner());
        if (prompt.isOk()) {
            String[] data = prompt.getSelection();
            Page current = pe.getPage();
            current.setBannerURL(data[0]);
            current.setBanner(data[1]);
        }
    }

    public void handleTitleChange(String text) {
        pe.getPage().setTitle(text);
        pe.updateTitle();
    }

    public void handleImageComp() {
        ImageComponent comp = new ImageComponent();
        ImagePrompt popup = new ImagePrompt(pe.getPrimaryStage(), comp);
        if (popup.isOk()) {
            pe.getPage().getComponents().add(comp);
            pe.updatePage();
        }
    }

    public void handleTextComp() {
        TextPrompt popup = new TextPrompt();
        if (popup.isOk()) {
            boolean flag = false;
            if (popup.getTextType() == COMPONENTS.HEADER) {
                HeaderPrompt prompt = new HeaderPrompt((HeaderComponent) popup.getComp());
                flag = prompt.isOk();
            } else if (popup.getTextType() == COMPONENTS.PARAGRAPH) {
                ParagraphPrompt prompt = new ParagraphPrompt((ParagraphComponent) popup.getComp());
                flag = prompt.isOk();
            } else if (popup.getTextType() == COMPONENTS.LIST) {
                ListPrompt prompt = new ListPrompt((ListComponent) popup.getComp());
                flag = prompt.isOk();
            }
            if (flag) {
                pe.getPage().getComponents().add(popup.getComp());
                pe.updatePage();
            }
        }
    }

    public void handleSlideComp() {
        SlideShowComponent comp = new SlideShowComponent();
        SlideShowPrompt popup = new SlideShowPrompt(comp);
        if (popup.isOk()) {
            pe.getPage().getComponents().add(comp);
            pe.getPage().getSlideshows().add(comp.getslideshow());
            pe.updatePage();
        }
        
            //@todo add slideshow to the page list of slideshows 


    }

    public void handleVideoComp() {
        VideoComponent comp = new VideoComponent();
        VideoPrompt popup = new VideoPrompt(pe.getPrimaryStage(), comp);
        if (popup.isOk()) {
            pe.getPage().getComponents().add(comp);
            pe.updatePage();
        }
    }

    public void handleRemoveComp(Component comp) {
        pe.getPage().getComponents().remove(comp);
        pe.updatePage();
    }

}
