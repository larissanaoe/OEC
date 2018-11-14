/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author augusto
 */
public class dbConn {
    public String executa (String statement,boolean select,String database) throws SQLException{
        String result = "sucesso";
        Statement st;
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/"+database ,"sa","");
            
            if (select){
                st = con.createStatement();
                ResultSet rs = st.executeQuery(statement);
            
                result = rs.getString("musica") + "," + rs.getString("album") + "," + rs.getString("artista");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String musica = rs.getString("musica");
                    String album = rs.getString("album");
                    String artista = rs.getString("artista");
                    result = result+ "|" + musica + "," + album + "," + artista;
                    System.out.println(result);
                }
                
            }else{
                System.out.println("select "+String.valueOf(select));
                st = con.createStatement();
                System.out.println("criou statement");
                st.execute(statement);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConn.class.getName()).log(Level.SEVERE, null, ex);System.out.println("dnNm");
            
            return ex.getMessage();
        }
        return result;
        
    }
}
