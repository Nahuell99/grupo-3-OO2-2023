package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.Device;

@Repository("deviceRepository")
public interface IDeviceRepository extends JpaRepository<Device, Integer> {
	
	List<Device> findByNombre(String nombre);
	
}
