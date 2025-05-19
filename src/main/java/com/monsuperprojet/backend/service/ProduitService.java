package com.monsuperprojet.backend.service;

import com.monsuperprojet.backend.model.Produit;
import com.monsuperprojet.backend.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Map<String, Double> getStatsMontant() {
        List<Produit> produits = produitRepository.findAll();

        if (produits.isEmpty()) {
            return Map.of("min", 0.0, "max", 0.0, "total", 0.0);
        }

        List<Double> montants = produits.stream()
                .map(p -> p.getMontant())
                .toList();

        double min = Collections.min(montants);
        double max = Collections.max(montants);
        double total = montants.stream().mapToDouble(Double::doubleValue).sum();

        return Map.of("min", min, "max", max, "total", total);
    }
}
