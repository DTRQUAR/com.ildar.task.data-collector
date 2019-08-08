package com.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "advertisers")
public class Advertiser extends NamedEntity {

    @Column(name = "statistic_url")
    private String statisticUrl;

    private Integer commission;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "advertiser")
    @OrderBy("dateTime DESC")
    private List<Offer> offers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "pub_adv",
            joinColumns = {@JoinColumn(name = "adv_id")},
            inverseJoinColumns = {@JoinColumn(name = "pub_id")})
    private Set<Publisher> publishers;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "resp_detail_id", nullable = false)
    private ResponseDetail responseDetail;

    public Advertiser() {
    }

    public String getStatisticUrl() {
        return statisticUrl;
    }

    public void setStatisticUrl(String statisticUrl) {
        this.statisticUrl = statisticUrl;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Set<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(Set<Publisher> publishers) {
        this.publishers = publishers;
    }

    public ResponseDetail getResponseDetail() {
        return responseDetail;
    }

    public void setResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
    }

    @Override
    public String toString() {
        return "Advertiser{" +
                "id='" + id + '\'' +
                ", statisticUrl='" + name + '\'' +
                ", name='" + name + '\'' +
                ", commission=" + commission +
                '}';
    }

}
