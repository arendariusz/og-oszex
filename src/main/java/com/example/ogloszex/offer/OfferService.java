package com.example.ogloszex.offer;

import com.example.ogloszex.category.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> findAll() {

        return offerRepository.findAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    private OfferDto toDto(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offer.getId());
        offerDto.setTitle(offer.getTitle());
        offerDto.setDescription(offer.getDescription());
        offerDto.setImgUrl(offer.getImgUrl());
        offerDto.setPrice(offer.getPrice());
        offerDto.setCategoryName(offer.getCategory().getName());
        return offerDto;
    }

    public List<OfferDto> findByTitle(String title) {
        List<Offer> offersWithTitle = offerRepository.findByTitleContainingIgnoreCase(title);

        return offersWithTitle.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    public OfferDto insert(OfferInsertDto dto) {
        Offer offer = new Offer();
        setEntityFields(dto, offer);
        offerRepository.save(offer);
        return toDto(offer);
    }

    private void setEntityFields(OfferInsertDto dto, Offer offer) {
        offer.setTitle(dto.getTitle());
        offer.setDescription(dto.getDescription());
        offer.setImgUrl(dto.getImgUrl());
        offer.setPrice(dto.getPrice());
        offer.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
    }

    public Optional<OfferDto> findById(Long id) {
        return offerRepository.findById(id).map(this::toDto);
    }

    public void deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            // ignore
        }
    }

    public Long countAllOffers() {
        return offerRepository.findAll().stream().count();
    }
}
