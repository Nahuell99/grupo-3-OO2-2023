package repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.SensorHumedad;

@Repository("SensorHumedadRepository")
public interface ISensorHumedadRepositorio extends JpaRepository<SensorHumedad,Serializable> {
	public abstract SensorHumedad findByName(String name);
	public abstract SensorHumedad findByInstitutionAndYear(String institution, int year);
	public abstract List<SensorHumedad> findByInstitutionAndYearOrderByYearDesc(String institution, int year);
	
}
