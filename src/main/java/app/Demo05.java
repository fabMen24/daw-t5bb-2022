package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	public static void main(String[] args) {
	//Eliminar version 2 --> usando busq
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		//empezo la transaccion
		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, 90);
		if(u==null)
			System.out.println("Codigo NO exite!!");
		else {
			em.remove(u);
			System.out.println("Usuario eliminado...");
		}
		
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();	
	
}
}
