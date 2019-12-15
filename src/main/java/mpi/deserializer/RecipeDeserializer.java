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

import static mpi.util.JsonUtil.getInt;
import static mpi.util.JsonUtil.getString;

public class RecipeDeserializer extends JsonDeserializer<Recipe> {
    @Override
    public Recipe deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Recipe deserialize(JsonNode json) {
        Recipe recipe = new Recipe();
        recipe.setId(getInt(json, "id"));
        Item item = new Item();
        item.setId(getInt(json, "createdItem", "id"));
        item.setName(getString(json, "createdItem", "name"));
        recipe.setCreatedItem(item);
        recipe.setRequiredProfession(new Profession());
        recipe.getRequiredProfession().setName(getString(json, "requiredProfession"));
        List<RecipeItem> recipeItems = new ArrayList<>();
        for (JsonNode requiredItem : json.get("requiredItems")) {
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
