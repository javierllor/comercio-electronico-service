package com.gft.commercial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prices")
@IdClass(PriceEntityId.class)
public class PriceEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6690183733368864288L;

    @Id
    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private String startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private String endDate;

    @Id
    @Column(name = "price_list")
    private Integer priceList;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private Float price;

    @Column(name = "currency")
    private String currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(brandId, that.brandId) &&
                Objects.equals(priceList, that.priceList) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, priceList, productId);
    }
}
