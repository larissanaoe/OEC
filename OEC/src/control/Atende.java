package control;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Atende extends Thread{
    Socket cli;
    Socket server;
    String resposta = "";
    
    public Atende(Socket cli, Socket server){
        this.cli = cli;
        this.server = server;
    }

    public String getResposta() {
        return resposta;
    }
    
    @Override
    public void run(){
        Scanner in;
        PrintStream out;
        String msg = "";
        
        try {
            in = new Scanner(cli.getInputStream());
            out = new PrintStream(cli.getOutputStream());
            msg = in.nextLine();
            System.out.println("Recebido: " + msg + 
                        " de "+cli.getInetAddress().getHostAddress());
            
            
            conServer connection = new conServer(msg, server);
            
            resposta = connection.Connect();
            out.println("recebido.");
            
            out.println(resposta);
            
            System.out.println("Encerrou com "+ 
                    cli.getInetAddress().getHostAddress());
            cli.close();
        } catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
        }
    }
    
}