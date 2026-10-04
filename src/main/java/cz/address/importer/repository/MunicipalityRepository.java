package cz.address.importer.repository;

import cz.address.importer.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MunicipalityRepository  extends JpaRepository<Municipality, UUID> {
}
