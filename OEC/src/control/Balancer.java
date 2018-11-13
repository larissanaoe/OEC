
package control;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Balancer {

    public static void main(String[] args) {
        int i = 0;
        ArrayList servers = new ArrayList();
        servers.add(9876);
        servers.add(9875);
        servers.add(9874);
        
        ServerSocket server;
        Socket cli;
        
        try {
            server = new ServerSocket(9600);
            while(true){
                cli = server.accept();
                System.out.println("Conectado com " + cli.getInetAddress().getHostAddress());
                Atende at = new Atende(cli);
                at.start();
            }
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        
        
        
        while(i < 3){
            conServer coneta = new conServer(); 
            coneta.setSocket((int) servers.get(i));
            coneta.setOperation(operation);
            
        }
        
    }
    
}
