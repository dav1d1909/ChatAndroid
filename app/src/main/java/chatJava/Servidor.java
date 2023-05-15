package chatJava;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Servidor {
    public static void main(String[] args) {
        ObjetoCompartido oc = new ObjetoCompartido();
        
        ServidorEmisores se = new ServidorEmisores(oc);
        ServidorReceptores sr = new ServidorReceptores(oc);
        
        se.start();
        sr.start();
        
    }
}
