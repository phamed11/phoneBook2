package com.phoneBook2.repositories;

import com.phoneBook2.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Long, Address> {
}
