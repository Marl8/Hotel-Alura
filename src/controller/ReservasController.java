package controller;

import java.sql.Date;
import java.util.List;
import dao.ReservaDao;
import factory.ConnectionFactory;
import modelos.Reserva;


public class ReservasController {

	private ReservaDao reservaDao;
	
	public ReservasController() {
         ConnectionFactory factory = new ConnectionFactory();
        this.reservaDao = new ReservaDao(factory.conexion());
    }
	
	public void guardar (Reserva reserva) {
        
    	reservaDao.guardar(reserva);
    }

	public int modificar(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
        return reservaDao.modificar(fechaEntrada, fechaSalida, valor, formaPago);
    }

    public int eliminar(Integer id) {
        return reservaDao.eliminar(id);
    }

    public List<Reserva> listar() {
        return reservaDao.listar();
    }
    
    public List<Reserva> busquedaPorId(Reserva reserva) {
    	
    	return reservaDao.busquedaPorId(reserva.getId());
    }
}
