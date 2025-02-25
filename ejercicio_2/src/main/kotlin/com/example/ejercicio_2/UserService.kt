package com.example.ejercicio_2

import io.grpc.stub.StreamObserver

class UserService : UserServiceGrpc.UserServiceImplBase() {

    // Implementación del método unario existente
    override fun getUser(request: GetUserRequest, responseObserver: StreamObserver<UserResponse>) {
        val userResponse = UserResponse.newBuilder()
            .setUserId("123")
            .setName("Alice")
            .setEmail("alice@example.com")
            .build()

        responseObserver.onNext(userResponse)
        responseObserver.onCompleted()
    }

    // Implementación del nuevo método de transmisión del servidor
    override fun listUsers(request: ListUsersRequest, responseObserver: StreamObserver<UserResponse>) {
        // Simulamos una lista de usuarios
        val users = listOf(
            UserResponse.newBuilder().setUserId("1").setName("Alice").setEmail("alice@example.com").build(),
            UserResponse.newBuilder().setUserId("2").setName("Bob").setEmail("bob@example.com").build(),
            UserResponse.newBuilder().setUserId("3").setName("Charlie").setEmail("charlie@example.com").build()
        )

        // Envía cada usuario al cliente
        users.forEach { user ->
            responseObserver.onNext(user)
        }

        // Indica que la transmisión ha terminado
        responseObserver.onCompleted()
    }

    override fun userActions(responseObserver: StreamObserver<UserUpdate>): StreamObserver<UserAction> {
        return object : StreamObserver<UserAction> {
            override fun onNext(action: UserAction) {
                val update = UserUpdate.newBuilder()
                    .setUserId(action.userId)
                    .setStatus("processed: ${action.action}")
                    .build()
    
                responseObserver.onNext(update)
            }
    
            override fun onError(t: Throwable) {
                println("Error: $t")
            }
    
            override fun onCompleted() {
                responseObserver.onCompleted()
            }
        }
    }
}