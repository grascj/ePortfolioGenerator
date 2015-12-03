/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epg.error;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author cgmp
 */
public class ErrorHandler {

    public ErrorHandler() {

    }

    public static EventHandler<KeyEvent> getNumberKeyEventHandler() {
        EventHandler<KeyEvent> key = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                char ar[] = event.getCharacter().toCharArray();
                if (ar.length > 0) {
                    char ch = ar[event.getCharacter().toCharArray().length - 1];
                    if (!(ch >= '0' && ch <= '9')) {
                        event.consume();
                    }
                }
            }
        };

        return key;
    }

}
