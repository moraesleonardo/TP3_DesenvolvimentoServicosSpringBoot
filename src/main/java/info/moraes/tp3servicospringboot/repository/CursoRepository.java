package info.moraes.tp3servicospringboot.repository;

import info.moraes.tp3servicospringboot.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByAlunoId(Long alunoId);

}
