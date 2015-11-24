/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.model.Portfolio;
import epg.view.PortfolioView;

/**
 *
 * @author cgmp
 */
public class FileController {

    PortfolioView pv;
    
    
    public FileController(PortfolioView pv)
    {
        this.pv = pv;
    }

    public void handleNew() {
        pv.changePortfolio(new Portfolio());
    }

    public void handleLoad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleSave() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleExport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void handleExit() {
        System.exit(0);
    }

    public void handleSaveAs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
