package info.moraes.tp3servicospringboot.repository;

import info.moraes.tp3servicospringboot.model.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
}
