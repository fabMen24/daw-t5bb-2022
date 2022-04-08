package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	public static void main(String[] args) {
	
		//Obtener los datos de un usuario, segun su codigo
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		//empezo la transaccion
		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, 1); 
		System.out.println(u);
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();	
}
}
