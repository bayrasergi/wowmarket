package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Recipe;
import mpi.model.RecipeItem;
import mpi.model.dto.RecipeDTO;
import mpi.model.dto.RecipeItemDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeSerializer extends JsonSerializer<Recipe> {
    @Override
    public void serialize(Recipe recipe, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(recipe));
    }

    public static RecipeDTO serialize(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setRequiredProfession(recipe.getRequiredProfession().getName());
        recipeDTO.setCreatedItem(ItemSerializer.serialize(recipe.getCreatedItem()));
        List<RecipeItemDTO> requiredItems = new ArrayList<>();
        recipe.getRequiredItems().forEach(ri -> requiredItems.add(serialize(ri)));
        recipeDTO.setRequiredItems(requiredItems);
        return recipeDTO;
    }

    public static RecipeItemDTO serialize(RecipeItem recipeItem) {
        RecipeItemDTO recipeItemDTO = new RecipeItemDTO();
        recipeItemDTO.setId(recipeItem.getId());
        recipeItemDTO.setCount(recipeItem.getItemsRequired());
        recipeItemDTO.setName(recipeItem.getItem().getName());
        return recipeItemDTO;
    }
}
