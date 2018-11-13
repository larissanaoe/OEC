
package control;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;



public class conServer extends Thread {
    String operation; 
    int Socket;
    public void run(){
            
        Scanner texto;
        Socket cliente; 
        
        try {

            cliente = new Socket("127.0.0.1", Socket);
            PrintStream saida = new PrintStream(cliente.getOutputStream());
                
            saida.println(operation);
                
            
            
            
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getSocket() {
        return Socket;
    }

    public void setSocket(int Socket) {
        this.Socket = Socket;
    }
}
