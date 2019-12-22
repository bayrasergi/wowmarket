package mpi.repository;

import mpi.model.Request;
import mpi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "select r from request r where (r.buyer_user_id=:userId or r.seller_user_id=:userId)",
            nativeQuery = true)
    List<Request> getAllByBuyerUserOrSellerUser(int userId);

    @Query(value = "SELECT r from request r where requested_item_id in (" +
            "select r.requested_item_id from recipe r where profession_id in (" +
            "select c.profession_id from certificate c where c.user_id=:userId))",
            nativeQuery = true)
    List<Request> getAllByProfession(int userId);
}
