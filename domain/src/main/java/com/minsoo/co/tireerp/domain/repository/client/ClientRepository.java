package com.minsoo.co.tireerp.domain.repository.client;

import com.minsoo.co.tireerp.domain.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
