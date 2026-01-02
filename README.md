# 🛒 RubikStore - E-Commerce Management System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

##  Giới thiệu

RubikStore là một hệ thống quản lý cửa hàng thương mại điện tử được xây dựng bằng Spring Boot, tích hợp JWT authentication, role-based access control (RBAC), và đầy đủ các chức năng CRUD cho sản phẩm, đơn hàng, và giỏ hàng.

##  Tính năng chính

###  Xác thực & Phân quyền
- **JWT Authentication**: Xác thực người dùng an toàn với JSON Web Tokens
- **Role-Based Access Control (RBAC)**: Quản lý phân quyền dựa trên vai trò
- **Custom JWT Decoder**: Giải mã và xác thực token tùy chỉnh
- **Permission Management**: Quản lý chi tiết các quyền truy cập

###  Quản lý Người dùng
- Đăng ký và đăng nhập người dùng
- Quản lý thông tin cá nhân (profile)
- Phân quyền và vai trò người dùng
- Validation dữ liệu đầu vào (email, ngày sinh, số điện thoại)

### Quản lý Sản phẩm
- CRUD đầy đủ cho sản phẩm
- Phân loại sản phẩm theo danh mục (categories)
- Quản lý số lượng và giá sản phẩm
- Tìm kiếm và lọc sản phẩm
- Mối quan hệ many-to-many giữa Product và Category

###  Quản lý Danh mục
- Tạo và quản lý danh mục sản phẩm
- Liên kết nhiều sản phẩm với một danh mục
- CRUD operations cho categories

###  Giỏ hàng (Cart)
- Thêm/xóa sản phẩm vào giỏ hàng
- Tự động tính tổng giá trị giỏ hàng
- Quản lý số lượng sản phẩm trong giỏ
- One-to-One relationship với User
- One-to-Many relationship với CartItem

