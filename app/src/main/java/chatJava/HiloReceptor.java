package chatJava;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.ObjectInputStream;
import java.net.Socket;

public class HiloReceptor extends Thread{
    Socket s;
    ObjetoCompartido oc;

    public HiloReceptor(Socket s, ObjetoCompartido oc) {
        this.s = s;
        this.oc = oc;
    }
    
    public void run(){
        ObjectInputStream ois = null;
        try{                 
          ois = new ObjectInputStream(s.getInputStream()); 
        } catch (Exception ex) {
            System.out.println("Cierre abrupto del receptor");
            oc.removeReceptor(s);
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
