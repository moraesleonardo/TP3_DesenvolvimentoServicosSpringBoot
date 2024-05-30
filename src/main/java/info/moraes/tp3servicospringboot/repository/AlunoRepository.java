package info.moraes.tp3servicospringboot.repository;

import info.moraes.tp3servicospringboot.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
