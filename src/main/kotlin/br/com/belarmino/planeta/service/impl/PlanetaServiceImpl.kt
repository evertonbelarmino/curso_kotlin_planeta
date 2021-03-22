package br.com.belarmino.planeta.service.impl

import br.com.belarmino.planeta.model.Planeta
import br.com.belarmino.planeta.repository.PlanetaRepository
import br.com.belarmino.planeta.service.PlanetaService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import springfox.documentation.annotations.Cacheable

@Component
class PlanetaServiceImpl(val planetaRepository: PlanetaRepository): PlanetaService {
    override fun getById(id: Long): Planeta? {
        return this.planetaRepository.findByIdOrNull(id)
    }

    @CacheEvict("planeta", allEntries = true)
    override fun create(planeta: Planeta) {
        this.planetaRepository.save(planeta)
    }

    override fun delete(id: Long) {
        this.planetaRepository.deleteById(id)
    }

    override fun update(planeta: Planeta) {
        create(planeta)
    }

    @Cacheable("planeta")
    override fun findAll(start: Int, size: Int): List<Planeta> {
        val pageable: Pageable = PageRequest.of(start,size)
        return this.planetaRepository.findAll(pageable).toList()
    }

    override fun count(): Long {
        return this.planetaRepository.count()
    }

    override fun getPlanetaByNome(nome: String): Planeta {
        return this.planetaRepository.findPlanetaByNome(nome)
    }

}