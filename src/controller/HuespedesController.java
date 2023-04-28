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

	public int modificar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        return huespedDao.modificar(nombre, apellido, fechaNacimiento , nacionalidad, telefono);
    }

    public int eliminar(Integer id) {
        return huespedDao.eliminar(id);
    }

    public List<Huesped> listar() {
        return huespedDao.listar();
    }
    
    public List<Huesped> busquedaPorId(Huesped huesped) {
    	
    	return huespedDao.busquedaPorId(huesped.getId());
    }
}
