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
    
    public static enum FONT        {Fjalla_One, Bree_Serif, Muli, Vollkorn, Iconsolata};
    public static enum LAYOUT      {lownav, sidenav, gaps, topnav, fixedname};
    public static enum COLOR       {beach, campfire, personal, SBUred, vintage};

    public static enum COMPONENTS  {HEADER, IMAGE, LIST, PARAGRAPH, SLIDESHOW, VIDEO};
    
    
    
    
    //Defaults
    public static String DEFAULT_IMG = "./appdata/default_images/DefaultStartSlide.png";
    
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
    public static String CSS_SITETOOLBAR_CHILD = "sitetoolbar_child";
    public static String CSS_SITETOOLBAR_NAME = "sitetoolbar_name";
    public static String CSS_SITETOOLBAR_NAME_CHILD = "sitetoolbar_name_child";
    
    public static String CSS_COMPONENTPANE = "componentpane";
    public static String CSS_COMP_SCROLLPANE = "comp_scrollpane";
    
    public static String CSS_COMPONENTVIEW = "componentview";
    public static String CSS_COMPONENTVIEW_SELECTED = "componentview_selected";
    public static String CSS_COMPONENTVIEW_BUTTON = "componentview_button";
    public static String CSS_COMPONENTVIEW_LABEL = "componentview_label";
    
    public static String CSS_PAGEVIEW = "pageview";
    public static String CSS_PAGEVIEW_TOPBAR = "pageview_topbar";
    public static String CSS_PAGEVIEW_TOPBAR_CHILD = "pageview_topbar_child";
    public static String CSS_PAGEVIEW_SIDEBAR_BUTTONS = "pageview_sidebar_buttons";
    
   
    //CSS STYLES FOR PROMPTS
    public static String CSS_SLIDE = "slide";
    public static String CSS_SLIDE_SELECTED = "slide_selected";
    public static String CSS_SLIDESHOW_BUTTON = "slideshow_button";
    public static String CSS_TEXT_BUTTON = "text_button";
    public static String CSS_LIST_BUTTON = "list_button";
    public static String CSS_OK_BUTTON = "ok_button";
    public static String CSS_CHOOSE_BUTTON = "choose_button";
    
    public static String CSS_PROMPT_BUTTON = "prompt_button";
    public static String CSS_CONTAINER = "container";
    
    public static String CSS_BANNER = "banner";
    public static String CSS_BANNER_IMAGE = "banner_image";
    
    public static String CSS_PROMPT_IMAGE = "prompt_image";
    
    
    
    public static String CSS_BUTTON_CONTAINER = "button_container"; 
    
    
    
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
    public static String ICON_EYE = PATH_ICONS + "view.png";
    public static String ICON_EDIT = PATH_ICONS + "edit.png";
    public static String ICON_HEADER = PATH_ICONS + "header.png";
    public static String ICON_LIST = PATH_ICONS + "list.png";
    public static String ICON_PARAGRAPH = PATH_ICONS + "paragraph.png";
    public static String ICON_CHOOSE = PATH_ICONS + "choose.png";
    public static String ICON_FOOTER = PATH_ICONS + "footer.png";
    public static String ICON_SAVEAS = PATH_ICONS + "save-as.png";

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
    public static String TT_COMP_IMAGE = "Add an image component";
    public static String TT_COMP_TEXT = "Add a text component";
    public static String TT_COMP_VIDEO = "Add a video component";
    public static String TT_COMP_SS = "Add a slide show component";
    public static String TT_COMP_REMOVE = "Remove the selected component";
    
    public static String TT_FONT = "Select a page font.";
    public static String TT_LAYOUT = "Select a page layout.";
    public static String TT_COLOR = "Select a page color scheme.";
    
    
    
    
    public static String TT_PV_BANNER = "Edit the page banner.";
    public static String TT_PV_FOOTER = "Edit the page footer.";
    
    
    //  prompt TOOLTIPS
    public static String TT_TEXT_PARA = "Add a paragraph.";
    public static String TT_TEXT_HEAD = "Add a header.";
    public static String TT_TEXT_LIST = "Add a list.";
    public static String TT_SS_UP = "Move the selected slide up.";
    public static String TT_SS_DOWN = "Move the selected slide down.";
    public static String TT_SS_RM = "Remove the selected slide.";
    public static String TT_SS_ADD = "Add a slide.";
    public static String TT_LIST_ADD = "Add an item.";
    public static String TT_LIST_RM = "Remove an item.";
    public static String TT_OK = "Done editing.";
    public static String TT_CHOOSE = "Choose File.";
    
    
    
}
