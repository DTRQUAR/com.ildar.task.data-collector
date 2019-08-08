package com.data.repository;

import com.data.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Offer findByNameAndNumber(String name, String offerNumber);
}
