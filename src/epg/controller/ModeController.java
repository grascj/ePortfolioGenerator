/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.controller;

import epg.error.ErrorHandler;
import epg.view.PortfolioView;

/**
 *
 * @author cgmp
 */
public class ModeController {

    PortfolioView pv;

    public ModeController(PortfolioView pv) {
        this.pv = pv;
    }

    public void handleSiteMode() {
        pv.viewMode();
    }

    public void handleEditMode() {
        if (ErrorHandler.isValidPortfolio(pv.getPortfolio())) {
            pv.loadSiteView();
            pv.editMode();
        }
    }

}
