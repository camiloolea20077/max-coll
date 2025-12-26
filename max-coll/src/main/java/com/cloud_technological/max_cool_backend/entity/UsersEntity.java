package com.cloud_technological.max_cool_backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter @Setter
public class UsersEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "empresa_id", nullable = false)
  private Long empresaId;

  @Column(name = "rol_id", nullable = false)
  private Long rolId;

  @Column(nullable = false, length = 120)
  private String nombre;

  @Column(nullable = false, length = 60, unique = true)
  private String username;

  @Column(length = 120)
  private String email;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @Column(nullable = false, precision = 1, scale = 0)
  private BigDecimal activo = BigDecimal.ONE;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();
}
