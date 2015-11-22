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
public class ListComponent extends TextComponent {

    ArrayList<String> listItems;

    public ListComponent(FONT font, int fontSize, ArrayList<String> listItems) {
        super(font, fontSize);
        this.listItems = listItems;
    }

    public ListComponent() {
        super(FONT.Bree_Serif, 12);
        listItems = new ArrayList<String>();
    }

    public ArrayList<String> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<String> listItems) {
        this.listItems = listItems;
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
