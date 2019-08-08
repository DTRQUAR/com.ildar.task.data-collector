package com.data.collector.repository;

import com.data.collector.model.Publisher;
import com.data.collector.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findAllByPublisher(Publisher publisher);

    Sale findByTransactionNumberAndOfferId(String transactionsNumber, Integer offerId);
}
