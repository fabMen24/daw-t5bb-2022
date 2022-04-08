package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		//Eliminar un usuario
		
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		//empezo la transaccion
		em.getTransaction().begin();
		//proceso--> grabaren la tabla
		//forma 1 --borrado logico--> no lo borras, solo cambias algun estado,flag
		//em.merge(u);
		//forma 2 borrado fisico --> borra el registro
		Usuario u = new Usuario(33,"Carla","Toro","U0022@gmail.com","10002","2020/03/24",2,1);
		em.remove(u);
		//confirmar la transaccion
		em.getTransaction().commit();
		em.close();
	}
}
