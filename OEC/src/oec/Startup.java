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
    public void Create() throws SQLException{
    dbConn conection = new dbConn();
    String criaTabela = "create table if not exists Musicas(id int auto_increment primay key,"
                                                        + " musica VARCHAR(30) not null,"
                                                        + " album VARCHAR(20) not null,"
                                                        + " artista VARCHAR(25) not null)";
    
    conection.executa(criaTabela);

    }
}
