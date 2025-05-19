package com.monsuperprojet.backend.controller;

import com.monsuperprojet.backend.model.Produit;
import com.monsuperprojet.backend.repository.ProduitRepository;
import com.monsuperprojet.backend.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitRepository produitRepository;
    private final ProduitService produitService;

    public ProduitController(ProduitRepository produitRepository, ProduitService produitService) {
        this.produitRepository = produitRepository;
        this.produitService = produitService;
    }

    // Récupérer la liste de tous les produits
    @GetMapping
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

    // Récupérer un produit par son ID
    @GetMapping("/{numProduit}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long numProduit) {
        Optional<Produit> produit = produitRepository.findById(numProduit);
        if (produit.isPresent()) {
            return new ResponseEntity<>(produit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ajouter un nouveau produit
    @PostMapping
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        // Vérification de la validité des champs requis
        if (produit.getDesign() == null || produit.getPrix() == null || produit.getQuantite() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Sauvegarde du produit
        Produit nouveauProduit = produitRepository.save(produit);
        return new ResponseEntity<>(nouveauProduit, HttpStatus.CREATED);
    }

    // Mettre à jour un produit existant
    @PutMapping("/{numProduit}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long numProduit, @RequestBody Produit produitDetails) {
        Optional<Produit> produitOpt = produitRepository.findById(numProduit);
        if (produitOpt.isPresent()) {
            Produit produit = produitOpt.get();
            produit.setDesign(produitDetails.getDesign());
            produit.setPrix(produitDetails.getPrix());
            produit.setQuantite(produitDetails.getQuantite());
            Produit updatedProduit = produitRepository.save(produit);
            return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un produit
    @DeleteMapping("/{numProduit}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable Long numProduit) {
        Optional<Produit> produit = produitRepository.findById(numProduit);
        if (produit.isPresent()) {
            produitRepository.deleteById(numProduit);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Récupérer les statistiques des montants des produits
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Double>> getStatsMontant() {
        Map<String, Double> stats = produitService.getStatsMontant();
        return ResponseEntity.ok(stats);
    }
}



//package com.monsuperprojet.backend.controller;
//
//import com.monsuperprojet.backend.model.Produit;
//import com.monsuperprojet.backend.repository.ProduitRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/produits")
//public class ProduitController {
//
//    private final ProduitRepository produitRepository;
//
//    public ProduitController(ProduitRepository produitRepository) {
//        this.produitRepository = produitRepository;
//    }
//
//    @GetMapping
//    public List<Produit> getProduits() {
//        return produitRepository.findAll();
//    }
//
//    @GetMapping("/{numProduit}")
//    public Optional<Produit> getProduitById(@PathVariable Long numProduit) {
//        return produitRepository.findById(numProduit);
//    }
//
//    @PostMapping
//    public Produit ajouterProduit(@RequestBody Produit produit) {
//        return produitRepository.save(produit);
//    }
//
//    @DeleteMapping("/{numProduit}")
//    public void supprimerProduit(@PathVariable Long numProduit) {
//        produitRepository.deleteById(numProduit);
//    }
//}
