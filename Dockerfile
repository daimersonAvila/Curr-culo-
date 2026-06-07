# ============================================================
# ETAPA 1: BUILD — compila el JAR con Maven
# ============================================================
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copia primero el wrapper y pom para aprovechar cache de capas
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

# Copia el código fuente y compila
COPY src/ src/
RUN ./mvnw clean package -DskipTests -q

# ============================================================
# ETAPA 2: RUNTIME — imagen final ligera (sin JDK, solo JRE)
# ============================================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Usuario no-root por seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Solo copia el JAR compilado
COPY --from=build /app/target/curriculum-web-1.0.0.jar app.jar

# Puerto que expone la app
EXPOSE 8080

# Opciones JVM para contenedor con poca RAM (plan free de Render)
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
