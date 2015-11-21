/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.model;

import epg.file.HTMLWorker;

/**
 *
 * @author cgmp
 */
public class ImageComponent extends Component {
    
    String imageURL;
    String file;

    public ImageComponent(int width, int length, String imageURL, String file)
    {
        super(width, length);
        this.imageURL = imageURL;
        this.file = file;
        
    }

    public ImageComponent() {
        super(200,200);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    
    //@TODO implement
    @Override
    public void editPrompt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateImageComponentHTML(this);
    }
    
    
    
    @Override
    public String getDisplayText() {
        return "An Image which is: " + width + "px wide and " + length + "px tall. The image file is:" + file;
    }
    
    
    
    
    
    
}
