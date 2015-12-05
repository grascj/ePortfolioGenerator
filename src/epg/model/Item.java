/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import java.util.ArrayList;

/**
 *
 * @author cgmp
 */
public class Item {

    public String itemtext;
    public ArrayList<Hyperlink> links;

    public Item(String itemtext, ArrayList<Hyperlink> links) {
        this.itemtext = itemtext;
        this.links = links;
    }
    
    
    public Item copy()
    {
        ArrayList<Hyperlink> nulinks = new ArrayList<>();
        for(Hyperlink a : links)
        {
            nulinks.add(a.copy());
        }
        return new Item(itemtext, nulinks);
    }
}
