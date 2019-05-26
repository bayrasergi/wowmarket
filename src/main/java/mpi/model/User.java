package mpi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "user_id")
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
