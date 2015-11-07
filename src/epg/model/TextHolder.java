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
public class TextHolder {
    
        
    ArrayList<int[]> linksloc;
    ArrayList<String> linkURLs;
    String text;

    public TextHolder(ArrayList<int[]> links, String text, ArrayList<String> linkURLs) {
        this.linksloc = links;
        this.text = text;
        this.linkURLs = linkURLs;
    }
    
    
    
    
    public void addLink(int start, int finish, String url)
    {
        int[] loc = {start,finish};
        linksloc.add(loc);
        linkURLs.add(url);
    }
    
    
    
}


