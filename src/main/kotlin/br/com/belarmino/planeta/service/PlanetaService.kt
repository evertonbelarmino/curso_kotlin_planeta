package br.com.belarmino.planeta.service

import br.com.belarmino.planeta.model.Planeta

interface PlanetaService {
    fun getById(id: Long): Planeta?
    fun create(planeta: Planeta)
    fun delete(id: Long)
    fun update(planeta: Planeta)
    fun findAll(start: Int, size: Int): List<Planeta>
    fun count():Long
    fun getPlanetaByNome(nome: String): Planeta
}