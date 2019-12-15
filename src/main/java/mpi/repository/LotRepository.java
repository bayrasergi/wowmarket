package mpi.repository;

import mpi.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {

    List<Lot> findAllByItem_Id(int itemId);

    @Query(value = "SELECT sum(\"count\") FROM lot WHERE item_id=:itemId",
            nativeQuery = true)
    int countAvailableItems(@Param("itemId") int itemId);

    @Query(value = "SELECT l FROM lot l WHERE l.lot_id not in (SELECT r.lot_id FROM request r)",
            nativeQuery = true)
    List<Lot> findAllNotInRequests();

    List<Lot> findAllBySellerUser_Id(int userId);
}
