package model;
import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity

@Table(name = "tb_productos")
public class Producto {
	@Column(name = "id_prod")

	@Id

	private String codigo;

	@Column(name = "des_prod")

	private String description;

	@Column(name = "stk_prod")

	private int stock;

	@Column(name = "pre_prod")

	private double precio;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false,updatable = false) // listado join
	private Categoria categoria;

	@Column(name = "idcategoria")//grabar un nuervo producto


	private int codigocategoria; 
	@Column(name = "est_prod")

	private int estado;
	
	@ManyToOne
	@JoinColumn(name = "idprovedor", insertable = false,updatable = false) // listado join
	private Proveedor proveedor; //para el listado join

	private int idprovedor;
	

		


}