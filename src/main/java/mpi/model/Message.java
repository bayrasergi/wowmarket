package mpi.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import mpi.deserializer.MessageDeserializer;
import mpi.serializer.MessageSerializer;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "message")
//@JsonSerialize(using = MessageSerializer.class)
//@JsonDeserialize(using = MessageDeserializer.class)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", columnDefinition = "serial")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Timestamp timestamp;

    @Column
    private String text;
}
