package chatJava;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorReceptores extends Thread{

    ObjetoCompartido oc;

    public ServidorReceptores(ObjetoCompartido oc) {
        this.oc = oc;
    }
    
    public void run() {
        Socket sr = null;
        HiloReceptor h = null;
        try (ServerSocket ss = new ServerSocket(6000)){
            do {                
                sr = ss.accept();
                oc.addReceptor(sr);
                h = new HiloReceptor(sr,oc);
                h.start();
            } while (true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
