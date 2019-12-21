package mpi.service;

import lombok.AllArgsConstructor;
import mpi.model.Recipe;
import mpi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe getRecipeByCreatedItemId(int itemId) {
        return recipeRepository.findFirstByCreatedItem_Id(itemId).orElse(null);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
