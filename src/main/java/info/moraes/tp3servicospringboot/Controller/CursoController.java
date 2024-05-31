package info.moraes.tp3servicospringboot.Controller;

import info.moraes.tp3servicospringboot.model.Curso;
import info.moraes.tp3servicospringboot.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.buscarCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable String id) {
        return cursoService.buscarCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.salvarCurso(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable String id, @RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.atualizarCurso(id, curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable String id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.ok().build();
    }
}
