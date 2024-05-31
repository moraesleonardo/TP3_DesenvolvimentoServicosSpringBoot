package info.moraes.tp3servicospringboot.repository;

import info.moraes.tp3servicospringboot.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CursoRepository extends MongoRepository<Curso, String> {
    List<Curso> findByAlunoId(String alunoId);
}
