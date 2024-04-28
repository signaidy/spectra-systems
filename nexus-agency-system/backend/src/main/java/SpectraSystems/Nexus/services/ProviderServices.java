package SpectraSystems.Nexus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.models.Provider;
import SpectraSystems.Nexus.models.Type;
import SpectraSystems.Nexus.repositories.ProviderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServices {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderServices(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    
    /** 
     * @return List<Provider>
     */
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    
    /** 
     * @param id
     * @return Optional<Provider>
     */
    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    
    /** 
     * @param type
     * @return List<Provider>
     */
    public List<Provider> getProviderByType(Type type) {
        return providerRepository.findByType(type);
    }

    
    /** 
     * @param provider
     * @return Provider
     */
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    
    /** 
     * @param id
     */
    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}

