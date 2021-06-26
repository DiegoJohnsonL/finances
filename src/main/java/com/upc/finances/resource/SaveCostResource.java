package com.upc.finances.resource;

import com.upc.finances.domain.model.ExpressedValue;
import lombok.Data;

@Data
public class SaveCostResource {
    private String motive;
    private ExpressedValue expressedValue;
}
