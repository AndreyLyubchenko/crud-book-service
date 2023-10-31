package com.agrotep.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
