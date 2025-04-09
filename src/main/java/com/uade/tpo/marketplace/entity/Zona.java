// package com.uade.tpo.marketplace.entity;

// import java.util.List;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToOne;
// import lombok.Data;

// @Data
// @Entity
// public class Zona {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     @Column
//     private String nombre;

//     @Column
//     private float precio_base;

//     @OneToMany(mappedBy = "zona")
//     private List<Butaca> butaca;

//     @ManyToOne
//     @JoinColumn(name = "locacion", nullable = false)
//     private Locacion locacion;

//     @OneToOne(mappedBy = "zona")
//     private Entrada entrada;
// }
