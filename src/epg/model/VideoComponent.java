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
public class VideoComponent extends Component{
    
    String videoURL;
    String file;
    
    public VideoComponent(int width, int length, String videoURL, String video) {
        super(width, length);
        this.videoURL = videoURL;
        this.file = video;
    }

    public VideoComponent() {
        super(200,200);
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public String getFile() {
        return file;
    }

    public void setFile(String video) {
        this.file = video;
    }

    
    
    @Override
    public void editPrompt() {
            //prompt to edit
    }

    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String htmlify() {
        return HTMLWorker.generateVideoComponentHTML(this);
    }
    
    @Override
    public String getDisplayText() {
        return "A Video which is: " + width + "px wide and " + length + "px tall. The file name is: " + file;
    }    
    
}
