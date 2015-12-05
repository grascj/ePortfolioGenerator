/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import javafx.scene.control.IndexRange;

/**
 *
 * @author cgmp
 */
    public class Hyperlink
    {
        public IndexRange range;
        public String txt;
        public String url;
        public Hyperlink(IndexRange range, String link, String url)
        {
            this.range = range;
            this.txt = link;
            this.url = url;
        }
        
        public Hyperlink copy()
        {
            return new Hyperlink(range, txt, url);
        }
    }
