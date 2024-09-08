package entity;

import converter.DepartmentTypeConverter;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "department_id_generator",
            sequenceName = "department_id_sequence",
//           initalValue giá trị khởi tạo cho id
            initialValue = 5,
//            allocationSize giá trị tăng thêm cho biến next id
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_id_generator"
    )
    private int id;
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
