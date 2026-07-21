# 🛠️ Sistema de Gestión de Incidentes - API Backend

API RESTful desarrollada en **Java 21 / Spring Boot 3** para la gestión centralizada de tickets e incidentes de soporte técnico, implementando seguridad stateless con JWT y persistencia en MySQL.

---

## 🚀 Tecnologías Utilizadas

* **Java & Spring Boot:** Desarrollo backend y arquitectura REST.
* **Spring Security & JWT:** Autenticación y autorización basada en tokens.
* **Spring Data JPA / Hibernate:** ORM y persistencia de datos.
* **MySQL:** Base de datos relacional.
* **Swagger UI / OpenAPI:** Documentación interactiva de endpoints.
* **Lombok:** Reducción de código boilerplate.

---

## 🔒 Endpoints Principales

| Método | Endpoint | Descripción | Acceso |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Autenticación y generación de JWT | Público |
| `GET` | `/api/tickets` | Listar todos los tickets | Requiere JWT |
| `POST` | `/api/tickets` | Crear un nuevo ticket | Requiere JWT |
| `GET` | `/api/tickets/{id}` | Obtener ticket por ID | Requiere JWT |
| `PUT` | `/api/tickets/{id}/estado` | Actualizar estado de ticket | Requiere JWT |

---

## 📑 Documentación Swagger
Con la aplicación en ejecución, la documentación interactiva está disponible en:
`http://localhost:8080/swagger-ui.html`
