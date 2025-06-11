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

## 📚 Endpoints

### 👤 Autenticación de Usuarios

| Método | Endpoint                         | Descripción                          |
|--------|----------------------------------|--------------------------------------|
| POST   | `/api/v1/auth/register`          | Registro de nuevo usuario/admin      |
| POST   | `/api/v1/auth/authenticate`      | Autenticación y obtención de token   |

---

### 🧑‍💼 Usuarios

| Método | Endpoint      | Descripción                | Autenticación |
|--------|---------------|----------------------------|----------------|
| POST   | `/usuarios`   | Crear nuevo usuario        | ❌             |

---

### 🏷️ Categorías

| Método | Endpoint                         | Descripción                         | Autenticación |
|--------|----------------------------------|-------------------------------------|---------------|
| POST   | `/categoria`                     | Crear nueva categoría               | ✅ `admin_token` |
| GET    | `/categoria`                     | Listar todas las categorías         | ❌             |
| GET    | `/categoria/nombre/:nombre`      | Buscar categoría por nombre         | ❌             |

---

### 🎫 Eventos

| Método | Endpoint                          | Descripción                               | Autenticación     |
|--------|-----------------------------------|-------------------------------------------|-------------------|
| POST   | `/eventos`                        | Crear nuevo evento                        | ✅ `admin_token`  |
| GET    | `/eventos`                        | Listar todos los eventos                  | ✅ `user_token`   |
| GET    | `/eventos/buscar?nombre=Recital`  | Buscar eventos por nombre                 | ✅ `user_token`   |
| GET    | `/eventos/buscar?artista=Artista` | Buscar eventos por artista                | ✅ `user_token`   |
| GET    | `/eventos/disponibles`            | Obtener eventos disponibles               | ✅ `user_token`   |
| PUT    | `/eventos/:id`                    | Editar un evento existente                | ✅ `admin_token`  |
| DELETE | `/eventos/:id`                    | Eliminar evento                           | ✅ `admin_token`  |

---

### 🛒 Compras

| Método | Endpoint     | Descripción         | Autenticación |
|--------|--------------|---------------------|---------------|
| POST   | `/compras`   | Registrar una compra| ✅ `user_token` |

---

## 📥 Instalación y ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/LauraMont/API-UADE-G1.git
```

### 2. Configura la base de datos
Modificá src/main/resources/application.properties:

```bash
spring.application.name=marketplace
spring.datasource.url=jdbc:mysql://localhost:3306/soundpass
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=your_mysql_pass
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=4002
application.security.jwt.secretKey:<key>

application.security.jwt.expiration: 86400000

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
