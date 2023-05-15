package chatJava;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorEmisores extends Thread{

    ObjetoCompartido oc;

    public ServidorEmisores(ObjetoCompartido oc) {
        this.oc = oc;
    }
    
    public void run() {
        Socket se = null;
        HiloServidorEmisores hs = null;
        try (ServerSocket ss = new ServerSocket(6001)){
            do {                
                se = ss.accept();
                hs = new HiloServidorEmisores(se,this.oc);              
                hs.start();
                
            } while (true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
