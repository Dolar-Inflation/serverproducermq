package frdmplayer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fio", nullable = false, length = Integer.MAX_VALUE)
    private String fio;

    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @Column(name = "\"position\"", nullable = false, length = Integer.MAX_VALUE)
    private String position;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Employeephonerelation> employeephonerelations = new LinkedHashSet<>();

}