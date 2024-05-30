package info.moraes.tp3servicospringboot.Controller;

import info.moraes.tp3servicospringboot.repository.AlunoRepository;
import info.moraes.tp3servicospringboot.model.Curso;
import info.moraes.tp3servicospringboot.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {


    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/curso/{cursoId}")
    public Curso addCursoAluno(@PathVariable Long alunoId, @RequestBody Curso curso){
        return alunoRepository.findById(alunoId).map(aluno -> {
            curso.setAluno(aluno);
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com id: "+alunoId));
        //return null;
    }

    @GetMapping
    public List<Curso> getAllCursos(){
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Curso getCurso(@PathVariable Long id){
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id:" + id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id){
        return cursoRepository.findById(id).map(curso -> {
            cursoRepository.delete(curso);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com id:" + id));
    }

    @PutMapping("/{id}")
    public Curso updateCurso(@PathVariable Long id, @RequestBody Curso cursoUpdate){
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(cursoUpdate.getNome());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com id:" + id));
    }

    //BUSCAR OS PEDIDOS POR CLIENTE
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<?> getAllCursosAluno(@PathVariable Long alunoId) {
        List<Curso> cursos = cursoRepository.findByAlunoId(alunoId);
        if(cursos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursos);
    }

}
