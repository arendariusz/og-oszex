package com.example.ogloszex.offer;

import com.example.ogloszex.offer.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByTitleContainingIgnoreCase(String title);
}
