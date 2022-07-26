package com.sigabem.shipping.repository;

import com.sigabem.shipping.model.Shipping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends MongoRepository<Shipping, String> {
}