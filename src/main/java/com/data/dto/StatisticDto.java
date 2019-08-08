package com.data.dto;

public class StatisticDto {

    private String publisherName;
    private String advertiserName;
    private String offerName;
    private Integer salesAmount;
    private Integer commission;
    private Double publisherEarning;

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Double getPublisherEarning() {
        return publisherEarning;
    }

    public void setPublisherEarning(Double publisherEarning) {
        this.publisherEarning = publisherEarning;
    }

    @Override
    public String toString() {
        return "StatisticDto{" +
                "publisherName='" + publisherName + '\'' +
                ", advertiserName='" + advertiserName + '\'' +
                ", offerName='" + offerName + '\'' +
                ", salesAmount=" + salesAmount +
                ", commission=" + commission +
                ", publisherEarning=" + publisherEarning +
                '}';
    }

}
