package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 9, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnModButton = new JButton("Modificar");
		btnModButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Modificar();
			}

		});
		btnModButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnModButton);
		
		JButton btnBuscarButton = new JButton("Buscar");
		btnBuscarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Buscar();
			}
		});
		btnBuscarButton.setBounds(324, 49, 89, 23);
		contentPane.add(btnBuscarButton);
		
		JButton btnElimButton = new JButton("Eliminar");
		btnElimButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar();
			}
		});
		btnElimButton.setBounds(324, 69, 89, 23);
		contentPane.add(btnElimButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		JLabel lblProveedores = new JLabel("Proveedor:");

		lblProveedores.setBounds(230, 106, 102, 14);

		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();

		cboProveedores.setBounds(300, 104, 120, 22);

		contentPane.add(cboProveedores);
		
		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		List<Categoria> lstCategorias = em.createQuery("select a from Categoria a",Categoria.class).getResultList();
		cboCategorias.addItem("Seleccione");
		for(Categoria c :lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		List<Proveedor> proveedores = em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
		
		cboProveedores.addItem("Seleccione");

		for(Proveedor p : proveedores) {

			cboProveedores.addItem(p.getNombre_rs());

		}
		
		em.close();	
	}
	
	void listado() {
		//Grabar en la tabla --> JPA

				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");		

				//Manejador de entidades
				EntityManager em = fabrica.createEntityManager();

				//Consulta con TypeQuery

				TypedQuery<Producto> consulta =em.createQuery("select pro from Producto pro", Producto.class);

				java.util.List<Producto> listadoProductos = consulta.getResultList();
				txtSalida.setText("");

				for(Producto p: listadoProductos) {

					txtSalida.append("************************************************" + "\n");

					txtSalida.append("Id Producto : " + p.getCodigo() + "\n");

					txtSalida.append("Descripcicon: " + p.getDescription() + "\n");

					txtSalida.append("Stock    : " + Integer.toString(p.getStock()) + "\n");

					txtSalida.append("Precio   : " + Double.toString(p.getPrecio()) + "\n");

					
					txtSalida.append("Categoria: " + p.getCodigocategoria() + "/" + p.getCategoria().getDescripcion() + "\n");
					
					txtSalida.append("Estado   : " +Integer.toString(p.getEstado())+"\n");

					txtSalida.append("Id Proveedor: " +p.getIdprovedor()+"\n");
					txtSalida.append("Nombre Proveedor: " +p.getProveedor().getNombre_rs()+"\n");
			

				}

			

				em.close();
	}
	
	void registrar() {
		
		String codigo = txtCódigo.getText();
		String descripcion = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int estado = 1;
		int Provedor = cboProveedores.getSelectedIndex();
		
		//proceso
		Producto p = new Producto();
		p.setCodigo(codigo);
		p.setDescription(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setCodigocategoria(categoria);
		p.setEstado(estado);
		p.setIdprovedor(Provedor);
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		//empezo la transaccion
		em.getTransaction().begin();
		//proceso--> grabaren la tabla
		em.persist(p);
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
		JOptionPane.showMessageDialog(this, "Producto registrado");
		
	}
	void Modificar() {
		
	}
	void Buscar() {
		//variables
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
				EntityManager em = fabrica.createEntityManager();
				//proceso

				//empezo la transaccion

				//em.getTransaction().begin();

				//proceso -- buscar usuario

				Producto p = em.find(Producto.class, txtCódigo.getText());

				//Salida

				txtDescripcion.setText(p.getDescription());
				//cboCategorias.setSelectedItem(p.getCategoria().getDescripcion());
				cboCategorias.setSelectedIndex(p.getCodigocategoria());
				txtPrecio.setText(Double.toString(p.getPrecio()));
				txtStock.setText(Integer.toString(p.getStock()));
				cboProveedores.setSelectedItem(p.getProveedor().getNombre_rs());

				em.close();	
				

			}
	
	void Eliminar() {
		
	}
}
