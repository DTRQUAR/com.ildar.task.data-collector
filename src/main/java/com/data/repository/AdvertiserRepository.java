package com.data.repository;

import com.data.model.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface AdvertiserRepository extends JpaRepository<Advertiser, Integer> {
    Advertiser findByStatisticUrl(String statisticUrl);
}
