package com.adal.tigo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Datatable<T> {

    private List<T> data;
    private long totalElements;
    private int size;
    private int offset;
}
