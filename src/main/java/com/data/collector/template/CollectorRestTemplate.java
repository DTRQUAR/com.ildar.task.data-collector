package com.data.collector.template;

import com.data.collector.dto.SaleDto;

import java.util.List;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public interface CollectorRestTemplate {
    List<SaleDto> getSaleStatistic(String statisticUrl, String publisherName);
}
