<<<<<<< HEAD
package jdbcManager;
import java.util.List;

import model.Doctor;

public interface DoctorInterface {
	public boolean insertDoctor (Doctor doctor) throws Exception;
	public boolean deleteDoctor (Doctor doctor) throws Exception;
	public List searchDoctor () throws Exception;
	public Doctor updateDoctor (Doctor doctor) throws Exception;
}
=======
package jdbcManager;
import model.Doctor;

public interface DoctorInterface {
	public boolean insertDoctor (Doctor doctor) throws Exception;
	public boolean deleteDoctor (Doctor doctor) throws Exception;
	public Doctor searchDoctorById (Integer id) throws Exception;
	public Doctor updateDoctor (Doctor doctor) throws Exception;
}
>>>>>>> branch 'master' of https://github.com/LolaArdura/Hospital-Management-System.git
