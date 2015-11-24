/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.ProgramConstants.FONT;
import epg.file.HTMLWorker;
import java.util.ArrayList;

/**
 *
 * @author cgmp
 */
public class ListComponent extends TextComponent {

    
    
    
    ArrayList<Item> listItems;

    public ListComponent(FONT font, int fontSize, ArrayList<Item> listItems) {
        super(font, fontSize);
        this.listItems = listItems;
    }

    public ListComponent() {
        super(FONT.Bree_Serif, 12);
        listItems = new ArrayList<Item>();
    }

    public ArrayList<Item> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Item> listItems) {
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
        return HTMLWorker.generateListComponentHTML(this);
    }

}
