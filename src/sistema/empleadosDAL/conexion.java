/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.empleadosDAL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
/**
 *
 * @author pc
 */
public class conexion {
    //Crear una string de conexion, en donde se coloca la url donde se encuentra
    //la base de datos sqllite
    //Lo primero es decir de que base de datos es, y luego la ruta
    String strConexionDB="jdbc:sqlite:D:/java-sistema/DB/sistema.s3db";
    //usamos una clase connection para hacer referencia a la clase conexion, con
    //esa clase nos conectamos a la base de datos.
    Connection conn=null;
    public conexion(){
        //Conectarnos directamente a la base de datos
        try {   
            //Cargo del drive de conexion a la base de datos
            Class.forName("org.sqlite.JDBC"); 
            //Creo la conexion entre sqllite y la aplicación, luego le pasamos la url
            conn= DriverManager.getConnection(strConexionDB);
            
            System.out.println("Conexión establecida");
        } catch (Exception e) { 
            System.out.println("Error de conexión" + e);
        }
    }
    
    public int ejecutarSentenciaSQL(String strSentenciaSQL){
        try {
            //Clase para ejecutar la intrucción
           PreparedStatement pstm=conn.prepareStatement(strSentenciaSQL);
            pstm.execute();
            return 1; //Devuelve uno si fue exitoso
                
            //Todo error va a parar a SQLException
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public ResultSet consultarRegistros(String strSentenciaSQL){
        try {
            //DEVOLVER INFORMACIÓN
             PreparedStatement pstm=conn.prepareStatement(strSentenciaSQL);
             ResultSet respuesta=pstm.executeQuery();
             return respuesta;
            
        } catch (Exception e) {
            //SABER SI LA INFORMACIÓN EXISTE O NO
            System.out.println(e);
            return null;
        }
    }
}
