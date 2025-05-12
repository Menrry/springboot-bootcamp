# Usamos una imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Damos permisos de ejecución al mvnw
RUN chmod +x mvnw

# Construimos el proyecto sin tests
RUN ./mvnw clean package -DskipTests

# Exponemos el puerto (opcional, Railway lo asigna dinámicamente)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/springboot-railway-0.0.1-SNAPSHOT.jar"]


