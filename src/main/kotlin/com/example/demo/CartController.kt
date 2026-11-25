package com.example.demo

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cart")
class CartController {

    private val cartItems = mutableListOf<Sock>()

    @GetMapping
    fun getCart(): List<Sock> = cartItems

    @PostMapping
    fun addToCart(@RequestBody sock: Sock): List<Sock> {
        cartItems.add(sock)
        return cartItems
    }

    @DeleteMapping
    fun clearCart(): List<Sock> {
        cartItems.clear()
        return cartItems
    }
}
