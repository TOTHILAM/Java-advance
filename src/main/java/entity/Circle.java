package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "circle")
@PrimaryKeyJoinColumn(name = "id")
public class Circle extends Shape {
    @Column(name = "radius", nullable = false)
    private int radius;
}
