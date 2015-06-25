package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidades.Persona;


public class ConexionDB {
	
	String dbDriver = "com.mysql.jdbc.Driver";
	String host="localhost";
	String port="3306";
	String user="root";
	String pass="patata";
	String db="javadb";
	
	public void add(Persona p) throws ClassNotFoundException, SQLException{
		Class.forName(dbDriver);
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://" + host+":"+port+"/"+db+"?user="+user+"&password="+pass);
		
		PreparedStatement stmt = conn.prepareStatement(
				"insert into personas (dni,nombre,apellido,email)values(?,?,?,?)"); //cuando hay parametros se usa el preparedStatement
		stmt.setInt(1,p.getDni());
		stmt.setString(2,p.getNombre());
		stmt.setString(3,p.getApellido());
		stmt.setString(4,p.getEmail());
		
		
		stmt.execute();
		
		stmt.close();
		conn.close();
	}
}