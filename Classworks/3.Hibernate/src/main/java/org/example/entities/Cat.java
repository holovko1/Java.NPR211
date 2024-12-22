package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Генерує сетери. гетери і тд
@AllArgsConstructor //Генерує конструктор з усіма полями
@NoArgsConstructor //Генерує конструктор без параметрів
@Entity //Сущість бд
@Table(name="tblCats")
public class Cat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 150, nullable = false)
    private String name;
    private int age;
    @Column(length = 100, nullable = false)
    private String color;
}
