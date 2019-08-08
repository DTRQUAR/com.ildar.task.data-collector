package com.data.collector.repository;

import com.data.collector.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Offer findByNameAndNumber(String name, String offerNumber);
}
