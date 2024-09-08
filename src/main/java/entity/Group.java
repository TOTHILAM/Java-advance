package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "group")
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_account",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    )
    private List<Account> accounts;
}
