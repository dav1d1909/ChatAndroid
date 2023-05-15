package chatJava;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ObjetoCompartido {

    ArrayList<Socket> arrayReceptores;

    public ObjetoCompartido() {
        arrayReceptores = new ArrayList<Socket>();
    }

    public synchronized void enviarMensaje(Mensaje m) {
        ObjectOutputStream ous = null;

        for (Socket receptor : arrayReceptores) {
            try {
                ous = new ObjectOutputStream(receptor.getOutputStream());
                ous.writeObject(m);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }

    }

    public synchronized void addReceptor(Socket s) {
        arrayReceptores.add(s);
    }

    public synchronized void removeReceptor(Socket s) {
        arrayReceptores.remove(s);
    }

}
