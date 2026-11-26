package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SockShopUnitTests {

    @Test
    fun `hello endpoint greets user properly`() {
        val app = DemoApplication()
        assertEquals("Hello World!", app.hello())
        assertEquals("Hello Umesh!", app.hello("Umesh"))
    }

    @Test
    fun `cart adds retrieves and clears socks properly`() {
        val cartController = CartController()
        val testSock = Sock(
            id = "101",
            name = "Classic Wool Sock",
            description = "Warm everyday wool sock",
            price = 12.99,
            count = 10,
            tag = listOf("wool", "winter")
        )

        cartController.addToCart(testSock)
        assertEquals(1, cartController.getCart().size)
        assertEquals("Classic Wool Sock", cartController.getCart()[0].name)

        cartController.clearCart()
        assertTrue(cartController.getCart().isEmpty(), "Cart should be empty after clearing")
    }
}
