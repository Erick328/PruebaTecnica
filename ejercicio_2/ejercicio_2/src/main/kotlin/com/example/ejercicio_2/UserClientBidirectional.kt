package com.example.ejercicio_2

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver

fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val stub = UserServiceGrpc.newStub(channel)

    val requestObserver = stub.userActions(object : StreamObserver<UserUpdate> {
        override fun onNext(update: UserUpdate) {
            println("Received update: ${update.status}")
        }

        override fun onError(t: Throwable) {
            println("Error: $t")
        }

        override fun onCompleted() {
            println("Stream completed")
        }
    })

    listOf("login", "logout", "update").forEach { action ->
        requestObserver.onNext(UserAction.newBuilder().setUserId("123").setAction(action).build())
    }

    requestObserver.onCompleted()
    channel.shutdown()
}