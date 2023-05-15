package chatJava;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEmisor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = "";
        Mensaje m;
        ObjectOutputStream ob = null;
        try (Socket s = new Socket("localhost", 6001);) {
            do {
                texto = sc.nextLine();
                m = new Mensaje(texto);
                ob = new ObjectOutputStream(s.getOutputStream());
                ob.writeObject(m);
            } while (!texto.equals("*"));
            System.out.println("Has abandonado el servidor... Adios!!");
        } catch (Exception ex) {
            System.out.println("Se ha perdido la conexión con el servidor");
        } finally {
            if (ob != null) {
                try {
                    ob.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
