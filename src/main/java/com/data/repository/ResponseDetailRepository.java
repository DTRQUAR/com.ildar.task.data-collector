package com.data.repository;

import com.data.model.ResponseDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface ResponseDetailRepository extends CrudRepository<ResponseDetail, Integer> {
    ResponseDetail findByName(String name);
}
