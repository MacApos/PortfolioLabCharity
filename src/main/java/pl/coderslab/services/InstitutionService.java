package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.domain.Institution;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {
    InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> findAll(){
        return institutionRepository.findAll();
    }

}
