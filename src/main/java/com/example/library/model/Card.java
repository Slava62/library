package com.example.library.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card implements Serializable{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookid;
    private Long readerid;


    public Card() {

    }


    public Card(Long readerid, Long bookid) {
        this.readerid = readerid;
        this.bookid = bookid;
    }

    public Long getReaderid() {
        return readerid;
    }

    public void setReaderid(Long readerid) {
        this.readerid = readerid;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return readerid.equals(card.readerid) &&
                bookid.equals(card.bookid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerid, bookid);
    }
}
