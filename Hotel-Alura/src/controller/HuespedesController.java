package controller;

import java.sql.Date;
import java.util.List;
import dao.HuespedDao;
import factory.ConnectionFactory;
import modelos.Huesped;


public class HuespedesController {

private HuespedDao huespedDao;
	
	public HuespedesController() {
         ConnectionFactory factory = new ConnectionFactory();
        this.huespedDao = new HuespedDao(factory.conexion());
    }
	
	public void guardar (Huesped huesped) {

    	huespedDao.guardar(huesped);
    }

<<<<<<< HEAD
	public int modificar(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, 
			String telefono, int id_reserva) {
        return huespedDao.modificar(id, nombre, apellido, fechaNacimiento , nacionalidad, telefono, id_reserva);
=======
	public int modificar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        return huespedDao.modificar(nombre, apellido, fechaNacimiento , nacionalidad, telefono);
>>>>>>> 39ae3cd9c0313034545bb0d120bfe151edae55bd
    }

    public int eliminar(Integer id) {
        return huespedDao.eliminar(id);
    }

    public List<Huesped> listar() {
        return huespedDao.listar();
    }
    
<<<<<<< HEAD
    public List<Huesped> busquedaPorId(int id) {
    	
    	return huespedDao.busquedaPorId(id);
=======
    public List<Huesped> busquedaPorId(Huesped huesped) {
    	
    	return huespedDao.busquedaPorId(huesped.getId());
>>>>>>> 39ae3cd9c0313034545bb0d120bfe151edae55bd
    }
}
