package com.data.repository;

import com.data.model.ResponseDetail;
import org.springframework.data.repository.CrudRepository;

public interface ResponseDetailRepository extends CrudRepository<ResponseDetail, Integer> {
    ResponseDetail findByName(String name);
}
