
package oec;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

//augusto de arruda kono RA 20760841
//Larissa Fontes Naoe RA 20854181
//Arthur Montoya  RA 20761385

public class server {

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanint = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);

        
        System.out.println("Digite o socket");
        int socket = scanint.nextInt();
        
        System.out.println("Digite o nome da database");
        String database = scan.nextLine();
        
        Startup start = new Startup(database);
        start.Create();
        
        String resp = "nadahhh";
        ServerSocket srv = null;
        Socket cliente = null;
        Scanner in = null;
        PrintStream out = null;
        
        //istanciar o socket servidor
        try {
            srv = new ServerSocket(socket); //porta que eu peço pro SO
            System.out.println("Servidor disponivel na porta 9876");
        } catch (Exception e) {
            System.out.println("Erro ao abrir a porta 9876");
            System.out.println(e.getMessage());
            return;
        }
        
        while(true){
            //atender o pedido de conexão
            try{
               System.out.println("Aguardando a conexão de um cliente...");
               cliente = srv.accept(); //retorna um socket declarado, no nosso caso cliente
               System.out.println("Conectado com " + cliente.getInetAddress().getHostAddress());// mostro com quem foi feita a conexão

            }catch (Exception e){
                System.out.println("Erro ao conectar com o cliente");
                System.out.println(e.getMessage());
                return;
            }
            PrintStream saida = new PrintStream(cliente.getOutputStream());

            //Comunicação
            try{
                in = new Scanner(cliente.getInputStream()); //o que meu scanner vai ler
                String msg = in.nextLine();
                String array[] = new String[5];
                array = msg.split(",");
                Sqlcommands comando = new Sqlcommands(database);


                if(array[0].equals("select")){
                    System.out.println("select iniciado");
                    resp = comando.select(msg);
                    saida.println(resp);
                }else if(array[0].equals("insert")){
                    System.out.println("insert iniciado");
                    resp = comando.insert(msg);
                    saida.println(resp);
                }else if(array[0].equals("delete")){
                    System.out.println("delete iniciado");
                    resp = comando.delete(array[1]);
                    saida.println(resp);
                }else if(array[0].equals("update")){
                    System.out.println("update iniciado");
                    resp = comando.update(msg);
                    saida.println(resp);
                }else{
                    saida.println("comando mal definido");
                }
                                
                System.out.println("Recebido: " + msg);

            }catch (Exception e){
                System.out.println("Erro na troca de mensagens.");
                System.out.println(e.getMessage());
                saida.println(e.getMessage());
                return;
            }


            //Encerrar comunicação
            try{
                cliente.close();
            }catch (Exception e){
                System.out.println("Erro ao encerrar a comunicação.");
                System.out.println("e.getMessage()");

            }
        }
        
    }
    
}
