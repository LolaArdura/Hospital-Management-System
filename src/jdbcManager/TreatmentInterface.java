package jdbcManager;
import model.Treatment;

public interface TreatmentInterface {
public boolean insertTreatment (Treatment treatment)throws Exception;
public boolean deleteTreatment (Treatment treatment)throws Exception;
public Treatment searchTreatment (Integer id) throws Exception;
public Treatment updateTreatment (Treatment treatment);
}