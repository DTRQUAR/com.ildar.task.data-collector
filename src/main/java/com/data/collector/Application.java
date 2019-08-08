package com.data.collector;

import com.data.collector.CollectTool.Collector;
import com.data.collector.CollectTool.SaleDto;
import com.data.collector.repository.AdvertiserRepository;
import com.data.collector.repository.OfferRepository;
import com.data.collector.repository.PublisherRepository;
import com.data.collector.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
//@EnableJpaRepositories("com.data.collector.repository")
@EnableScheduling
public class Application implements CommandLineRunner {

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    Collector collector;

    @Override
    public void run(String... args) throws Exception {
//        getAdvFromPub();
//        getPubFromAdv();
//        getOffers();
//        getOffersAdv();
//        getOfferSales();
//        getSales();
//        getSaleOffer();
//        getStatisticsForPublisher();
//        System.err.println(getStatisticsForPublisher(100004));
//        testCollectorSaveMethod();

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Transactional
    public void getAdvFromPub() {
        publisherRepository.findById(100004).get().getAdvertisers().forEach(a -> System.err.println(a));
    }

    @Transactional
    public void getPubFromAdv() {
        advertiserRepository.findById(100000).get().getPublishers().forEach(a -> System.err.println(a));
    }

    @Transactional
    public void getOffers() {
        offerRepository.findAll().forEach(o -> System.err.println(o));
    }

    @Transactional
    public void getOffersAdv() {
        offerRepository.findAll().forEach(o -> System.err.println(o.getAdvertiser()));
    }

    @Transactional
    public void getOfferSales() {
        offerRepository.findById(100008).get().getSales().forEach(s -> System.err.println(s));
    }

    @Transactional
    public void getSales() {
        saleRepository.findAll().forEach(s -> System.err.println(s));
    }

    @Transactional
    public void getSaleOffer() {
        System.err.println(saleRepository.findById(100010).get().getOffer());
    }

    public void testCollectorSaveMethod() {
        SaleDto saleDto = new SaleDto(
                "35453884520",
                "2019-08-06 22:52:34",
                "Электрический степлер ЗУБР ЗСП-2000",
                "230382047",
                "Publisher3");
        collector.saveSaleDto(saleDto);
    }

}
