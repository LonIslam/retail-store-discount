package com.example.retailstorediscounts.dao;

import com.example.retailstorediscounts.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDao extends JpaRepository<Bill, Long> {

}
