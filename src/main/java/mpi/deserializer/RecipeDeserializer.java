package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Item;
import mpi.model.Profession;
import mpi.model.Recipe;
import mpi.model.RecipeItem;
import mpi.util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mpi.util.JsonUtil.*;

public class RecipeDeserializer extends JsonDeserializer<Recipe> {
    @Override
    public Recipe deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Recipe deserialize(JsonNode json) {
        if (json == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(getInt(json, "id"));
        recipe.setCreatedItem(ItemDeserializer.deserialize(get(json, "createdItem")));
        recipe.setRequiredProfession(ProfessionDeserializer.deserialize(get(json, "requiredProfession")));
        List<RecipeItem> recipeItems = new ArrayList<>();
        for (JsonNode requiredItem : json.get("requiredItems")) {
            if (requiredItem == null || requiredItem.isNull()) {
                continue;
            }
            RecipeItem recipeItem = new RecipeItem();
            recipeItem.setItem(new Item());
            recipeItem.getItem().setName(getString(requiredItem, "name"));
            recipeItem.setItemsRequired(getInt(requiredItem, "count"));
            recipeItems.add(recipeItem);
        }
        recipe.setRequiredItems(recipeItems);
        return recipe;
    }
}
