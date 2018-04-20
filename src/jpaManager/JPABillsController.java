package jpaManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import interfaces.*;
import model.*;
import jdbcManager.JDBCBillsController;


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
  
  public Bills searchBillsById (Integer id)throws Exception{
	  EntityManager em = DBEntityManager.getEntityManager();
	  Query q1= em.createNativeQuery("SELECT * FROM bills WHERE id LIKE ?", Bills.class);
	  q1.setParameter(1, id );
	  Bills bill = (Bills)q1.getSingleResult();
	  return bill;
  }
  
  public Bills updateBill (Bills bill)throws Exception {
	  EntityManager em = DBEntityManager.getEntityManager();
	  //Begin transaction
	  em.getTransaction().begin();
	  
	  //Make changes
	  // vamos a permitir cambiar el id desde aqui????
	  bill.setBankID(bill.getBankID());
	  bill.setTotalCost(bill.getTotalCost());
	  bill.setPaid(bill.getPaid());
	  
	  
	  //End transaction
	  em.getTransaction().commit();
	  return bill;
	  
	  
  }
 
 
}
