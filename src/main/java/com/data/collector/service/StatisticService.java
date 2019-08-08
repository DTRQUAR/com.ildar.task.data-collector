package com.data.collector.service;

import com.data.collector.dto.StatisticDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticService {

    List<StatisticDto> getStatisticForAllPublishers();

    List<StatisticDto> getStatisticForOnePublisher(Integer publisherId);

}
