package com.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Entity
@Table(name = "resp_detail")
public class ResponseDetail extends NamedEntity {

    @Column(name = "transaction_number")
    private String transactionNumberName;

    @Column(name = "date_time")
    private String dateTimeName;

    @Column(name = "offer_name")
    private String offerName;

    @Column(name = "offer_number")
    private String offerNumberName;

    @Column(name = "publisher_name")
    private String publisherName;

    public String getTransactionNumberName() {
        return transactionNumberName;
    }

    public void setTransactionNumberName(String transactionNumberName) {
        this.transactionNumberName = transactionNumberName;
    }

    public String getDateTimeName() {
        return dateTimeName;
    }

    public void setDateTimeName(String dateTimeName) {
        this.dateTimeName = dateTimeName;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferNumberName() {
        return offerNumberName;
    }

    public void setOfferNumberName(String offerNumberName) {
        this.offerNumberName = offerNumberName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "ResponseDetail{" +
                "name='" + name + '\'' +
                ", transactionNumberName='" + transactionNumberName + '\'' +
                ", dateTimeName='" + dateTimeName + '\'' +
                ", offerName='" + offerName + '\'' +
                ", offerNumberName='" + offerNumberName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                '}';
    }

}
