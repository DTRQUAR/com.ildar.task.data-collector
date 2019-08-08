package com.data.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column(name = "transaction_number", nullable = false)
    private String transactionNumber;

    @Column(name = "date_time", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_id", nullable = false)
    private Publisher publisher;

    public Sale() {
    }

    public Sale(String transactionNumber, LocalDateTime dateTime, Offer offer, Publisher publisher) {
        this.transactionNumber = transactionNumber;
        this.dateTime = dateTime;
        this.offer = offer;
        this.publisher = publisher;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", transactionNumber=" + transactionNumber +
                ", dateTime=" + dateTime +
                ", publisher=" + publisher.getName() +
                '}';
    }

}
