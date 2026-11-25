package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalogue")
class CatalogueController {

    private val socks = listOf(
        Sock("1", "Holy Socks", "Socks with breathable holes.", 12.99, 50, listOf("magic", "cotton")),
        Sock("2", "Colourful Stripes", "Bright striped socks.", 9.50, 120, listOf("cotton", "sale")),
        Sock("3", "YouTube Socks", "Creator edition comfort socks.", 15.00, 30, listOf("formal"))
    )

    @GetMapping
    fun getAllSocks(): List<Sock> = socks

    @GetMapping("/{id}")
    fun getSockById(@PathVariable id: String): Sock? =
        socks.find { it.id == id }
}
