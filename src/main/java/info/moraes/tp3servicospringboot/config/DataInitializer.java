package info.moraes.tp3servicospringboot.config;

import info.moraes.tp3servicospringboot.model.Aluno;
import info.moraes.tp3servicospringboot.model.Curso;
import info.moraes.tp3servicospringboot.model.MaterialDidatico;
import info.moraes.tp3servicospringboot.repository.AlunoRepository;
import info.moraes.tp3servicospringboot.repository.CursoRepository;
import info.moraes.tp3servicospringboot.repository.MaterialDidaticoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MaterialDidaticoRepository materialDidaticoRepository;


    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            alunoRepository.deleteAll();
            cursoRepository.deleteAll();
            materialDidaticoRepository.deleteAll();

            Aluno aluno1 = new Aluno();
            aluno1.setId("1");
            aluno1.setNome("João");

            Aluno aluno2 = new Aluno();
            aluno2.setId("2");
            aluno2.setNome("Maria");

            alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));

            Curso curso1 = new Curso();
            curso1.setId("01");
            curso1.setNome("Matemática");
            curso1.setAlunoId(aluno1.getId());

            Curso curso2 = new Curso();
            curso2.setId("02");
            curso2.setNome("História");
            curso2.setAlunoId(aluno1.getId());

            Curso curso3 = new Curso();
            curso3.setId("03");
            curso3.setNome("Ciências");
            curso3.setAlunoId(aluno2.getId());

            cursoRepository.saveAll(Arrays.asList(curso1, curso2, curso3));

            aluno1.setCursos(Arrays.asList(curso1, curso2));
            aluno2.setCursos(Arrays.asList(curso3));

            alunoRepository.saveAll(Arrays.asList(aluno1, aluno2));

            MaterialDidatico material1 = new MaterialDidatico();
            material1.setId("001");
            material1.setTitulo("Introdução ao Spring Boot");
            material1.setDescricao("Material de estudo sobre Spring Boot");
            material1.setAutor("João Silva");

            MaterialDidatico material2 = new MaterialDidatico();
            material2.setId("002");
            material2.setTitulo("Avançado em MongoDB");
            material2.setDescricao("Material de estudo sobre MongoDB");
            material2.setAutor("Maria Oliveira");

            materialDidaticoRepository.saveAll(Arrays.asList(material1, material2));
        };
    }
}
