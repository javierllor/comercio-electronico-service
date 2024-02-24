package com.gft.commercial.repository;

import com.gft.commercial.entity.PriceEntity;
import com.gft.commercial.entity.PriceEntityId;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<PriceEntity, PriceEntityId> {

    Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Integer brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate);
}
