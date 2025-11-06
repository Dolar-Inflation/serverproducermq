package frdmplayer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false, length = Integer.MAX_VALUE)
    private String number;

    @Column(name = "type", nullable = false, length = Integer.MAX_VALUE)
    private String type;

    @OneToMany(mappedBy = "phone")
    private Set<Employeephonerelation> employeephonerelations = new LinkedHashSet<>();

}