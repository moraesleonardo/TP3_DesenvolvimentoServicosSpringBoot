package info.moraes.tp3servicospringboot.Controller;

import java.util.List;
import info.moraes.tp3servicospringboot.model.Aluno;
import info.moraes.tp3servicospringboot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/alunos")

public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos(){
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno getAlunoById(@PathVariable Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado " + id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado " + id));
        alunoRepository.delete(aluno);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    @PutMapping("/{id}")
    public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno aluno){
        Aluno cli = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        cli.setNome(aluno.getNome());
        cli.setCursos(aluno.getCursos());
        //Vamos ter que implementar depois a atualização dos cursos
        return alunoRepository.save(cli);
    }
}
