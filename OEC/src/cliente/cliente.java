
package cliente;

import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class cliente {

    
    public static void main(String[] args) {
        
        int option =0;
        Socket cliente; 
        Scanner texto;
        @SuppressWarnings("UnusedAssignment")
        byte[] dados = new byte[1024];
        
        String operation = "nadahhh";
        
        System.out.println(" _  _  ____  __ _  _  _    ____  ____  __  __ _   ___  __  ____   __   __   ");
        System.out.println("( \\/ )(  __)(  ( \\/ )( \\  (  _ \\(  _ \\(  )(  ( \\ / __)(  )(  _ \\ / _\\ (  )  ");
        System.out.println("/ \\/ \\ ) _) /    /) \\/ (   ) __/ )   / )( /    /( (__  )(  ) __//    \\/ (_/\\");
        System.out.println("\\_)(_/(____)\\_)__)\\____/  (__)  (__\\_)(__)\\_)__) \\___)(__)(__)  \\_/\\_/\\____/");
        
        while(option!=5){
        System.out.println("1 - Adicionar TAG");
        System.out.println("2 - Atualizar TAG");
        System.out.println("3 - Deletar TAG");
        System.out.println("4 - Buscar TAG");
        System.out.println("5 - SAIR");
        
        System.out.println("DIGITE A OPÇÃO DESEJADA: ");
        
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();
        
        switch(option){
            case 1:
                System.out.println("Digite o nome da música: ");
                Scanner in = new Scanner(System.in);
                String nomeMusica = scanner.nextLine();
                
                System.out.println("Digite o nome do álbum: ");
                String nomeAlbum = scanner.nextLine();
                
                System.out.println("Digite o nome do artista: ");
                String nomeArtista = scanner.nextLine();
                
                operation = "insert," + nomeMusica + "," + nomeAlbum + "," + nomeArtista;
                
                
                break;
                
            case 2:
                System.out.println("Digite a ID da TAG: ");
                String upID = scanner.nextLine();
                
                System.out.println("Digite o nome da música para atualizar: ");
                
                String upMusica = scanner.nextLine();
                
                System.out.println("Digite o nome do álbum para atualizar: ");
                String upAlbum = scanner.nextLine();
                
                System.out.println("Digite o nome do artista para atualizar: ");
                String upArtista = scanner.nextLine();
                
                operation = "update," + upMusica + "," + upAlbum + "," + upArtista + "," + upID;
                
                break;
                
            case 3:
                System.out.println("Digite a ID da TAG que deseja deletar: ");
                String delID = scanner.nextLine();
                
                operation = "delete," + delID;
                
                break;
                
            case 4:
                System.out.println("-----MENU BUSCA-----");
                System.out.println("1 - Buscar por nome de música");
                System.out.println("2 - Buscar por nome do álbum");
                System.out.println("3 - Buscar por nome do artista");
                System.out.println("Digite a opcao desejada: ");
                int optionSearch = scanner.nextInt();
                
                switch(optionSearch){
                    case 1: 
                        System.out.println("Digite o nome da música para buscar: ");
                        String buscaNomeMusica = scanner.nextLine();
                        
                        operation = "select," + buscaNomeMusica + ", , ";
                        
                        break;
                        
                    case 2:
                        System.out.println("Digite o nome do álbum para buscar: ");
                        String buscaNomeAlbum = scanner.nextLine();
                        
                        operation = "select, ," + buscaNomeAlbum + ", ";
                        break;
                        
                    case 3:
                        System.out.println("Digite o nome do artista para buscar: ");
                        String buscaNomeArtista = scanner.nextLine();
                        
                        operation = "select, , ," + buscaNomeArtista;
                        break;
                        
                    default:
                        System.out.println("DIGITE UM NÚMERO VÁLIDO");
                        break;
                }
                
                break;
                
            case 5:
                System.out.println(" Até logo, até mais ver, bon voyage, arrivederci, até mais, adeus, boa viagem, vá em paz, que a porta bata onde o sol não bate, não volte mais aqui, hasta la vista baby, escafeda-se, e saia logo daqui. ");
                break;
                
            default:
                break;
                
    }
        try {
            cliente = new Socket("127.0.0.1",3322);
            PrintStream saida = new PrintStream(cliente.getOutputStream());
                
            saida.println(operation);

        } 
            catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            
            }
            
        }
        
    }
    
}
