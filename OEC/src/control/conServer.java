
package control;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class conServer {
    String operation; 
    Socket Socket;
    Scanner in;
    PrintStream saida;
    String msg;

    public conServer(String operation, Socket Socket) {
        this.operation = operation;
        this.Socket = Socket;
    }
    
    
    public String Connect(){
            
        Scanner texto;
        
        try {
            saida = new PrintStream(Socket.getOutputStream());
                
            saida.println(operation);
            
            in = new Scanner(Socket.getInputStream());
            
            msg = in.nextLine();
            System.out.println("Recebido: " + msg + 
                        " de "+Socket.getInetAddress().getHostAddress());
            saida.println("recebido.");
            
            
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return msg;
    }
}
