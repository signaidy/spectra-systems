package SpectraSystems.Nexus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpectraSystems.Nexus.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}

