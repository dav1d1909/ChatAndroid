package chatJava;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HiloServidorEmisores extends Thread{
    
    Socket s;
    ObjetoCompartido oc;

    public HiloServidorEmisores(Socket s, ObjetoCompartido oc) {
        this.s = s;
        this.oc = oc;
    }
    
    public void run(){
        ObjectInputStream ois = null;
        Mensaje m = null;
        try{
            do {                
                ois = new ObjectInputStream(s.getInputStream());
                m = (Mensaje) ois.readObject();
                System.out.println("Recibiendo mensaje: "+m.getMensaje());
                if (!m.getMensaje().equals("*")) {
                    oc.enviarMensaje(m);
                }     
            } while (!m.getMensaje().equals("*"));
            System.out.println("Cierre controlado del emisor");
        } catch (Exception ex) {
            System.out.println("Cierre abrupto del emisor");
        } finally {
            if (ois != null) try {
                ois.close();
            } catch(Exception e){               
            }
            try{
                s.close();
            } catch(Exception e){
            }
        }
        
    }
    
    
    
}
