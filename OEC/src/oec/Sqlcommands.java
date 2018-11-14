/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oec;

import java.sql.SQLException;

/**
 *
 * @author augusto
 */
public class Sqlcommands {
    String database;
    public Sqlcommands(String database) {
        this.database = database;
    }
    
    public String update(String pedido) throws SQLException{
        dbConn banco = new dbConn();
        String sql = "";
        String Arraypedido[] = new String[5];
        Arraypedido = pedido.split(",");
        
        sql = "UPDATE Musicas " +
                    "SET musica= '"+ Arraypedido[1] +"', album= '"+ Arraypedido[2]+"',artista= '"+ Arraypedido[3]+
                    "' WHERE id= '"+ Arraypedido[4]+"';";
        System.out.println("query criada "+sql );
        
        String resp = banco.executa(sql,false,database);
        
        System.out.println("resposta "+resp);
        
        return resp;
    }
    
    public String select(String pedido) throws SQLException{
        String sql = "";
        dbConn banco = new dbConn();

        String Arraypedido[] = new String[5];
        Arraypedido = pedido.split(",");
        
        if(!Arraypedido[0].equals(" ")){
            sql = "SELECT * from Musicas" +
                    " WHERE musica= '"+ Arraypedido[1]+"';";
            
        }else if(!Arraypedido[1].equals(" ")){
            sql = "SELECT * from Musicas" +
                    " WHERE album= '"+ Arraypedido[2]+"';";
            
        }else if(!Arraypedido[2].equals(" ")){
            sql = "SELECT * from Musicas" +
                    " WHERE artista= '"+ Arraypedido[3]+"';";
        }else{
            sql = "SELECT * from Musicas;";
            
        }
        System.out.println("query criada "+sql );

        
        String resp = banco.executa(sql,true,database);
        
        System.out.println("resposta "+resp);

        return resp;
    }
    public String delete(String pedido) throws SQLException{
        String sql = "";
        dbConn banco = new dbConn();


        sql = "delete from Musicas WHERE id= '"+ pedido +"';";
        System.out.println("query criada "+sql );
        
        String resp = banco.executa(sql,false,database);
        
        System.out.println("resposta "+resp);

        return resp;
    }
    public String insert(String pedido) throws SQLException{
        String sql = "";
        dbConn banco = new dbConn();

        String Arraypedido[] = new String[5];
        Arraypedido = pedido.split(",");
        
        sql = "INSERT INTO Musicas(musica,album,artista) VALUES"
                + "('"+Arraypedido[1]+"','"+Arraypedido[2]+"','"+Arraypedido[3]+"');";
        System.out.println("query criada "+sql );
        
        String resp = banco.executa(sql,false,database);

        System.out.println("resposta "+resp);
        
        return resp;
    }

}
