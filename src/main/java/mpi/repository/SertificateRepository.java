package mpi.repository;

import mpi.model.Sertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SertificateRepository extends JpaRepository<Sertificate, Integer> {
}
