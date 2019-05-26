package mpi.repository;

import mpi.model.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeItemRepository extends JpaRepository<RecipeItem, Integer> {
}
