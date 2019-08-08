package com.data.collector.CollectTool;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MockRestTemplate {

    public SalesDtoList getSaleStatistic(String statisticUrl, String publisherName) {
        List<SaleDto> saleDtos = SalesGenerator.getSales().get(statisticUrl);
        if (saleDtos != null) {
            return new SalesDtoList(saleDtos.stream()
                    .filter(s -> s.getPublisherName().equals(publisherName))
                    .collect(Collectors.toList()));
        }
        return new SalesDtoList(Collections.emptyList());
    }

}
