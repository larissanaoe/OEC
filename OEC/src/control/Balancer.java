package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

//augusto de arruda kono RA 20760841
//Larissa Fontes Naoe RA 20854181
//Arthur Montoya  RA 20761385

public class Balancer {

    public static void main(String[] args) throws IOException {
        int i = 0;
        ArrayList<Socket> servers = new ArrayList<Socket>();
        
        Scanner scan = new Scanner(System.in);
        Scanner scanint = new Scanner(System.in);

        for(int j = 0; j<3;j++){
            System.out.println("Digite o socket numero "+(j+1));
            System.out.println("Digite o endereço");
            String address = scan.nextLine();
            if (address.equals("local"))
                address = "127.0.0.1";

            System.out.println("Digite o socket");
            int socket = scanint.nextInt();

            servers.add(new Socket(address ,socket));
        }
        
        ServerSocket server;
        Socket cli;
        System.out.println("Digite o port deste server");
        int socket = scanint.nextInt();
        
        
        try {
            server = new ServerSocket(socket);
            while(true){
                cli = server.accept();
                System.out.println("Conectado com " + cli.getInetAddress().getHostAddress());
                
                Atende at = new Atende(cli,servers.get(i));
                at.start();
                
                if(i == 3){
                    i = 0;
                }else{
                    i++;
                }

            }
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        
    }
    
}
