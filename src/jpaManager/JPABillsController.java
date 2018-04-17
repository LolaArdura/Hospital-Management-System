package jpaManager;
import javax.persistence.EntityManager;

import interfaces.*;
import model.*;
public class JPABillsController  implements BillsInterface {
 private static JPABillsController singleton;
 public static JPABillsController getJPABillsController() {
	 if (singleton==null) {
		 singleton = new JPABillsController ();
	 }
	 return singleton;
 }
 public boolean insertBill (Bills bill)throws Exception {
	 EntityManager em = DBEntityManager.getEntityManager();
	em.getTransaction().begin();
    em.persist(bill);
	em.getTransaction().commit();
	 return true;
 }
  public boolean deleteBill (Bills bill)throws Exception {
	  EntityManager em = DBEntityManager.getEntityManager();
		em.getTransaction().begin();
	    em.remove(bill);
		em.getTransaction().commit();
		 return true;
	  
  }
  
  //Falta hacer metodo search y update 
 
 
}
