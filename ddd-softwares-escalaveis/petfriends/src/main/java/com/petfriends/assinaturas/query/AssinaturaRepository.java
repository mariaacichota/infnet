package com.petfriends.assinaturas.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturaRepository extends JpaRepository<AssinaturaView, String> {
}
