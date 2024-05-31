package info.moraes.tp3servicospringboot.service;

import info.moraes.tp3servicospringboot.model.Aluno;
import info.moraes.tp3servicospringboot.model.Curso;
import info.moraes.tp3servicospringboot.repository.AlunoRepository;
import info.moraes.tp3servicospringboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "alunos")
    public List<Aluno> buscarAlunos() {
        return alunoRepository.findAll();
    }

    @Cacheable(value = "alunos", key = "#id")
    public Optional<Aluno> buscarAlunoPorId(String id) {
        return alunoRepository.findById(id);
    }

    @CacheEvict(value = "alunos", allEntries = true)
    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @CacheEvict(value = "alunos", key = "#id")
    public void deletarAluno(String id) {
        alunoRepository.deleteById(id);
    }

    @CacheEvict(value = "alunos", key = "#id")
    public Aluno atualizarAluno(String id, Aluno alunoUpdate) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoUpdate.getNome());
            aluno.setCursos(alunoUpdate.getCursos());
            return alunoRepository.save(aluno);
        }).orElseGet(() -> {
            alunoUpdate.setId(id);
            return alunoRepository.save(alunoUpdate);
        });
    }

    @CacheEvict(value = "alunos", key = "#alunoId")
    public Curso adicionarCurso(String alunoId, Curso curso) {
        return alunoRepository.findById(alunoId).map(aluno -> {
            curso.setAlunoId(alunoId);
            Curso savedCurso = cursoRepository.save(curso);
            aluno.getCursos().add(savedCurso);
            alunoRepository.save(aluno);
            return savedCurso;
        }).orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + alunoId));
    }
}
