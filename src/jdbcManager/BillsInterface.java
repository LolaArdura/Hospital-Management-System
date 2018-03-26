package jdbcManager;

import model.Bills;

public interface BillsInterface {
 public boolean insertBills (Bills bill, Integer patientId) throws Exception;
 public boolean deleteBills (Bills bill)throws Exception;
 public Bills searchBillsById (Integer id)throws Exception;
 public Bills updateBills (Bills bill) throws Exception;
}
