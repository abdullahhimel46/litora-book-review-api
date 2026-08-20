# 📚 Litora — Book Management & Review API

> A robust, secure, and production-ready RESTful backend service for managing books, reviews, and user access.

---

## 🌟 What is Litora? (At a Glance)

Imagine a modern digital library or bookstore system. **Litora** is the smart engine running behind the scenes that:
- 📖 **Organizes Books:** Keeps an up-to-date catalog of all books (titles, authors, genres, release years).
- 🔐 **Protects Information:** Ensures only authorized members (Users, Admins, Super Admins) can perform specific actions (like adding or deleting books).
- ⚡ **Connects Easily:** Provides standard endpoints so that Mobile Apps, Websites, or Desktop systems can easily plug in and communicate with it.

---

## ✨ Key Highlights

- **🔒 Role-Based Access Control:** Strict permissions ensuring regular users can browse books, while only Admins/Super Admins can create, modify, or delete entries.
- **🛡️ JWT (JSON Web Token) Security:** Industry-standard stateless token authentication for secure communication.
- **⚡ High Performance Mapping:** Powered by **MapStruct** for zero-overhead, ultra-fast data conversions.
- **📖 Interactive API Documentation:** Live, interactive Swagger/OpenAPI dashboard for testing endpoints straight from the browser.
- **🗄️ PostgreSQL Database:** Enterprise-grade relational database for reliable and scalable data persistence.

---

## 👥 User Roles & Permissions

| Role | Browse / View Books | Add Books | Update Books | Delete Books |
| :--- | :---: | :---: | :---: | :---: |
| 👤 **User** | ✅ | ❌ | ❌ | ❌ |
| 🛡️ **Admin** | ✅ | ✅ | ✅ | ❌ |
| 👑 **Super Admin** | ✅ | ✅ | ✅ | ✅ |

---

## 🛠️ Technology Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Java 21 (LTS) |
| **Framework** | Spring Boot 4.x / 3.x (Web, Data JPA, Security) |
| **Database** | PostgreSQL |
| **Object Mapping** | MapStruct & Lombok |
| **Security & Auth** | Spring Security 6 + JJWT (JSON Web Token) |
| **API Documentation**| SpringDoc OpenAPI / Swagger UI |
| **Build Tool** | Gradle |

---

## 🌐 API Overview

### 1. 🔐 Authentication (`/api/v1/auth`)
- `POST /api/v1/auth/login` — Log in with username and password to receive a secure JWT access token.

### 2. 📚 Books Catalog (`/api/v1/books`)
- `GET /api/v1/books` — Fetch a list of all available books *(User / Admin)*.
- `GET /api/v1/books/{id}` — Fetch detailed info for a single book by ID *(User / Admin)*.
- `POST /api/v1/books` — Add a new book to the catalog *(Admin / Super Admin)*.
- `PUT /api/v1/books/{id}` — Update existing book information *(Admin)*.
- `DELETE /api/v1/books/{id}` — Remove a book from the catalog *(Super Admin only)*.

---

## 🚀 Getting Started (How to Run)

### 📋 Prerequisites
Before running the application, make sure you have:
1. **Java JDK 21** installed.
2. **PostgreSQL** installed and running on port `5432`.
3. A database created named `litora_db`.

---

### ⚙️ Step 1: Database Configuration
Update your database credentials in `src/main/resources/application.yaml` (if different from default):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/litora_db
    username: postgres
    password: your_postgres_password
```

---

### 💻 Step 2: Run the Application

#### On Windows (PowerShell / CMD):
```bash
./gradlew bootRun
```

#### On Linux / macOS:
```bash
./gradlew bootRun
```

Once started, the server will be running live at: **`http://localhost:8080`**

---

## 📑 Interactive Documentation (Swagger UI)

Once the application is running, open your web browser and navigate to:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

From here, you can:
- Explore all available API endpoints.
- Authorize with your JWT token.
- Send real requests and see live responses directly from your browser.

---

## 📁 Project Architecture

```text
src/main/java/com/litora/bookreview/
├── 📂 config          # Security, JWT filters & Swagger configuration
├── 📂 controller      # REST endpoints (Auth & Book APIs)
├── 📂 dto             # Request & Response data structures (Records)
├── 📂 exception       # Global error handler and custom exception classes
├── 📂 mapper          # MapStruct interface definitions
├── 📂 model           # Database JPA entities
├── 📂 repository      # Spring Data JPA database repositories
└── 📂 service         # Business logic layer and JWT handling
```

---

## 📄 License & Author

- **Author:** [Abdullah Himel](https://github.com/abdullahhimel46)
- **Project:** Litora Book Review API
- **License:** MIT License
