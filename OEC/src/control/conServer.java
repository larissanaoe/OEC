/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author lab803
 */
public class conServer extends Thread {
    public void run(){
        DatagramSocket cliente; 
        DatagramPacket pacote; 
        InetAddress ender;
        Scanner texto;
        @SuppressWarnings("UnusedAssignment")
        byte[] dados = new byte[1024];
        
        try {
            ender = InetAddress.getByName("127.0.0.1");
            String msg = "";
            while(!msg.equals("exit")){
                texto = new Scanner(System.in);
                msg = texto.nextLine();

                dados = msg.getBytes();
                pacote = new DatagramPacket(dados, dados.length, ender, 4448);
                cliente = new DatagramSocket();
                cliente.send(pacote);
                
                cliente.receive(pacote);
                msg = new String(pacote.getData());
                msg = msg.trim();
                System.out.println("Recebido: " + msg);
            }
            
            
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
