package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Huesped;



public class HuespedDao {

	private Connection con;
    
    public HuespedDao(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO HUESPEDES "
                        + "(nombre, apellido, fecha_nac, nacionalidad, telefono, id_reserva)"
                        + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);


    
            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getIdReservas());
                

                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el huesped: %s", huesped));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	public int modificar(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, int idReserva) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE HUESPED SET "
                    + " id = ?, "
                    + " NOMBRE = ?, "
                    + " APELLIDO = ?,"
                    + " FECHA_NACIMIENTO = ?"
                    + " NACIONALIDAD = ?,"
                    + " TELEFONO = ?,"
                    + " ID_RESERVA = ?");

            try (statement) {
            	statement.setInt(1, id);
                statement.setString(2, nombre);
                statement.setString(3, apellido);
                statement.setDate(4, fechaNacimiento);
                statement.setString(5, nacionalidad);
                statement.setString(6, telefono);
                statement.setInt(7, idReserva);

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	 public int eliminar(Integer id) {
	        try {
	            final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPED WHERE ID = ?");

	            try (statement) {
	                statement.setInt(1, id);
	                statement.execute();

	                int updateCount = statement.getUpdateCount();

	                return updateCount;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 public List<Huesped> listar() {

	        List<Huesped> resultado = new ArrayList<>();

	        try {
	            final PreparedStatement statement = con
	                    .prepareStatement("SELECT * FROM HUESPEDES");

	            try (statement) {
	                statement.execute();

	                final ResultSet resultSet = statement.getResultSet();

	                try (resultSet) {

	                    while (resultSet.next()) {
	                        resultado.add(new Huesped(
	                        		
	                        		resultSet.getInt("id"),
	                                resultSet.getString("nombre"),
	                                resultSet.getString("apellido"),
	                                resultSet.getDate("fecha_nac"),
	                                resultSet.getString("nacionalidad"),
	                                resultSet.getString("telefono"),
	                                resultSet.getInt("id_reserva")
	                        ));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        return resultado;
	    }
		
		 public List<Huesped> busquedaPorId(int idBusqueda) {

		        List<Huesped> resultado = new ArrayList<>();

		        try {
		            final PreparedStatement statement = con
		                    .prepareStatement("SELECT * FROM HUESPED WHERE id = ?");

		            try (statement) {
		                statement.setInt(1, idBusqueda);
		                statement.execute();

		                final ResultSet resultSet = statement.getResultSet();

		                try (resultSet) {

		                    while (resultSet.next()) {
		                        resultado.add(new Huesped(

		                        		resultSet.getInt("id"),
		                        		resultSet.getString("nombre"),
		                                resultSet.getString("apellido"),
		                                resultSet.getDate("fecha_nac"),
		                                resultSet.getString("nacionalidad"),
		                                resultSet.getString("telefono"),
		                                resultSet.getInt("id_reserva")
		                        ));
		                    }
		                }
		            }
		        } catch (SQLException e) {
		            throw new RuntimeException(e);
		        }

		        return resultado;
		    }
}