package com.uade.tpo.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Compra;


@Repository
public interface ComprasRepository extends JpaRepository<Compra,Long> {

}