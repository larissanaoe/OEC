
package oec;

import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class server {

    public static void main(String[] args) throws SQLException {
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
               System.out.println("Conectado com" + cliente.getInetAddress().getHostAddress());// mostro com quem foi feita a conexão

            }catch (Exception e){
                System.out.println("Erro ao conectar com o cliente");
                System.out.println(e.getMessage());
                return;
            }

            //Comunicação
            try{
                in = new Scanner(cliente.getInputStream()); //o que meu scanner vai ler
                String msg = in.nextLine();
                String array[] = new String[5];
                array = msg.split(",");
                Sqlcommands comando = new Sqlcommands(database);

                if(array[0].equals("select")){
                    resp = comando.select(msg);
                    
                }else if(array[0].equals("insert")){
                    resp = comando.insert(msg);

                }else if(array[0].equals("delete")){
                    resp = comando.delete(array[1]);

                }else if(array[0].equals("update")){
                    resp = comando.update(msg);

                }else{
                    System.out.println("comando mal definido");
                }
                
                PrintStream saida = new PrintStream(cliente.getOutputStream());

                saida.println(resp);
                

                System.out.println("Recebido: " + msg);

            }catch (Exception e){
                System.out.println("Erro na troca de mensagens.");
                System.out.println(e.getMessage());
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
