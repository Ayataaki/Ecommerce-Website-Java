# E-Commerce Platform

A full-stack e-commerce application built with Spring Boot 3 (Java 21) backend and React + Vite + Tailwind CSS frontend.

## 🚀 Features

### User Features
- **Authentication & Authorization**
  - JWT-based authentication
  - User registration and login
  - Role-based access control (Admin/User)
  - Profile management

- **Product Browsing**
  - Browse products by category
  - Search products
  - Filter by price, rating
  - Sort products
  - Product details with images

- **Shopping Cart**
  - Add/remove items
  - Update quantities
  - Persistent cart (saved to database)

- **Order Management**
  - Place orders
  - View order history
  - Track order status
  - Multiple payment methods (COD, Card, UPI)

- **Reviews & Ratings**
  - Write product reviews
  - Star ratings
  - Verified purchase badges

### Admin Features
- **Dashboard**
  - Overview statistics
  - Recent orders
  - Quick actions

- **Product Management**
  - Create, edit, delete products
  - Manage product images
  - Set pricing and discounts
  - Stock management

- **Category Management**
  - Create category hierarchy
  - Edit/delete categories

- **Order Management**
  - View all orders
  - Update order status
  - Order details

- **User Management**
  - View all users
  - Change user roles
  - Activate/deactivate users

## 🛠️ Tech Stack

### Backend
- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Security** - JWT authentication
- **Spring Data MongoDB**
- **Lombok** - Boilerplate reduction
- **Maven** - Build tool

### Frontend
- **React 18**
- **Vite 5** - Build tool
- **Tailwind CSS 3.4** - Styling
- **React Router 6** - Routing
- **Zustand** - State management
- **Axios** - HTTP client
- **Headless UI** - Accessible components
- **Heroicons** - Icons
- **React Hot Toast** - Notifications

### Database
- **MongoDB** - NoSQL database

## 📁 Project Structure

```
├── backend/
│   └── src/main/java/com/ecommerce/
│       ├── config/           # Configuration classes
│       ├── controller/       # REST controllers
│       ├── dto/              # Data Transfer Objects
│       │   ├── request/      # Request DTOs
│       │   └── response/     # Response DTOs
│       ├── exception/        # Exception handling
│       ├── model/            # MongoDB documents
│       ├── repository/       # Data repositories
│       ├── security/         # JWT & Security
│       └── service/          # Business logic
│
├── frontend/
│   └── src/
│       ├── components/       # Reusable components
│       │   ├── auth/         # Auth guards
│       │   ├── layout/       # Layout components
│       │   ├── product/      # Product components
│       │   ├── category/     # Category components
│       │   └── ui/           # UI components
│       ├── pages/            # Page components
│       │   └── admin/        # Admin pages
│       ├── services/         # API services
│       └── store/            # Zustand stores
```

## 🚀 Getting Started

### Prerequisites
- Java 21 JDK
- Node.js 18+
- MongoDB 6+
- Maven 3.9+

### Backend Setup

1. **Configure MongoDB**
   
   Update `backend/src/main/resources/application.yml`:
   ```yaml
   spring:
     data:
       mongodb:
         uri: mongodb://localhost:27017/ecommerce
   ```

2. **Configure JWT Secret**
   
   Update the JWT secret in `application.yml`:
   ```yaml
   app:
     jwt:
       secret: your-256-bit-secret-key-here
   ```

3. **Build and Run**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```
   
   The backend will start at `http://localhost:8080`

### Frontend Setup

1. **Install Dependencies**
   ```bash
   cd frontend
   npm install
   ```

2. **Run Development Server**
   ```bash
   npm run dev
   ```
   
   The frontend will start at `http://localhost:5173`

3. **Build for Production**
   ```bash
   npm run build
   ```

## 🔗 API Endpoints

### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login user |
| GET | `/api/auth/me` | Get current user |

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/featured` | Get featured products |
| GET | `/api/products/search` | Search products |
| GET | `/api/products/category/{id}` | Get by category |
| POST | `/api/products` | Create product (Admin) |
| PUT | `/api/products/{id}` | Update product (Admin) |
| DELETE | `/api/products/{id}` | Delete product (Admin) |

### Categories
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | Get all categories |
| GET | `/api/categories/{id}` | Get category by ID |
| GET | `/api/categories/root` | Get root categories |
| POST | `/api/categories` | Create category (Admin) |
| PUT | `/api/categories/{id}` | Update category (Admin) |
| DELETE | `/api/categories/{id}` | Delete category (Admin) |

### Cart
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/cart` | Get user's cart |
| POST | `/api/cart/items` | Add item to cart |
| PUT | `/api/cart/items/{productId}` | Update item quantity |
| DELETE | `/api/cart/items/{productId}` | Remove item |
| DELETE | `/api/cart` | Clear cart |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/orders/my-orders` | Get user's orders |
| GET | `/api/orders/{id}` | Get order by ID |
| POST | `/api/orders` | Create order |
| PUT | `/api/orders/{id}/cancel` | Cancel order |

### Reviews
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/reviews/product/{id}` | Get product reviews |
| POST | `/api/reviews` | Create review |
| PUT | `/api/reviews/{id}` | Update review |
| DELETE | `/api/reviews/{id}` | Delete review |

### Admin
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/dashboard` | Dashboard stats |
| GET | `/api/admin/users` | Get all users |
| GET | `/api/admin/orders` | Get all orders |
| PUT | `/api/admin/orders/{id}/status` | Update order status |
| PUT | `/api/admin/users/{id}/role` | Update user role |

## 🚀 Deployment

### Backend Deployment (Render)

1. Create a new Web Service on Render
2. Connect your GitHub repository
3. Configure:
   - **Build Command**: `cd backend && mvn clean install -DskipTests`
   - **Start Command**: `cd backend && java -jar target/*.jar`
4. Add environment variables:
   - `MONGODB_URI`
   - `JWT_SECRET`
   - `SPRING_PROFILES_ACTIVE=prod`

### Frontend Deployment (Vercel/Netlify)

1. Connect your GitHub repository
2. Configure:
   - **Build Command**: `cd frontend && npm run build`
   - **Publish Directory**: `frontend/dist`
3. Add environment variable:
   - `VITE_API_URL` - Your backend URL

### MongoDB Atlas

1. Create a cluster at [MongoDB Atlas](https://www.mongodb.com/atlas)
2. Create a database user
3. Whitelist your IP addresses (or 0.0.0.0/0 for all)
4. Get the connection string
5. Update your backend configuration

## 🔒 Security

- All passwords are hashed using BCrypt
- JWT tokens expire after 24 hours
- CORS is configured for the frontend domain
- Admin routes are protected with role-based access
- Input validation on all endpoints
- MongoDB injection prevention through Spring Data

## 📝 Environment Variables

### Backend
| Variable | Description | Default |
|----------|-------------|---------|
| `MONGODB_URI` | MongoDB connection string | `mongodb://localhost:27017/ecommerce` |
| `JWT_SECRET` | JWT signing secret | - |
| `JWT_EXPIRATION` | Token expiration (ms) | `86400000` |
| `SERVER_PORT` | Server port | `8080` |

### Frontend
| Variable | Description | Default |
|----------|-------------|---------|
| `VITE_API_URL` | Backend API URL | `http://localhost:8080/api` |

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Built with ❤️ using Spring Boot and React
