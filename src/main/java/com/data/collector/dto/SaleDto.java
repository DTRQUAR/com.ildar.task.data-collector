package com.data.collector.dto;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public class SaleDto {

    private String transactionNumber;
    private String dateTime;
    private String offerName;
    private String offerNumber;
    private String publisherName;

    public SaleDto(String transactionNumber, String dateTime, String offerName, String offerNumber, String publisherName) {
        this.transactionNumber = transactionNumber;
        this.dateTime = dateTime;
        this.offerName = offerName;
        this.offerNumber = offerNumber;
        this.publisherName = publisherName;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferNumber() {
        return offerNumber;
    }

    public void setOfferNumber(String offerNumber) {
        this.offerNumber = offerNumber;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "SaleDto{" +
                "transactionNumber='" + transactionNumber + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", offerName='" + offerName + '\'' +
                ", offerNumber='" + offerNumber + '\'' +
                ", publisherName='" + publisherName + '\'' +
                '}';
    }

}
