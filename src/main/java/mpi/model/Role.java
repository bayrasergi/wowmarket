package mpi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`role`")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;

    @Column
    private String name;
}
