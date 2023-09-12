package tech.java.dangeous_dragons.infrastructure.seed.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.infrastructure.seed.entity.SeedControl;
import tech.java.dangeous_dragons.infrastructure.seed.repository.SeedControlRepository;

@Service
@RequiredArgsConstructor
public class SeedControlService {

    private final SeedControlRepository seedControlRepository;

    public Boolean existsById(long id) {
        return seedControlRepository.existsById(id);
    }

    public void save(SeedControl seedControl) {
        seedControlRepository.save(seedControl);
    }

}