###  Quản lý Đơn hàng
- Tạo đơn hàng từ giỏ hàng
- Theo dõi trạng thái đơn hàng (PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
- Quản lý phương thức thanh toán (COD, BANK_TRANSFER, CREDIT_CARD, MOMO, ZALOPAY)
- Chi tiết đơn hàng với OrderItems
- Tính toán tổng giá trị đơn hàng

###  Giao diện quản trị
- Dashboard quản lý tổng quan
- Giao diện quản lý sản phẩm
- Giao diện quản lý danh mục
- Giao diện quản lý vai trò và quyền
- Responsive design với CSS3
- Interactive JavaScript cho dynamic content

## Kiến trúc hệ thống

### Technology Stack

#### Backend
- **Framework**: Spring Boot 3.5.3
- **Language**: Java 21
- **Database**: MySQL 8.0
- **ORM**: Hibernate/JPA
- **Security**: Spring Security + OAuth2 Resource Server
- **Authentication**: JWT (JSON Web Token)
- **Validation**: Spring Validation
- **Mapping**: MapStruct 1.6.3
- **Build Tool**: Maven

#### Frontend
- **HTML5**: Cấu trúc trang web
- **CSS3**: Styling và responsive design
- **JavaScript ES6+**: Logic tương tác
- **Fetch API**: Giao tiếp với REST API

### Cấu trúc thư mục

```
src/
├── main/
│   ├── java/RubikStorecom/example/demo/
│   │   ├── configuration/          # Cấu hình Spring Security, JWT
│   │   │   ├── ApplicationInitConfig.java
│   │   │   ├── CustomJWTDecoder.java
│   │   │   ├── JWTAuthenticationEntryPoint.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/             # REST API Controllers
│   │   │   ├── AuthenticationController.java
│   │   │   ├── CartController.java
│   │   │   ├── CategoryController.java
│   │   │   ├── OrderController.java
│   │   │   ├── PermissionController.java
│   │   │   ├── ProductController.java
│   │   │   ├── RoleController.java
│   │   │   └── UserController.java
│   │   ├── dto/                    # Data Transfer Objects
│   │   │   ├── request/            # Request DTOs
│   │   │   └── response/           # Response DTOs
│   │   ├── entity/                 # JPA Entities
│   │   │   ├── User.java
│   │   │   ├── Product.java
│   │   │   ├── Category.java
│   │   │   ├── Cart.java
│   │   │   ├── CartItem.java
│   │   │   ├── Orders.java
│   │   │   ├── OrderItem.java
│   │   │   ├── Role.java
│   │   │   └── Permission.java
│   │   ├── enums/                  # Enumerations
│   │   │   ├── OrderStatus.java
│   │   │   └── PaymentMethod.java
│   │   ├── exception/              # Custom Exceptions
│   │   │   ├── AppException.java
│   │   │   ├── ErrorCode.java
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── mapper/                 # MapStruct Mappers
│   │   │   ├── ProductMapper.java
│   │   │   ├── CategoryMapper.java
│   │   │   ├── UserMapper.java
│   │   │   └── ...
│   │   ├── repository/             # JPA Repositories
│   │   │   ├── ProductRepository.java
│   │   │   ├── CategoryRepository.java
│   │   │   ├── UserRepository.java
│   │   │   └── ...
│   │   ├── service/                # Business Logic
│   │   │   ├── ProductService.java
│   │   │   ├── CategoryService.java
│   │   │   ├── AuthenticationService.java
│   │   │   ├── CartService.java
│   │   │   └── ...
│   │   └── validator/              # Custom Validators
│   │       ├── DobConstraint.java
│   │       └── DobValidator.java
│   └── resources/
│       ├── application.yaml        # Cấu hình ứng dụng
│       └── static/                 # Frontend files
│           ├── *.html              # HTML pages
│           ├── css/                # Stylesheets
│           └── js/                 # JavaScript files
└── test/
    └── java/                       # Unit & Integration Tests
```



##  Cài đặt và Chạy ứng dụng

### Yêu cầu hệ thống
- Java 21 hoặc cao hơn
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Các bước cài đặt

1. **Clone repository**
```bash
git clone <repository-url>
cd demo
```

2. **Cấu hình Database**

Tạo database MySQL:
```sql
CREATE DATABASE rubik_store;
```

Cập nhật cấu hình trong `src/main/resources/application.yaml`:
```yaml
spring:
  datasource:
    url: "jdbc:mysql://localhost:3307/rubik_store"
    username: root
    password: root
```


##   API Endpoints

### Authentication
- `POST /home/authenticate/token` - Đăng nhập và nhận JWT token
- `POST /home/authenticate/introspect` - Xác thực token

### User Management
- `GET /home/users` - Lấy danh sách người dùng
- `POST /home/users` - Tạo người dùng mới
- `GET /home/users/{id}` - Lấy thông tin người dùng
- `PUT /home/users/{id}` - Cập nhật người dùng
- `DELETE /home/users/{id}` - Xóa người dùng

### Product Management
- `GET /home/products` - Lấy danh sách sản phẩm
- `POST /home/products` - Tạo sản phẩm mới
- `GET /home/products/{id}` - Lấy thông tin sản phẩm
- `PUT /home/products/{id}` - Cập nhật sản phẩm
- `DELETE /home/products/{id}` - Xóa sản phẩm

### Category Management
- `GET /home/categories` - Lấy danh sách danh mục
- `POST /home/categories` - Tạo danh mục mới
- `PUT /home/categories/{id}` - Cập nhật danh mục
- `DELETE /home/categories/{id}` - Xóa danh mục

### Cart Management
- `GET /home/cart` - Xem giỏ hàng
- `POST /home/cart/add` - Thêm sản phẩm vào giỏ
- `PUT /home/cart/update/{itemId}` - Cập nhật số lượng
- `DELETE /home/cart/remove/{itemId}` - Xóa sản phẩm khỏi giỏ

### Order Management
- `GET /home/orders` - Lấy danh sách đơn hàng
- `POST /home/orders` - Tạo đơn hàng mới
- `GET /home/orders/{id}` - Xem chi tiết đơn hàng
- `PUT /home/orders/{id}/status` - Cập nhật trạng thái đơn hàng

### Role & Permission
- `GET /home/roles` - Lấy danh sách vai trò
- `POST /home/roles` - Tạo vai trò mới
- `GET /home/permission` - Lấy danh sách quyền
- `POST /home/permission` - Tạo quyền mới

##  Security Features

### JWT Authentication Flow
1. User đăng nhập với username/password
2. Server xác thực và tạo JWT token
3. Client gửi token trong header cho các request tiếp theo
4. Server verify token và authorize request

### Authorization
- Method-level security với `@PreAuthorize`
- Role-based access control
- Custom JWT decoder với validation
- Secure password encoding với BCrypt









