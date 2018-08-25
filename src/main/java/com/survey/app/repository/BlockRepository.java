package com.survey.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.survey.app.model.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    List<Block>  findAll();
    
    @Query("SELECT b FROM Block b WHERE b.district.id = :districtId")
   	List<Block> findBlockBydistrictId(Long districtId);

}
