package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Persona;

public class CatalogoPersonas {
	
	private static ConexionDB conn= new ConexionDB();
	private static ArrayList<Persona> personas=new ArrayList<Persona>();
	
	public CatalogoPersonas(){
		
	}
	
	public List<Persona> getPeronas(){
		return null; //Desarrollar
	}
	
	public Persona getByDni(int dni) throws ClassNotFoundException, SQLException{
		return conn.getByDni(dni);		
	}
	
	public void addPersona(Persona p) throws ClassNotFoundException, SQLException{
		conn.add(p);
		
	}
	
	public void modifyPersona(Persona p){
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getDni()==p.getDni()){
				personas.set(i, p);
			}
		}
	}
	
	public void deletePersona(Persona p){
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getDni()==p.getDni()){
				personas.remove(i);
			}
		}
	}

}

