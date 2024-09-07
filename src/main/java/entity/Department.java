package entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(name =  "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name =  "update_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist() {
        System.out.println("Trước khi thêm vào database");
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Sau khi thêm vào database");
    }

    public enum Type {
        DEVELOPER,TESTER, SCRUM_MASTER,PROJECT_MANAGER
    }
}
