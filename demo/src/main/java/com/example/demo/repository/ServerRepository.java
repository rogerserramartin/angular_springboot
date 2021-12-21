package com.example.demo.repository;

import com.example.demo.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {

    // Jpa understands findBy and applies a Select * From
    Server findByIpAddress(String ipAddress);

}
