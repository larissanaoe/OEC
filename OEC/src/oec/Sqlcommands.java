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
    public String update(String pedido) throws SQLException{
        dbConn banco = new dbConn();
        String sql = "";
        String Arraypedido[] = new String[5];
        Arraypedido = pedido.split(",");
        
        sql = "UPDATE Musicas " +
                    "SET musica= "+ Arraypedido[1] +", album= "+ Arraypedido[2]+"artista= "+ Arraypedido[3]+
                    "WHERE id= "+ Arraypedido[4]+";";
        
        String resp = banco.executa(sql);
        
        return resp;
    }
    
    public String select(String pedido) throws SQLException{
        String sql = "";
        dbConn banco = new dbConn();

        String Arraypedido[] = new String[5];
        Arraypedido = pedido.split(",");
        
        if(Arraypedido.length == 0){
            sql = "SELECT * from musicas;";
            
        }else if(!Arraypedido[0].equals(" ")){
            sql = "SELECT * from musicas" +
                    "WHERE musica= "+ Arraypedido[1]+";";
            
        }else if(!Arraypedido[1].equals(" ")){
            sql = "SELECT * from musicas" +
                    "WHERE album= "+ Arraypedido[2]+";";
            
        }else if(!Arraypedido[2].equals(" ")){
            sql = "SELECT * from musicas" +
                    "WHERE artista= "+ Arraypedido[3]+";";
        }
        
        String resp = banco.executa(sql);

        return resp;
    }
    public String delete(String pedido) throws SQLException{
        String sql = "";
        dbConn banco = new dbConn();


        sql = "delete from Musicas WHERE id= "+ pedido +";";
        
        String resp = banco.executa(sql);

        return resp;
    }
    public String insert(String pedido) throws SQLException{
        String sql = "";
        dbConn banco = new dbConn();

        String Arraypedido[] = new String[5];
        Arraypedido = pedido.split(",");
        
        sql = "INSERT INTO musicas VALUES"
                + "("+Arraypedido[1]+","+Arraypedido[2]+","+Arraypedido[3]+");";
        
        String resp = banco.executa(sql);

        
        return resp;
    }

}