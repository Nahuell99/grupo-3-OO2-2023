package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.TypeDevice;

@Repository("typeDeviceRepository")
public interface ITypeDeviceRepository extends JpaRepository<TypeDevice, Integer> {
    // Aquí puedes agregar métodos personalizados de consulta si los necesitas
}
