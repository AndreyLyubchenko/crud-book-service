package com.agrotep.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Builder
@Accessors(chain = true)
public class BookFullResponse {

    long id;
    String name;
    Date bookDate;
}
