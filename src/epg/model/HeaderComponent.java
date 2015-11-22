/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.FONT;


/**
 *
 * @author cgmp
 */
public class HeaderComponent extends TextComponent{

    String text;
    
    public HeaderComponent(FONT font, int fontSize, String text) {
        super(font, fontSize);
        this.text = text;
    }
    
    public HeaderComponent()
    {
        super(FONT.Bree_Serif, 16);
        this.text = "";
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
