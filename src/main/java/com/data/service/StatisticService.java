package com.data.service;

import com.data.dto.StatisticDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface StatisticService {

    List<StatisticDto> getStatisticForAllPublishers();

    List<StatisticDto> getStatisticForOnePublisher(Integer publisherId);

}
