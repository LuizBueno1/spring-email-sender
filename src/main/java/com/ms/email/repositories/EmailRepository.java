package com.ms.email.repositories;

import com.ms.email.models.EmailModel;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
