/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

/**
 *
 * @author cgmp
 */
    public class Hyperlink
    {
        public int start;
        public int end;
        public String link;
        public Hyperlink(int start, int end, String link)
        {
            this.start = start;
            this.end = end;
            this.link = link;
        }
    }
