/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.FONT;
import java.util.ArrayList;


/**
 *
 * @author cgmp
 */
public class ParagraphComponent extends TextComponent{
    
    
    ArrayList<Hyperlink> links;
    String text;
            
    
    
    
    public ParagraphComponent(FONT font, int fontSize, ArrayList<Hyperlink> links, String text) {
        super(font, fontSize);
        this.links = links;
        this.text = text; 
    }
    
    public ParagraphComponent()
    {
        super(FONT.Bree_Serif, 12);
        this.links = new ArrayList<Hyperlink>();
        this.text = "";
    }

    public ArrayList<Hyperlink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Hyperlink> links) {
        this.links = links;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    
    
    @Override
    public void editPrompt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String htmlify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
