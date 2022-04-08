package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
public static void main(String[] args) {
	//Actualizar los datos de un usuario
	// valores del nuevo usuario
	Usuario u = new Usuario(3,"Juan","Perez","jperez@gmail.com","345","2000/01/15",1,1);
	//grabar en la tabla --> JPA
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	//empezo la transaccion
	em.getTransaction().begin();
	//proceso--> grabaren la tabla
	em.persist(u);
	//confirmar la transaccion
	em.getTransaction().commit();
	em.close();
	
}
}
