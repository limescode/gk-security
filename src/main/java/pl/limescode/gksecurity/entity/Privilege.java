package pl.limescode.gksecurity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
