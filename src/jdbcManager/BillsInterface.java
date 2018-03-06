package jdbcManager;
import model.Bills;

public interface BillsInterface {
 public boolean insertBills (Bills bill) throws Exception;
 public boolean deleteBills (Bills bill)throws Exception;
 public Bills searchBills (Integer id)throws Exception;
 public Bills updateBills (Bills bill) throws Exception;
}
