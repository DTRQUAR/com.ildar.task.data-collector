package com.data.advertiser.system.stub.storage;

import net.minidev.json.JSONArray;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Ildar Makhmutov
 * 8.08.2019.
 * <p>
 * Хранилище Response'ов получаемых по REST API Advertiser'ов
 * и содержащих сведения о Sale'ах
 */
public class SalesResponseStorage {

    private static ConcurrentMap<String, ConcurrentMap<String, JSONArray>> jsonSales = new ConcurrentHashMap<>();

    public static ConcurrentMap<String, ConcurrentMap<String, JSONArray>> getJsonSales() {
        return jsonSales;
    }

    public static void setJsonSales(ConcurrentMap<String, ConcurrentMap<String, JSONArray>> jsonSales) {
        SalesResponseStorage.jsonSales = jsonSales;
    }

    public static String getJsonSalesResponse(String statisticUrl, String publisherName) {
        ConcurrentMap<String, JSONArray> advertiserAllPublishersSales = SalesResponseStorage.getJsonSales().get(statisticUrl);
        if (advertiserAllPublishersSales == null) return null;
        JSONArray publisherSales = advertiserAllPublishersSales.get(publisherName);
        if (publisherSales == null) return null;
        return publisherSales.toString();
    }

}
