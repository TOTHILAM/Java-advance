package entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("rectangle")
public class Rectangle extends Shape {
    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;
}
