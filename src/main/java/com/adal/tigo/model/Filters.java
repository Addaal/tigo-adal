package com.adal.tigo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Filters {
    private String name;
    private Boolean active;
    private String orderBy;
    private Integer size;
    private Integer page;
}
