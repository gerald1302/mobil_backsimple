package com.monsuperprojet.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Produit") // Le nom correspond exactement à la table SQL
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numProduit") // correspondance avec le champ SQL
    private Long numProduit;

    @Column(name = "design", nullable = false)
    private String design;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    // Constructeur vide requis par JPA
    public Produit() {
    }

    public Produit(Long numProduit, String design, Double prix, Integer quantite) {
        this.numProduit = numProduit;
        this.design = design;
        this.prix = prix;
        this.quantite = quantite;
    }

    // Getters et Setters
    public Long getNumProduit() {
        return numProduit;
    }

    public void setNumProduit(Long numProduit) {
        this.numProduit = numProduit;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    @Transient
    public Double getMontant() {
        if (prix == null || quantite == null) return 0.0;
        return prix * quantite;
    }

}
