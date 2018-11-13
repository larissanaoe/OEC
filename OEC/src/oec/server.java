
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
        Startup start = new Startup();
        start.Create();
        
        ServerSocket srv = null;
        Socket cliente = null;
        Scanner in = null;
        PrintStream out = null;
        
        while(true){
        //istanciar o socket servidor
            try {
                srv = new ServerSocket(9876); //porta que eu peço pro SO
                System.out.println("Servidor disponivel na porta 9876");
            } catch (Exception e) {
                System.out.println("Erro ao abrir a porta 9876");
                System.out.println(e.getMessage());
                return;
            }

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
                Sqlcommands comando = new Sqlcommands();

                if(array[0].equals("select")){
                    comando.select(msg);
                }else if(array[0].equals("insert")){
                    comando.insert(msg);

                }else if(array[0].equals("delete")){
                    comando.delete(array[1]);

                }else if(array[0].equals("update")){
                    comando.update(msg);

                }else{
                    System.out.println("comando mal definido");
                }

                System.out.println("Recebido: " + msg);

            }catch (Exception e){
                System.out.println("Erro na troca de mensagens.");
                System.out.println(e.getMessage());
                return;
            }


            //Encerrar comunicação
            try{
                cliente.close();
    //            srv.close();
            }catch (Exception e){
                System.out.println("Erro ao encerrar a comunicação.");
                System.out.println("e.getMessage()");
                return;

            }
        }
        
    }
    
}
