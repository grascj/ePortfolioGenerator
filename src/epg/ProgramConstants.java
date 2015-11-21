/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg;

/**
 *
 * @author cgmp
 */
public class ProgramConstants {
    
    public enum FONT        {Fjalla_One, Bree_Serif, Muli, Vollkorn, Iconsolata};
    public enum LAYOUT      {lownav, sidenav, gaps, topnav, fixedname};
    public enum COLOR       {beach, campfire, personal, SBUred, vintage};
    public enum TEXT_TYPE   {HEADER, PARAGRAPH, LIST};

    //Defaults
    public static String DEFAULT_SLIDEIMG = "./appdata/default_images/DefaultStartSlide.png";
    
    //UI text and such
    public static String OKAY = "OK";
    public static String NEWIMAGE = "Pick an image.";
    public static String NEWVIDEO = "Pick a video.";
   
    //Paths
    public static String PATH_SAVES = "./saves";
    public static String PATH_SITES = "./sites";
    public static String PATH_STYLESHEET = "epg/style/AppStyle.css";
    public static String PATH_PROMPTSTYLESHEET = "epg/style/PromptStyle.css";

    
    
    //CSS STYLES FOR APPLICATION
    public static String CSS_FILETOOLBAR = "filetoolbar";
    public static String CSS_FILETOOLBAR_BUTTONS = "filetoolbar_buttons";
    public static String CSS_MODETOOLBAR = "modetoolbar";
    public static String CSS_MODETOOLBAR_BUTTONS = "modetoolbar_buttons";
    public static String CSS_SITEVIEW = "siteview";
    public static String CSS_SITEVIEW_WEBVIEW = "siteview_webview";
    public static String CSS_PAGEEDITOR = "pageeditor";
    
    public static String CSS_SITETOOLBAR = "sitetoolbar";
    
    public static String CSS_COMPONENTPANE = "componentpane";
    
    public static String CSS_COMPONENTVIEW = "componentview";
    public static String CSS_COMPONENTVIEW_SELECTED = "componentview_selected";
    public static String CSS_COMPONENTVIEW_BUTTON = "componentview_button";
    public static String CSS_COMPONENTVIEW_LABEL = "componentview_label";
    
    public static String CSS_PAGEVIEW = "pageview";
    public static String CSS_PAGEVIEW_TOPBAR = "pageview_topbar";
    public static String CSS_PAGEVIEW_SIDEBAR_BUTTONS = "pageview_sidebar_buttons";
    
   
    //CSS STYLES FOR PROMPTS
    public static String CSS_SLIDE = "slide";
    public static String CSS_SLIDE_SELECTED = "slide_selected";
    
    
    //@todo tooltips
    
}
