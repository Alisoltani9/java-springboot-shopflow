# Shopflow

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-green?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue?style=flat-square&logo=postgresql)
![Redis](https://img.shields.io/badge/Redis-red?style=flat-square&logo=redis)
![Kafka](https://img.shields.io/badge/Apache_Kafka-black?style=flat-square&logo=apachekafka)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-005571?style=flat-square&logo=elasticsearch)
![Docker](https://img.shields.io/badge/Docker-blue?style=flat-square&logo=docker)
![AWS](https://img.shields.io/badge/AWS-EC2-orange?style=flat-square&logo=amazonaws)

A production-grade ecommerce REST API built with Spring Boot. Features JWT authentication, Redis caching, Kafka event streaming, Elasticsearch product search, and full Docker support.

> **Scaled version:** [Shopflow Microservices](https://github.com/Alisoltani9/shopflow-microservices) — same domain rebuilt as independent microservices with Kubernetes.

---

## Branches

| Branch | Description |
|---|---|
| `main` | Full version with Kafka, Elasticsearch, Redis |
| `aws` | Lightweight version deployed on AWS (no Kafka/Elasticsearch) |

---

## Live Demo
> Running on AWS EC2 (lightweight version — Kafka and Elasticsearch require additional infrastructure)

Base URL: `http://13.63.198.160`


## Features

- **JWT Authentication** — stateless auth with role-based access control (USER / ADMIN)
- **Product Management** — full CRUD with Redis caching for fast repeated reads
- **Order Management** — place orders with Kafka event publishing on every transaction
- **Elasticsearch Search** — fast full-text product search beyond SQL LIKE queries
- **Global Exception Handling** — clean JSON error responses with proper HTTP status codes
- **Input Validation** — request validation with meaningful error messages
- **Docker** — fully containerized with one-command startup via docker-compose

---

## Architecture

```
┌─────────────────────────────────────┐
│            Client (Postman)          │
└─────────────────┬───────────────────┘
                  │ HTTP
                  ▼
┌─────────────────────────────────────┐
│          Spring Boot API             │
│                                     │
│  AuthController  ──►  UserService   │
│  ProductController ► ProductService │
│  OrderController ──►  OrderService  │
└──────┬──────────┬──────┬────────────┘
       │          │      │
       ▼          ▼      ▼
 PostgreSQL     Redis   Kafka
 (main DB)    (cache) (events)
       │
       ▼
 Elasticsearch
  (search)
```

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 + Spring Boot 3.5 | Core framework |
| Spring Security + JWT | Stateless authentication |
| PostgreSQL | Primary database |
| Redis | Product caching |
| Apache Kafka | Order event streaming |
| Elasticsearch | Full-text product search |
| Flyway | Database migrations |
| Docker + Docker Compose | Containerization |
| Lombok | Boilerplate reduction |

---

## API Endpoints

### Auth
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/auth/register` | Public | Register new user |
| POST | `/api/auth/login` | Public | Login and receive JWT |

### Products
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/products` | Public | List all products |
| GET | `/api/products/{id}` | Public | Get product by ID |
| GET | `/api/products/search?q=` | Public | Elasticsearch full-text search |
| POST | `/api/products` | ADMIN | Create product |
| DELETE | `/api/products/{id}` | ADMIN | Delete product |

### Orders
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/orders` | Authenticated | Place an order |
| GET | `/api/orders/my` | Authenticated | Get user's orders |

---

## Running with Docker

The easiest way to run the full stack with one command:

```bash
git clone https://github.com/Alisoltani9/java-springboot-shopflow.git
cd java-springboot-shopflow

# Build the jar
./mvnw clean package -DskipTests

# Copy environment variables
cp .env.example .env
# Edit .env with your values

# Start everything (app + PostgreSQL + Redis + Kafka + Elasticsearch)
docker-compose up --build
```

The API will be available at `http://localhost:8080`.

---

## Running Locally

### Prerequisites
- Java 21
- Docker (for infrastructure)
- Maven

### 1. Start infrastructure

```bash
docker-compose up postgres redis kafka elasticsearch
```

### 2. Run the application

```bash
./mvnw spring-boot:run
```

---

## Usage Example

### 1. Register
```bash
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "ali",
  "password": "123456"
}
```

### 2. Login
```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "ali",
  "password": "123456"
}
```

Copy the JWT token from the response.

### 3. Browse products
```bash
GET http://localhost:8080/api/products
```

### 4. Search products
```bash
GET http://localhost:8080/api/products/search?q=iphone
```

### 5. Place an order
```bash
POST http://localhost:8080/api/orders
Authorization: Bearer <your-jwt-token>
Content-Type: application/json

{
  "productId": 1,
  "quantity": 2
}
```

---

## Project Structure

```
src/main/java/soltani/code/shopflow/
├── controller/
│   ├── AuthController.java
│   ├── ProductController.java
│   └── OrderController.java
├── service/
│   ├── UserService.java
│   ├── ProductService.java
│   ├── OrderService.java
│   ├── ProductSearchService.java
│   └── NotificationService.java
├── entity/
│   ├── User.java
│   ├── Product.java
│   ├── ProductDocument.java
│   ├── Order.java
│   └── OrderItem.java
├── repository/
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── ProductSearchRepository.java
│   └── OrderRepository.java
├── security/
│   ├── JwtUtil.java
│   ├── JwtAuthFilter.java
│   └── SecurityConfig.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── dto/
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   └── OrderEvent.java
└── config/
    └── KafkaConfig.java
```

---

## Author

**Ali Soltani**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Ali_Soltani-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/ali-soltani-841115204/)
[![GitHub](https://img.shields.io/badge/GitHub-Alisoltani9-black?style=flat-square&logo=github)](https://github.com/Alisoltani9)
