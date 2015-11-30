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
    String fileName;
    
    
    
    //CONSTRUCTORS
    public Portfolio()
    {
        studentName = "";
        pages = new ArrayList<Page>();
        pages.add(new Page());
        fileName = "";
    }
    public Portfolio(String studentName, ArrayList<Page> pages, String fileName)
    {
        this.studentName = studentName;
        this.pages = pages;
        this.fileName = fileName;
    }

    
    
    //GETTERS AND SETTERS
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }



    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStudentName() {
        return studentName;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }



    public String getFileName() {
        return fileName;
    }   
    
}
