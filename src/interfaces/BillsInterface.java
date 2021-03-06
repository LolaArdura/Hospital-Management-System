package interfaces;

import model.Bills;

public interface BillsInterface {
 public boolean insertBills (Bills bill) throws Exception;
 public boolean deleteBills (Bills bill)throws Exception;
 public Bills searchBillsById (Integer id)throws Exception;
 public void updateBills (Bills bill) throws Exception;
}
