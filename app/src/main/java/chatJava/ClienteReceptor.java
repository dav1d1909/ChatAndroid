package chatJava;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.ObjectInputStream;
import java.net.Socket;

public class ClienteReceptor {
    public static void main(String[] args) {
        Mensaje m;
        ObjectInputStream ois = null;
        try (Socket s = new Socket("localhost", 6000);) {
            do {
                ois = new ObjectInputStream(s.getInputStream());
                m = (Mensaje) ois.readObject();
                System.out.println(m.getMensaje());
            } while (true);
        } catch (Exception ex) {
            System.out.println("Se ha perdido la conexión con el servidor");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}