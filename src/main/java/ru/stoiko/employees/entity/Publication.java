package ru.stoiko.employees.entity;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 70)
    private String title;

    @Column(length = 400)
    private String theAbstract;

    @ManyToMany
    @JoinColumn(nullable = false)
    List<Employee> employees;
}
