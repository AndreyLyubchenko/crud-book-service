package com.agrotep.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;


import jakarta.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class BookRequest {

    @NotEmpty(message = "{field.is.empty}")
    String name;

    @NotNull
    @Past
    Date bookDate;
}
