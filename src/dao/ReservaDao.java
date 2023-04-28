package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import modelos.Reserva;

public class ReservaDao {

private Connection con;
    
    public ReservaDao(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO RESERVAS "
                        + "(fecha_entrada, fecha_salida, valor, forma_pago)"
                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setString(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el huesped: %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
	}   
	
	public int modificar (Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String entrada = date.format(fechaEntrada);
        String salida = date.format(fechaSalida);

        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE RESERVAS SET "
                    + " fechaEntrada = ?, "
                    + " fechaSalida = ?,"
                    + " valor = ?,"
                    + " formaPago = ?"
                    + " WHERE id = ?"
            );

            try (statement) {

                statement.setDate(1, Date.valueOf(entrada));
                statement.setDate(2, Date.valueOf(salida));
                statement.setString(3, valor);
                statement.setString(4, formaPago);

                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public int eliminar(Integer idReserva) {
        try {

            PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE id = ?");

            try (statement) {
                statement.setInt(1, idReserva);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Reserva> listar() {

        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM RESERVAS");

            try (statement) {
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {

                    while (resultSet.next()) {
                        resultado.add(new Reserva(
                                resultSet.getInt("id"),
                                resultSet.getDate("fecha_entrada"),
                                resultSet.getDate("fecha_salida"),
                                resultSet.getString("valor"),
                                resultSet.getString("forma_pago")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
	
	 public List<Reserva> busquedaPorId(int idBusqueda) {

	        List<Reserva> resultado = new ArrayList<>();

	        try {
	            final PreparedStatement statement = con
	                    .prepareStatement("SELECT * FROM RESERVAS WHERE id = ?");

	            try (statement) {
	                statement.setInt(1, idBusqueda);
	                statement.execute();

	                final ResultSet resultSet = statement.getResultSet();

	                try (resultSet) {

	                    while (resultSet.next()) {
	                        resultado.add(new Reserva(
	                                resultSet.getInt("id"),
	                                resultSet.getDate("fechaEntrada"),
	                                resultSet.getDate("fechaSalida"),
	                                resultSet.getString("valor"),
	                                resultSet.getString("formaPago")
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
