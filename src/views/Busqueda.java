package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedesController;
import controller.ReservasController;
import modelos.Huesped;
import modelos.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservasController reservasController;
	private HuespedesController huespedesController;
	String reservas;
	String huespedes;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservasController = new ReservasController();
		this.huespedesController = new HuespedesController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(321, 62, 290, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		tbReservas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		mostrarTablaReservas();
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);		
		mostrarTablaHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();
				if(txtBuscar.getText().equals("")) {
					mostrarTablaReservas();
					mostrarTablaHuespedes();
				}else {
					mostrarTablaReservasId();
					mostrarTablaHuespedesId();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				if(filaReservas >= 0) {
					limpiarTabla();
					mostrarTablaReservas();
					mostrarTablaHuespedes();
				}else
					{if(filaHuespedes >= 0) {
						limpiarTabla();
						mostrarTablaHuespedes();
						mostrarTablaReservas();
					}
				}
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 if (panel.getSelectedIndex() == 0) {
	                    editarReserva();
	                    limpiarTabla();
	                    mostrarTablaReservas();
	                } else if (panel.getSelectedIndex() == 1) {
	                    editarHuesped();
	                    limpiarTabla();
	                    mostrarTablaHuespedes();
	                }
	                limpiarInput();
	            }
		});
		
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					reservas = tbReservas.getValueAt(filaReservas, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar la reserva?");
					if (confirmar == JOptionPane.YES_OPTION) {
						String valor = tbReservas.getValueAt(filaReservas, 0).toString();
						reservasController.eliminar(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Registro eliminado con exito!");
						limpiarTabla();
						mostrarTablaReservas();	
						mostrarTablaHuespedes();
					}
				}else 
					if(filaHuespedes >= 0) {
					huespedes = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
					int confirmar = JOptionPane.showConfirmDialog(null, "Desea borrar el Huésped?");
					if (confirmar == JOptionPane.YES_OPTION) {
						String valor = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
						huespedesController.eliminar(Integer.valueOf(valor));
						JOptionPane.showMessageDialog(contentPane, "Huésped eliminado con exito!");
						limpiarTabla();
						mostrarTablaHuespedes();
						mostrarTablaReservas();	
					}
				}
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
				
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	
	private List<Reserva> mostrarReservas(){
		return this.reservasController.listar();
	}
	
	private List<Reserva> buscarIdReserva(){
		return this.reservasController.busquedaPorId(Integer.parseInt(txtBuscar.getText()));
	}
	
	private void mostrarTablaReservas() {
		List<Reserva> reserva = mostrarReservas();
		modelo.setRowCount(0);
		try {
			for(Reserva reservas : reserva ) {
				modelo.addRow(new Object[] {
						reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(), 
						reservas.getValor(), reservas.getFormaPago()});
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void mostrarTablaReservasId() {
		List<Reserva> reserva = buscarIdReserva();
		
		try {
			for(Reserva reservas : reserva ) {
				modelo.addRow(new Object[] {
						reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(), 
						reservas.getValor(), reservas.getFormaPago()});
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	
	private List<Huesped> mostrarHuespedes(){
		return this.huespedesController.listar();
	}
	
	private List<Huesped> buscarIdHuespedes(){
		return this.huespedesController.busquedaPorId(Integer.parseInt(txtBuscar.getText()));
	}
	
	private void mostrarTablaHuespedes() {
		List<Huesped> huesped = mostrarHuespedes();
		modeloHuesped.setRowCount(0);
		try {
			for(Huesped huespedes : huesped ) {
				modeloHuesped.addRow(new Object[] {
						huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(), huespedes.getFechaNacimiento(), 
						huespedes.getNacionalidad(), huespedes.getTelefono(), huespedes.getIdReservas()
				});
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void mostrarTablaHuespedesId() {
		List<Huesped> huesped = buscarIdHuespedes();
		
		try {
			for(Huesped huespedes : huesped ) {
				modeloHuesped.addRow(new Object[] {
						huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(), huespedes.getFechaNacimiento(), 
						huespedes.getNacionalidad(), huespedes.getTelefono(), huespedes.getIdReservas()
				});
			}
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	 private void editarReserva() {

	            if (JOptionPane.showConfirmDialog(this, "¿Deseas modificar la información del huesped?", "QUESTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            

	                /*int id = (int) modelo.getValueAt(tbReservas.getSelectedColumn(), 0);
	                String fechaE = modelo.getValueAt(tbReservas.getSelectedColumn(), 1).toString().trim();
	                java.util.Date entrada = null;
					try {
						entrada = conversion.parse(fechaE);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
	                Date fechaEntrada = new Date(entrada.getTime());
	                String fechaS = modelo.getValueAt(tbReservas.getSelectedColumn(), 2).toString().trim();
	                java.util.Date salida = null;
					try {
						salida = conversion.parse(fechaS);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
					Date fechaSalida = new Date(salida.getTime());
	                String valor = modelo.getValueAt(tbReservas.getSelectedColumn(), 3).toString().trim();
	                String formaPago = modelo.getValueAt(tbReservas.getSelectedColumn(), 4).toString().trim();*/
	            	
	            	Integer id = Integer.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					Date fechaEntrada = Date.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
					Date fechaSalida = Date.valueOf( modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
					String valor = modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
					String formaPago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();

	                this.reservasController.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
	                JOptionPane.showMessageDialog(this, "La reserva se ha modificado.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

	            } else {
	                limpiarInput();
	            }
	      }
		
	    private void editarHuesped() {

	            if (JOptionPane.showConfirmDialog(this, "¿Deseas modificar la información del huésped?", "QUESTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            	
	            	int id = (int) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0);

	                String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString().trim();
	                String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString().trim();

	                Date fechaNacimiento = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());

	                String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString().trim();
	                String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),5).toString().trim();
	                int idReserva = (int) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6);

	                this.huespedesController.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
	                JOptionPane.showMessageDialog(this, "El huésped número se ha modificado.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

	            } else {
	                limpiarInput();
	            }
	    }
	
	private void limpiarInput() {
	        txtBuscar.setText("");
	        txtBuscar.requestFocus();
	}    
	    
	private void limpiarTabla() {
		((DefaultTableModel)tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel)tbReservas.getModel()).setRowCount(0);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}

