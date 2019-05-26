package mpi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`User`")
public class User {
    @Id
    @Column(name = "user_id")
    private int id;

    @Column
    private String name;

    @Column
    private String password;

    @Column(name = "user_role")
    private String userRole;
}
