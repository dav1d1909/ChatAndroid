package chatJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.crypto.Cipher;


public class Mensaje implements Serializable {
    private byte[] mensaje;
    private static transient GenerarClave keyObj = null;
    
    static {
        try (ObjectInputStream clave = new ObjectInputStream(new FileInputStream(new File("./miClave.key")))){
            keyObj = (GenerarClave) clave.readObject();
        }catch (Exception e){
            System.out.println("Error al recuperar la clave");
        }
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje.getBytes();
        try{
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, keyObj.getClave());
            this.mensaje = c.doFinal(this.mensaje);
        } catch(Exception e){
            System.out.println("Error al cifrar");
        }
    }
    public String getMensaje(){
        byte[] descifrado = this.mensaje;
        try{
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keyObj.getClave());
            descifrado = c.doFinal(this.mensaje);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error al descifrar");
        }
        return new String(descifrado);
    }
    
    
    
    
}
