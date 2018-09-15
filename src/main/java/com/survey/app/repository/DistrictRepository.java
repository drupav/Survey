package com.survey.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.survey.app.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District>  findAll();
    
    @Query("SELECT d FROM District d WHERE d.region.id = :regionId")
	List<District> findDistrictsByRegionId(Long regionId);

    @Query("SELECT d FROM District d WHERE d.districtCode = :districCode")
  	District findDistrictsByDistricCode(@Param("districCode")Long districCode);
}
