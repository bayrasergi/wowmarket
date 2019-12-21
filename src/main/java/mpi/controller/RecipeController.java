package mpi.controller;

import lombok.AllArgsConstructor;
import mpi.model.Recipe;
import mpi.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/recipes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {

    private RecipeService recipeService;

    @GetMapping("/created-item/{itemId}")
    @ResponseBody
    public ResponseEntity<Recipe> getRecipeByCreatedItemId(@PathVariable("itemId") int itemId) {
        return ResponseEntity.ok(recipeService.getRecipeByCreatedItemId(itemId));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
}
