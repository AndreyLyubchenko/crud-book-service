package com.agrotep.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class BookFullResponse {

    long id;
    String name;
    Date bookDate;
}
