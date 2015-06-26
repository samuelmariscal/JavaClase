package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import entidades.Persona;


public class ConexionDB {
	
	String dbDriver = "com.mysql.jdbc.Driver";
	String host="localhost";
	String port="3306";
	String user="user";
	String pass="user";
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
	
	public ArrayList<Persona> getAll() throws ClassNotFoundException, SQLException{
		Class.forName(dbDriver);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+ host+":"+port+"/"+db+"?user="+user+"&password="+pass);

		Statement stmt = (Statement) conn.createStatement();
		ArrayList<Persona> personas =new ArrayList<>();
		ResultSet rs= stmt.executeQuery("select * from personas;");
		while(rs.next()){
			Persona p=new Persona();
			p.setId(rs.getInt("id"));
			p.setDni(rs.getInt("dni"));
			p.setNombre(rs.getString("nombre"));
			p.setApellido(rs.getString("apellido"));
			p.setEmail(rs.getString("email"));
			
			personas.add(p);
		}
		
		rs.close();
		
		stmt.close();
		
		conn.close();
		return personas;
		
	}
	
	public Persona getByDni(int dni) throws ClassNotFoundException, SQLException{
		Class.forName(dbDriver);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+ host+":"+port+"/"+db+"?user="+user+"&password="+pass);

		PreparedStatement stmt = conn.prepareStatement("select * from personas where dni=?");
		stmt.setInt(1,dni);
		ResultSet rs= stmt.executeQuery();
		Persona p=new Persona();
		if (rs.next()==true) {
			p.setId(rs.getInt("id"));
			p.setDni(rs.getInt("dni"));
			p.setNombre(rs.getString("nombre"));
			p.setApellido(rs.getString("apellido"));
			p.setEmail(rs.getString("email"));
		} else {
			p=null;
		}
		rs.close();
		
		stmt.close();
		
		conn.close();
		return p;
		
	}


}