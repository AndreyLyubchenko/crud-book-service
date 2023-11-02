package com.agrotep.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class BookFullResponse {

    long id;
    String name;
    Date bookDate;
}
