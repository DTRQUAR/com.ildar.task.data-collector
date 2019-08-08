package com.data.collector.repository;

import com.data.collector.model.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Integer> {
}
