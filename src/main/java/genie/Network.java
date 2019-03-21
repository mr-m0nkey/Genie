/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genie;

import java.net.Socket;

/**
 * Handles the connection
 * @author mayowa
 */
public class Network {
    
    private static Socket socket;
    private static String host = "127.0.0.1";
    private static int port = 8000;
    
    private Network() {
        
    }
    
    /*public static Socket getSocket() {
        if(socket == null) {
            socket = new Socket(host, port);
        }
        return socket;
    }*/
    
 
    
}
