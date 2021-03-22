package br.com.belarmino.planeta.model

import javax.persistence.*

@Entity
@Table(name = "planeta")
data class Planeta (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nome:String = "",
    val clima: String = "",
    val terreno: String = ""
)