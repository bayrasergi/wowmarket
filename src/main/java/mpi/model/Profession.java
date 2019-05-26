package mpi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue
    @Column(name = "profession_id")
    private int id;

    @Column
    private int name;

    @Column
    private boolean craft;
}
