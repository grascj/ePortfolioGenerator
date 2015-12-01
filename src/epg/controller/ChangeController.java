/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.view.PortfolioView;

/**
 *
 * @author cgmp
 */
public class ChangeController {
    
    static PortfolioView pv;
    static ChangeController cc;
    
    private ChangeController(){}
    
    public static ChangeController getChangeController()
    {
        return cc;
    }
    
    public static void initChangeController(PortfolioView pv)
    {
        ChangeController.pv = pv;
    }
    
    public static void wasChanged()
    {
        pv.wasChanged();
        pv.updateFileToolbar();
    }
    
    
    
}
