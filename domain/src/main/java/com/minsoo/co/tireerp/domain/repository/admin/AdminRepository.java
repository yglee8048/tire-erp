package com.minsoo.co.tireerp.domain.repository.admin;

import com.minsoo.co.tireerp.domain.entity.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
