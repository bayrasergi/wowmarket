package mpi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mpi.deserializer.RecipeDeserializer;
import mpi.serializer.RecipeSerializer;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "recipe")
@JsonSerialize(using = RecipeSerializer.class)
@JsonDeserialize(using = RecipeDeserializer.class)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id", columnDefinition = "serial")
    private int id;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeItem> requiredItems;

    @ManyToOne
    @JoinColumn(name = "created_item_id")
    private Item createdItem;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession requiredProfession;
}
