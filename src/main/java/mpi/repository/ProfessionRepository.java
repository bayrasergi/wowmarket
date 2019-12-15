package mpi.repository;

import mpi.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
    List<Profession> findAllByName(String name);
}
