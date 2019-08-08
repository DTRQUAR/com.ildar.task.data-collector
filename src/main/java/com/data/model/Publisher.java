package com.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Entity
@Table(name = "publishers")
public class Publisher extends NamedEntity {

    @ManyToMany(mappedBy = "publishers", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Advertiser> advertisers;

    public Set<Advertiser> getAdvertisers() {
        return advertisers;
    }

    public void setAdvertisers(Set<Advertiser> advertisers) {
        this.advertisers = advertisers;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }

}
