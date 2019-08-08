package com.data.repository;

import com.data.model.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Integer> {
    Advertiser findByStatisticUrl(String statisticUrl);
}
