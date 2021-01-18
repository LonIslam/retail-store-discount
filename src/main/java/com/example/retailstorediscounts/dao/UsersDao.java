package com.example.retailstorediscounts.dao;

import com.example.retailstorediscounts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<User, Long> {

}
