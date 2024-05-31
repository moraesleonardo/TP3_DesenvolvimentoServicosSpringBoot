package info.moraes.tp3servicospringboot.service;

import info.moraes.tp3servicospringboot.model.Curso;
import info.moraes.tp3servicospringboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "cursos")
    public List<Curso> buscarCursos() {
        return cursoRepository.findAll();
    }

    @Cacheable(value = "cursos", key = "#id")
    public Optional<Curso> buscarCursoPorId(String id) {
        return cursoRepository.findById(id);
    }

    @CacheEvict(value = "cursos", allEntries = true)
    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @CacheEvict(value = "cursos", key = "#id")
    public void deletarCurso(String id) {
        cursoRepository.deleteById(id);
    }

    @CacheEvict(value = "cursos", key = "#id")
    public Curso atualizarCurso(String id, Curso cursoUpdate) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoUpdate.getNome());
            curso.setAlunoId(cursoUpdate.getAlunoId());
            return cursoRepository.save(curso);
        }).orElseGet(() -> {
            cursoUpdate.setId(id);
            return cursoRepository.save(cursoUpdate);
        });
    }
}
