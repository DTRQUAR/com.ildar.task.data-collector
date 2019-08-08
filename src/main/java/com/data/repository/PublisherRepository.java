package com.data.repository;

import com.data.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByName(String name);
}
