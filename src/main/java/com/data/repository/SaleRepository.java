package com.data.repository;

import com.data.model.Publisher;
import com.data.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findAllByPublisher(Publisher publisher);

    Sale findByTransactionNumberAndOfferId(String transactionsNumber, Integer offerId);
}
