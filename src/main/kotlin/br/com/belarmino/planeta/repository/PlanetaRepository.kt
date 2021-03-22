package br.com.belarmino.planeta.repository

import br.com.belarmino.planeta.model.Planeta
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PlanetaRepository: PagingAndSortingRepository<Planeta, Long> {

    @Query(value = "select p from Planeta p where p.nome = :nome")
    fun findPlanetaByNome(@Param(value = "nome") nome: String): Planeta

}