package es.curso.repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.curso.negocio.Ordenador;
import es.curso.repositories.OrdenadorRepository;

public class OrdenadorRepositoryJDBC implements OrdenadorRepository{
	
	static final String DB_URL="jdbc:mysql://localhost:3306/biblioteca";
	static final String USER="root";
	static final String PASS="";

	@Override
	public List<Ordenador> buscarTodos() {
		Connection conexion=null;
		Statement sentencia=null;
		ResultSet rs=null;
		
		List<Ordenador> lista= new ArrayList<Ordenador>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion=DriverManager.getConnection(DB_URL,USER,PASS);
			sentencia= conexion.createStatement();
			rs= sentencia.executeQuery("select * from ordenadores");
			
			while(rs.next()) {
				
				Ordenador o= new Ordenador(rs.getString("marca"),rs.getInt("precio"));
				lista.add(o);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

	@Override
	public void insertar(Ordenador ordenador) {
		Connection conexion=null;
		Statement sentencia=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion=DriverManager.getConnection(DB_URL,USER,PASS);
			sentencia=conexion.createStatement();
			String insertarSql = "insert into ordenadores values('"+ordenador.getMarca()+"','"+ordenador.getPrecio()+"')";
			
			System.out.println(insertarSql);
			
			sentencia.executeUpdate(insertarSql);
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
