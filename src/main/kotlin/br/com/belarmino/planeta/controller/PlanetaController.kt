package br.com.belarmino.planeta.controller

import br.com.belarmino.planeta.model.ErrorMessage
import br.com.belarmino.planeta.model.Planeta
import br.com.belarmino.planeta.service.PlanetaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/planeta")
class PlanetaController {

    @Autowired
    lateinit var planetaService: PlanetaService

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long): ResponseEntity<Any> {
        var planeta = this.planetaService.getById(id)

        return if(planeta != null)
            return ResponseEntity(planeta, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Planeta Não Encontrado", "Planeta ${id} não Localizado"), HttpStatus.NOT_FOUND)
    }

    @PostMapping()
    fun create(@RequestBody planeta: Planeta): ResponseEntity<Planeta>{
        this.planetaService.create(planeta)
        return ResponseEntity(planeta, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): ResponseEntity<Unit>{
        var status =  HttpStatus.NOT_FOUND
        if(this.planetaService.getById(id) != null){
            status =  HttpStatus.ACCEPTED
            this.planetaService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Long,  @RequestBody planeta: Planeta): ResponseEntity<Planeta>{
        var status =  HttpStatus.NOT_FOUND
        if(this.planetaService.getById(id) != null){
            this.planetaService.update(planeta)
            status =  HttpStatus.ACCEPTED
        }
        return ResponseEntity(planeta, status)
    }

    @GetMapping()
    fun getAll(@RequestParam(required = false, defaultValue = "0")start: Int,
               @RequestParam(required = false, defaultValue = "3")size: Int)
            : ResponseEntity<List<Planeta>>{

        var listaPlanetas = this.planetaService.findAll(start,size)
        var status = if(listaPlanetas.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(listaPlanetas, status)
    }

    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String,Long>> =
        ResponseEntity.ok().body(mapOf("count" to this.planetaService.count()))

    @GetMapping("/nome-planeta")
    fun getPlanetaByNome(@RequestParam nome: String) = this.planetaService.getPlanetaByNome(nome)
}