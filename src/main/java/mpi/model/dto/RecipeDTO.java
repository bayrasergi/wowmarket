package mpi.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    private int id;
    private List<RecipeItemDTO> requiredItems;
    private ItemDTO createdItem;
    private ProfessionDTO requiredProfession;

}
