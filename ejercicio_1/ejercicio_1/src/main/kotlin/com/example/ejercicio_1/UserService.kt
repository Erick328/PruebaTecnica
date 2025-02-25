package com.example.user

import io.grpc.stub.StreamObserver

class UserService : UserServiceGrpc.UserServiceImplBase() {
    override fun getUser(request: GetUserRequest, responseObserver: StreamObserver<UserResponse>) {
        val userResponse = UserResponse.newBuilder()
            .setUserId("123")
            .setName("Alice")
            .setEmail("alice@example.com")
            .build()

        responseObserver.onNext(userResponse)
        responseObserver.onCompleted()
    }
}