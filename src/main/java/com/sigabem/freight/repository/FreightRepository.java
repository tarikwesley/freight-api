package com.sigabem.freight.repository;

import com.sigabem.freight.model.Freight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreightRepository extends MongoRepository<Freight, String> {
}