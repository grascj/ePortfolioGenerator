/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.model.Page;
import epg.view.PageEditor;

/**
 *
 * @author cgmp
 */
public class SiteToolbarController {
    
    PageEditor pe; 
    
    public SiteToolbarController(PageEditor pe)
    {
        this.pe = pe;
    }
    
    public void handleNameChange(String text) {
        pe.getPortfolio().setStudentName(text);
    }

    public void handlePageChange(int index) {
        pe.changePage(index);
    }

    public void handleAddPage() {
        pe.getPortfolio().getPages().add(new Page());
        pe.setSelectedPage(pe.getPortfolio().getPages().size()-1);
        pe.update();
    }
    

    public void handleRemovePage() {
        pe.removeSelectedPage();
        pe.update();
    }
}
