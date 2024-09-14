package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shape")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Shape {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "color", length = 50, nullable = false)
    private String color;
}
