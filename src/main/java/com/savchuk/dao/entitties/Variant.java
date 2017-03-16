package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

/**
 * Created by home on 10.03.17.
 */
@Entity
@Table(name = "variants")
@EnableJpaRepositories
public class Variant {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long varianId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voteId", nullable = false)
    private Vote vote;

    private String item;
    private int amount;

    public Variant(String item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Variant() {
        super();
    }

    public long getVarianId() {
        return varianId;
    }

    public void setVarianId(long varianId) {
        this.varianId = varianId;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
