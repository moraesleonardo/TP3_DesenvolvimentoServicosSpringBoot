package info.moraes.tp3servicospringboot.repository;

import info.moraes.tp3servicospringboot.model.MaterialDidatico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaterialDidaticoRepository extends MongoRepository<MaterialDidatico, String> {
}
