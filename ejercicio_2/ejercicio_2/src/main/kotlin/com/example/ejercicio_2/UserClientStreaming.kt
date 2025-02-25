package com.example.ejercicio_2

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {
    // Crear un canal de comunicación con el servidor
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext() // Usar conexión no segura (solo para pruebas)
        .build()

    // Crear un stub para el servicio UserService
    val stub = UserServiceGrpc.newBlockingStub(channel)

    // Crear la solicitud para listar usuarios
    val request = ListUsersRequest.newBuilder().setFilter("all").build()

    // Llamar al método de transmisión del servidor
    val responses = stub.listUsers(request)

    // Procesar las respuestas transmitidas
    println("Received users:")
    responses.forEach { userResponse ->
        println("User: ${userResponse.name} (${userResponse.email})")
    }

    // Cerrar el canal de comunicación
    channel.shutdown()
}