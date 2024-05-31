package info.moraes.tp3servicospringboot.Controller;

import info.moraes.tp3servicospringboot.model.MaterialDidatico;
import info.moraes.tp3servicospringboot.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiaisDidaticos")
public class MaterialDidaticoController {

    @Autowired
    private MaterialDidaticoService materialDidaticoService;

    @PostMapping
    public MaterialDidatico criarMaterialDidatico(@RequestBody MaterialDidatico materialDidatico) {
        return materialDidaticoService.criarMaterialDidatico(materialDidatico);
    }

    @GetMapping
    public List<MaterialDidatico> listarMateriaisDidaticos() {
        return materialDidaticoService.listarMateriaisDidaticos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDidatico> buscarMaterialDidaticoPorId(@PathVariable String id) {
        return materialDidaticoService.buscarMaterialDidaticoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDidatico> atualizarMaterialDidatico(@PathVariable String id, @RequestBody MaterialDidatico materialDidaticoDetails) {
        return materialDidaticoService.buscarMaterialDidaticoPorId(id)
                .map(materialDidatico -> {
                    materialDidatico.setTitulo(materialDidaticoDetails.getTitulo());
                    materialDidatico.setDescricao(materialDidaticoDetails.getDescricao());
                    materialDidatico.setAutor(materialDidaticoDetails.getAutor());
                    materialDidaticoService.atualizarMaterialDidatico(id, materialDidatico);
                    return ResponseEntity.ok(materialDidatico);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMaterialDidatico(@PathVariable String id) {
        materialDidaticoService.deletarMaterialDidatico(id);
        return ResponseEntity.ok().build();
    }
}
