package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reader implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long cardid;

    public Reader() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCardid() {
        return cardid;
    }

    public void setCardid(Long cardid) {
        this.cardid = cardid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return name.equals(reader.name) &&
                cardid.equals(reader.cardid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardid);
    }
}