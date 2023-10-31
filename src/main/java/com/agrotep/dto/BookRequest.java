package com.agrotep.dto;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class BookRequest {
    @NotEmpty(message = "{field.is.empty}")
    String name;
    @NotEmpty(message = "{field.is.empty}")
    Date bookDate;
}
