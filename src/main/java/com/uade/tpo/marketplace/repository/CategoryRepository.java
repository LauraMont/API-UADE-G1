package com.uade.tpo.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.marketplace.entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long>{
//     @Query(value = "select c from Categories where c.description =?1") //Define una Query en SQL // * == c
//     List<Categories> findByDescription(String description); 
}
