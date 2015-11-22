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
    
    //ICONS
    public static String PATH_ICONS = "./appdata/icons/";
    public static String ICON_ARROW_DOWN = PATH_ICONS + "arrow-down.png";
    public static String ICON_ARROW_UP = PATH_ICONS + "arrow-up.png";
    public static String ICON_BOOK_OPEN = PATH_ICONS + "book-open.png";
    public static String ICON_FLOPPYDISK = PATH_ICONS + "floppydisk.png";
    public static String ICON_FOLDER_PLUS = PATH_ICONS + "folder-plus.png";
    public static String ICON_PAGE_ARROW = PATH_ICONS + "page-arrow.png";
    public static String ICON_X = PATH_ICONS + "x.png";
    public static String ICON_MINUS = PATH_ICONS + "minus.png";
    public static String ICON_BANNER = PATH_ICONS + "banner.png";
    public static String ICON_PLUS = PATH_ICONS + "plus.png";
    public static String ICON_CHECK = PATH_ICONS + "check.png";
    public static String ICON_BOARD = PATH_ICONS + "board.png";
    public static String ICON_VIDEO = PATH_ICONS + "video.png";
    public static String ICON_PICTURE = PATH_ICONS + "picture.png";
    public static String ICON_TEXT_PLUS = PATH_ICONS + "t-plus.png";
    public static String ICON_CIRCLE_X = PATH_ICONS + "circle-x.png";
    
    

    //TOOLTIPS
    public static String TT_FILE_NEW = "Open a new Portfolio.";
    public static String TT_FILE_LOAD = "Load a new Portfolio.";
    public static String TT_FILE_SAVE = "Save this Portfolio.";
    public static String TT_FILE_SAVEAS = "Save this Portfolio to a different file.";
    public static String TT_FILE_EXPORT = "Export this Portfolio to a website.";
    public static String TT_FILE_EXIT = "Exit this application.";
    public static String TT_MODE_SITE = "Switch to view mode.";
    public static String TT_MODE_EDIT = "Switch to edit mode.";
    public static String TT_ST_NAME = "Change the Student Name.";
    public static String TT_ST_PAGE = "Change the current Page to edit.";
    public static String TT_ST_ADD = "Add a page.";
    public static String TT_ST_REMOVE = "Remove a page.";
    
    
    
    
    
    
}
