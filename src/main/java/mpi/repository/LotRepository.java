package mpi.repository;

import mpi.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {

    List<Lot> findAllBySellerUser_Id(int id);

    List<Lot> findAllByStatus(String status);

    List<Lot> findAllByItem_Name(String name);

    List<Lot> findAllByItem_Type(String type);
}
