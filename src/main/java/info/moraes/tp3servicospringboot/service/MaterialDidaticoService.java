package info.moraes.tp3servicospringboot.service;

import info.moraes.tp3servicospringboot.model.MaterialDidatico;
import info.moraes.tp3servicospringboot.repository.MaterialDidaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDidaticoService {

    @Autowired
    private MaterialDidaticoRepository materialDidaticoRepository;

    @Cacheable(value = "materiaisDidaticos")
    public List<MaterialDidatico> listarMateriaisDidaticos() {
        return materialDidaticoRepository.findAll();
    }

    @Cacheable(value = "materiaisDidaticos", key = "#id")
    public Optional<MaterialDidatico> buscarMaterialDidaticoPorId(String id) {
        return materialDidaticoRepository.findById(id);
    }

    @CacheEvict(value = "materiaisDidaticos", allEntries = true)
    public MaterialDidatico criarMaterialDidatico(MaterialDidatico materialDidatico) {
        return materialDidaticoRepository.save(materialDidatico);
    }

    @CacheEvict(value = "materiaisDidaticos", key = "#id")
    public void deletarMaterialDidatico(String id) {
        materialDidaticoRepository.deleteById(id);
    }

    @CacheEvict(value = "materiaisDidaticos", key = "#id")
    public MaterialDidatico atualizarMaterialDidatico(String id, MaterialDidatico materialDidatico) {
        materialDidatico.setId(id);
        return materialDidaticoRepository.save(materialDidatico);
    }
}
