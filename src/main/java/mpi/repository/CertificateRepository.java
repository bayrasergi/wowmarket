package mpi.repository;

import mpi.model.Certificate;
import mpi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    List<Certificate> findAllByUser(User user);
}
