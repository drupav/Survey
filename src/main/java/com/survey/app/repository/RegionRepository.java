package com.survey.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.survey.app.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region>  findAll();

}
