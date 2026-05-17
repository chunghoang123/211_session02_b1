# Bài làm - Kiểm tra Web Service của tôi đã "hot" chưa?

## Phần 1 - Phân tích logic

Các ứng dụng client không thể phân tích cú pháp dữ liệu nhận được từ Web Service `getHotProducts` vì phương thức này đang trả về dữ liệu dưới dạng `String`:

```java
return products.toString();
```

Mặc dù danh sách `products` đã được thêm các sản phẩm, nhưng `products.toString()` không chuyển danh sách đối tượng Java thành JSON hợp lệ. Đây chỉ là cách Java biểu diễn danh sách dưới dạng chuỗi.

Nếu lớp `Product` không override phương thức `toString()`, kết quả có thể có dạng như sau:

```text
[org.example.b1.ProductController$Product@1a2b3c4d, org.example.b1.ProductController$Product@5e6f7g8h]
```

Chuỗi trên không phải JSON hợp lệ, không có các cặp key-value rõ ràng như `id`, `name`, `price`. Vì vậy, các ứng dụng frontend hoặc mobile không thể dùng JSON parser để đọc dữ liệu.

Nguyên nhân gốc rễ là phương thức `getHotProducts()` trả về sai kiểu dữ liệu. Thay vì trả về `String`, phương thức cần trả về trực tiếp `List<Product>`. Khi dùng `@RestController`, Spring Boot sẽ tự động chuyển đổi đối tượng Java thành JSON thông qua cơ chế message converter.

## Phần 2 - Thực thi

Controller đã được cấu hình với `@RequestMapping` ở cấp lớp để quản lý URL tốt hơn:

```java
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
}
```

Endpoint lấy danh sách sản phẩm hot được cấu hình bằng HTTP GET:

```java
@GetMapping("/hot")
public List<Product> getHotProducts() {
    List<Product> products = new ArrayList<>();
    products.add(new Product("HP001", "\u00c1o thun 'Code is Life'", 199000));
    products.add(new Product("HP002", "M\u00f3c kh\u00f3a 'Bug Free'", 99000));
    return products;
}
```

Endpoint đầy đủ:

```http
GET /api/v1/products/hot
```

Khi client gọi endpoint này, Spring Boot sẽ tự động trả về JSON hợp lệ:

```json
[
  {
    "id": "HP001",
    "name": "Áo thun 'Code is Life'",
    "price": 199000.0
  },
  {
    "id": "HP002",
    "name": "Móc khóa 'Bug Free'",
    "price": 99000.0
  }
]
```

Đã kiểm tra bằng lệnh:

```powershell
.\gradlew.bat test
```

Kết quả: build và test thành công.
