package com.example.ejercicio_2

import io.grpc.Server
import io.grpc.ServerBuilder

class UserServer(private val port: Int) {
    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(UserService())
        .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(Thread {
            println("*** shutting down gRPC server since JVM is shutting down")
            this@UserServer.stop()
            println("*** server shut down")
        })
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}

fun main() {
    val port = 50051
    val server = UserServer(port)
    server.start()
    server.blockUntilShutdown()
}