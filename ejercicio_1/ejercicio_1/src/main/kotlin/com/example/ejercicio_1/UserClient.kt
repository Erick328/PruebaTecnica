package com.example.ejercicio_1

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val stub = UserServiceGrpc.newBlockingStub(channel)
    val request = GetUserRequest.newBuilder().setUserId("123").build()

    val response = stub.getUser(request)
    println("Response: $response")

    channel.shutdown()
}
