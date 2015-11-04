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
public class Portfolio {
    String studentName;
    ArrayList<Page> pages;
    int numPages;
    String fileName;
    
    
    public Portfolio()
    {
        studentName = "";
        pages = new ArrayList<Page>();
        numPages = 1;
        fileName = "default title";
    }
    public Portfolio(String studentName, ArrayList<Page> pages, int numPages, String fileName)
    {
        this.studentName = studentName;
        this.pages = pages;
        this.numPages = numPages;
        this.fileName = fileName;
    }
    
    
    //@TODO implement Portfolio methods
    public void deletePage(Page pageToDelete)
    {
        
    }
    
    public void addPage(Page pageToAdd)
    {
        
    }
    
    public void reset()
    {
        
    }
    
    public Page findPageByTitle(String title)
    {
        return null;
    }
    
    
    
}
