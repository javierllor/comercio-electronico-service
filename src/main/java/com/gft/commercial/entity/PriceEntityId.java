package com.gft.commercial.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntityId implements Serializable {

    @Serial
    private static final long serialVersionUID = 6282971884443353648L;
    private Integer brandId;
    private Integer priceList;
    private Long productId;
}