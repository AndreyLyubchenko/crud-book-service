package com.agrotep.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


import java.util.Date;

@Entity
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;


    @Column(name = "name", length = 80, nullable = false)
    String name;

    @Column(name = "book_date", nullable = false)
    Date bookDate;
}
