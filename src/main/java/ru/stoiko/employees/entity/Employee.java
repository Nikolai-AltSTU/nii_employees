package ru.stoiko.employees.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFathername() {
        return fathername;
    }

    public String getBiography() {
        return biography;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getInterests() {
        return interests;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public List<Publication> getPublication() {
        return publication;
    }

    /**
     * Фамилия
     */
    @Column(nullable = false, length = 50)
    private String surname;

    /**
     * Имя
     */
    @Column(nullable = false, length = 50)
    private String name;
    /**
     * Отчество
     */
    @Column(nullable = true, length = 50)
    private String fathername;

    /**
     * Краткая биография сотрудника
     */
    @Column(nullable = false, length = 300)
    private String biography;

    /**
     * Наименование должности сотрудника
     */
    @Column(nullable = false, length = 80)
    private String positionName;

    /**
     * Описание интересов сотрудника
     */
    @Column(nullable = false, length = 300)
    private String interests;

    /**
     * Фотография сотрудника
     */
    @Column()
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;

    /**
     * Список публикаций сотрудника
     */
    @ManyToMany
    @JoinColumn()
    List<Publication> publication;
}

