# 🎟️ SoundPass

Este proyecto forma parte de una materia de desarrollo de software y representa un marketplace para la **reserva y compra de entradas para conciertos**. Fue desarrollado con **Spring Boot** y **Java**.

## 🚀 Objetivo

Permitir a los usuarios:
- Ver eventos disponibles
- Registrarse
- Autenticarse 
- Registrar compras

## ⚙️ Tecnologías utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL** 
- **Maven**
- **Postman** 

---

## 🧑‍💻 Funcionalidades principales

- 🔎 Listar conciertos disponibles
- 📅 Crear eventos (admin)
- 🧍‍♂️ Registro de usuarios
- 🎟️ Reserva de entradas
- 💳 Registro de compras
- 📜 Consultar historial de compras por usuario

---

## 📦 Endpoints principales (ejemplos)

| Método | Endpoint              | Descripción                         |
|--------|-----------------------|-------------------------------------|
| GET    | `/eventos`            | Listar todos los eventos            |
| POST   | `/usuarios`           | Registrar nuevo usuario             |
| POST   | `/compras`            | Crear nueva compra                  |

---

## 📥 Instalación y ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/LauraMont/API-UADE-G1.git
```

### 2. Configura la base de datos
Modificá src/main/resources/application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/marketplace
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
```

### 3. Ejecutá la aplicación
```bash
./mvnw spring-boot:run
```

## 🧠 Equipo de trabajo
* Laura Montaño
* Rocio Bottinelli
* David Arambulo Avila
* Cecilia Taboada 
