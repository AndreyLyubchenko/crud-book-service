package com.agrotep.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;


import jakarta.validation.constraints.NotEmpty;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {

    @NotEmpty(message = "field must be not empty")
    @Size(max = 10, message = "Name must not exceed 10 characters")
    String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Past
    Date bookDate;
}
