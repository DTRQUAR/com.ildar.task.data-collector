package com.data.collector;

import com.data.advertiser.system.stub.SalesGenerator;
import com.data.model.Advertiser;
import com.data.model.ResponseDetail;
import com.data.repository.AdvertiserRepository;
import com.data.util.DateTimeUtil;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Component
public class MockRestTemplate {

    private static final Logger logger = LoggerFactory.getLogger(MockRestTemplate.class);

    @Autowired
    private AdvertiserRepository advertiserRepository;

    public List<SaleDto> getSaleStatistic(String statisticUrl, String publisherName) {
        List<SaleDto> saleDtos = new ArrayList<>();

        String jsonSales = SalesGenerator.getSales(statisticUrl, publisherName);
        if (jsonSales != null) {
            Advertiser advertiser = advertiserRepository.findByStatisticUrl(statisticUrl);
            ResponseDetail responseDetail = advertiser.getResponseDetail();

            JSONParser jsonParser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
            JSONArray parse = new JSONArray();
            try {
                parse = jsonParser.parse(jsonSales, JSONArray.class);
            } catch (ParseException e) {
                logger.info(e.getMessage(), DateTimeUtil.DATE_TME_FORMATTER.format(LocalDateTime.now()));
            }

            for (int i = 0; i < parse.size(); i++) {
                JSONObject cap = (JSONObject) parse.get(i);
                String transactionNumber = (String) cap.get(responseDetail.getTransactionNumberName());
                String dateTime = (String) cap.get(responseDetail.getDateTimeName());
                String offerName = (String) cap.get(responseDetail.getOfferName());
                String offerNumber = (String) cap.get(responseDetail.getOfferNumberName());
                String publisher = (String) cap.get(responseDetail.getPublisherName());

                SaleDto saleDto = new SaleDto(transactionNumber, dateTime, offerName, offerNumber, publisher);
                saleDtos.add(saleDto);
            }
        }

        return saleDtos;
    }
}
