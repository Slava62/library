package com.example.library.repository;

import com.example.library.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>  {
}
