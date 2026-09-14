package com.dog.starwars_api.repository;

import com.dog.starwars_api.model.WorldModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldRepository extends JpaRepository <WorldModel, Long> {

}
