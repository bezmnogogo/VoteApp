package com.savchuk.dao.entitties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by home on 07.03.17.
 */
@Entity
@Table(name = "vote")
@EnableJpaRepositories
public class Vote implements Serializable {
    private static String PATH = "http://localhost:8080/vote/";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long voteId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vote", cascade = CascadeType.ALL)
    private List<Variant> variants;

    private String question;
    private String link;
    boolean isFinish, isActive;

    public static String getPATH() {
        return PATH;
    }

    public static void setPATH(String PATH) {
        Vote.PATH = PATH;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public long getVoteId() {
        return voteId;
    }

    public void setVoteId(long voteId) {
        this.voteId = voteId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void generateLink(){
        String str = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
        link = PATH + DigestUtils.md5DigestAsHex(question.concat(new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date())).getBytes());
    }
}
