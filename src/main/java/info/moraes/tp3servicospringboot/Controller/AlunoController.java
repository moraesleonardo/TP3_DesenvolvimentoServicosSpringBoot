package info.moraes.tp3servicospringboot.Controller;


import info.moraes.tp3servicospringboot.model.Aluno;
import info.moraes.tp3servicospringboot.model.Curso;
import info.moraes.tp3servicospringboot.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.buscarAlunos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable String id) {
        return alunoService.buscarAlunoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.salvarAluno(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable String id, @RequestBody Aluno aluno) {
        return ResponseEntity.ok(alunoService.atualizarAluno(id, aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable String id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{alunoId}/cursos")
    public Curso addCursoToAluno(@PathVariable String alunoId, @RequestBody Curso curso) {
        return alunoService.adicionarCurso(alunoId, curso);
    }
}
