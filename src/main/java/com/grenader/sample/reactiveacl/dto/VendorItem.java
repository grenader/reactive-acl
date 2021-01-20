package com.grenader.sample.reactiveacl.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendorItem {
    private String id;

    private String name;
    private int count;

}
