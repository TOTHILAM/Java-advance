package entity;

import converter.DepartmentTypeConverter;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")

    @GenericGenerator(
            name = "department_id_generator",
            strategy = "generator.DepartmentIdGenarator"
    )
    @GeneratedValue(generator = "department_id_generator")
    private String id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Convert(converter = DepartmentTypeConverter.class)
    private Type type;

    @Column(name =  "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name =  "update_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        System.out.println("Trước thêm vào database");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Sau thêm vào database");
    }

    public enum Type {
        DEVELOPER,TESTER, SCRUM_MASTER,PROJECT_MANAGER
    }
}
