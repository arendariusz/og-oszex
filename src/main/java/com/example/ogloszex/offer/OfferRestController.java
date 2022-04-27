package com.example.ogloszex.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/offers")
@RestController
public class OfferRestController {

    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("")
    public List<OfferDto> findAll(@RequestParam(required = false) String title) {
        List<OfferDto> dtos;
        if (title != null) {
            dtos = offerService.findByTitle(title);
        } else {
            dtos = offerService.findAll();
        }
        return dtos;
    }

    @PostMapping("")
    public ResponseEntity<OfferDto> addTask(@RequestBody OfferInsertDto dto) {
        OfferDto offerDto = offerService.insert(dto);
        return ResponseEntity.ok(offerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findById(@PathVariable Long id) {
        Optional<OfferDto> offerOptional = offerService.findById(id);
        return offerOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("count")
    public Long offerQty() {
        return offerService.countAllOffers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        offerService.deleteById(id);
    }

}

