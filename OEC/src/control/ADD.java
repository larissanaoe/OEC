/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author lab803
 */
public class ADD {
    public void run(){
        Socket cliente = null; 
        PrintStream out = null;
        Scanner texto;
        
        
        try {
            cliente = new Socket("127.0.0.1", 9000);
            System.out.println("conectando");
        }catch(Exception e){
            System.out.println("Erro na conexão");
            System.out.println(e.getMessage());
        }
        
            try{
                
            String msg = "";
                texto = new Scanner(System.in);
                msg = texto.nextLine();
                
                out = new PrintStream(cliente.getOutputStream());
                out.println("");
                
                
                //while(!msg.)
                

                msg = msg.trim();
                System.out.println("Mensagem adicionada: " + msg);
            
            
            
            
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
    }
}
