package mpi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
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
