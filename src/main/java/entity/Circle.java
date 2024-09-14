package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "circle")
public class Circle extends Shape {
    @Column(name = "radius", nullable = false)
    private int radius;
}
