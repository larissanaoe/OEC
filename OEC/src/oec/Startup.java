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
public class Startup {
    String database;
    
    public Startup(String database) {
        this.database = database;
    }
    
    public void Create() throws SQLException{
    dbConn conection = new dbConn();
    String criaTabela = "create table if not exists Musicas(id int PRIMARY KEY auto_increment,"
                                                        + " musica VARCHAR(30) not null,"
                                                        + " album VARCHAR(20) not null,"
                                                        + " artista VARCHAR(25) not null)";
        System.out.println("chega aqui "+criaTabela);
    
        System.out.println("tabela criada "+conection.executa(criaTabela,false,database));

    }
}
