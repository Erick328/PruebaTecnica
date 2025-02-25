# PruebaTecnica
Este repositorio contiene dos ejercicios que demuestran el uso de gRPC en Kotlin:
1. **Ejercicio 1**: RPC unario (servidor y cliente básico).
2. **Ejercicio 2**: Transmisión del servidor (streaming).
## Ejecución de los ejercicios

### Ejercicio 1: RPC unario

1. **Compilar el proyecto:**
   ```bash
   ./gradlew build
   ./gradlew run --args='com.example.user.UserServer'
### Ejercicio 2: Transmision del servidor

1. **Compilar el proyecto:**
   ```bash
   ./gradlew build
   ./gradlew run --args='com.example.user.UserServer'
   ./gradlew run --args='com.example.user.UserClientStreaming'
