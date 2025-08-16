# Hệ Thống Quản Lý Đặt Vé Máy Bay

## Tổng Quan Dự Án

Dự án này là một ứng dụng Java để quản lý việc đặt vé máy bay, bao gồm quản lý vé, hành khách, chuyến bay và hãng hàng không. Hệ thống hỗ trợ các thao tác CRUD (Thêm, Sửa, Xóa) cơ bản, lưu trữ dữ liệu bằng file JSON và giao diện người dùng (GUI) sử dụng thư viện Swing.

### Các Tính Năng Chính
- **Thực thể**:
  - **Vé máy bay (VeMayBay)**: Mã vé, số hiệu chuyến bay, điểm xuất phát, điểm đến, thời gian đi, thời gian đến, hạng vé (phổ thông/thương gia), giá vé.
  - **Hành khách (HanhKhach)**: Số CCCD, họ tên, mã vé.
  - **Chuyến bay (ChuyenBay)**: Số hiệu chuyến bay, số hiệu máy bay, điểm xuất phát, điểm đến, thời gian đi, thời gian đến, số ghế thương gia, số ghế phổ thông, số vé đã bán.
  - **Hãng hàng không (HangHangKhong)**: Mã hãng, tên hãng, số lượng máy bay, danh sách máy bay (theo số hiệu).

- **Thao tác**:
  - Thêm, xóa vé, chuyến bay, hãng hàng không, hành khách (sửa chưa được triển khai).
  - Lọc và thống kê (chưa hoàn thiện, xem phần "Thiếu Sót").

- **Lưu trữ dữ liệu**:
  - Dữ liệu được lưu trong các file JSON (`data/chuyen_bay.json`, `data/ve.json`, v.v.).
  - Kết quả lọc sẽ được lưu vào file riêng (chưa triển khai).

- **Giao diện (GUI)**:
  - Giao diện tabbed (JTabbedPane) để quản lý vé, hành khách, chuyến bay, hãng hàng không.
  - Hỗ trợ nút tải dữ liệu từ JSON, thêm và xóa mục.

- **Lưu ý**:
  - Chưa có xác thực người dùng (class `TaiKhoan` chưa được sử dụng).
  - Sắp xếp theo thứ tự alphabet chưa được áp dụng đầy đủ.
  - Một số tính năng lọc và thống kê chưa hoàn thiện.

## Công Nghệ Sử Dụng
- **Ngôn ngữ**: Java (JDK 8+)
- **Thư viện**:
  - Gson: Xử lý JSON.
  - Swing: Giao diện người dùng.
- **Phụ thuộc**: Gson (`com.google.code.gson:gson:2.10.1`).
- **Công cụ build**: Không yêu cầu; chạy trực tiếp trong IDE như IntelliJ hoặc Eclipse.

## Cấu Trúc Dự Án
```
src/
├── model/                          # Chứa các class mô hình dữ liệu và logic
│   ├── ChuyenBay.java             # Thực thể chuyến bay
│   ├── HangHangKhong.java         # Thực thể hãng hàng không
│   ├── HanhKhach.java             # Thực thể hành khách
│   ├── QuanLyChung.java           # Quản lý CRUD và dữ liệu
│   ├── TaiKhoan.java              # Tài khoản (chưa sử dụng)
│   └── VeMayBay.java              # Thực thể vé máy bay
├── view/                          # Chứa các thành phần giao diện
│   ├── ChuyenBayPanel.java        # Panel quản lý chuyến bay
│   ├── HangHangKhongPanel.java    # Panel quản lý hãng hàng không
│   ├── HanhKhachPanel.java        # Panel quản lý hành khách
│   ├── LocalDateTimeAdapter.java  # Adapter cho LocalDateTime trong Gson
│   └── VeMayBayPanel.java         # Panel quản lý vé
└── Test/                          # Điểm khởi chạy
    └── MainFrame.java             # Giao diện chính

data/                              # Thư mục lưu trữ dữ liệu (tạo thủ công)
├── chuyen_bay.json                # Dữ liệu chuyến bay
├── hang_hang_khong.json           # Dữ liệu hãng hàng không
├── hanh_khach.json                # Dữ liệu hành khách
└── ve.json                        # Dữ liệu vé

README.md                          # File này
```

## Cài Đặt và Chạy
1. **Sao chép dự án** (nếu dùng Git):
   ```
   git clone <repo-url>
   ```

2. **Tạo thư mục `data`**:
   - Tạo thư mục `data/` trong thư mục gốc dự án.
   - Thêm các file JSON mẫu (xem phần "Dữ Liệu Mẫu").

3. **Thêm phụ thuộc**:
   - Tải Gson JAR từ [Maven Repository](https://mvnrepository.com/artifact/com.google.code.gson/gson) và thêm vào classpath.
   - Hoặc dùng Maven:
     ```xml
     <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.10.1</version>
     </dependency>
     ```

4. **Chạy ứng dụng**:
   - Mở trong IDE và chạy `Test/MainFrame.java`.
   - Hoặc biên dịch và chạy từ dòng lệnh:
     ```
     javac -cp .:gson-2.10.1.jar Test/MainFrame.java
     java -cp .:gson-2.10.1.jar Test.MainFrame
     ```

## Hướng Dẫn Sử Dụng
1. **Khởi chạy GUI**:
   - Chạy ứng dụng để mở giao diện với các tab.

2. **Tải dữ liệu**:
   - Trong mỗi tab (Vé, Hành khách, Chuyến bay, Hãng hàng không), nhấn nút "Đọc file JSON" để tải dữ liệu từ file tương ứng.

3. **Thêm/Xóa mục**:
   - Nhấn "Thêm..." để mở form nhập liệu và thêm mục mới.
   - Chọn một hàng trong bảng và nhấn "Xóa..." để xóa.

4. **Lọc và thống kê**:
   - (Chưa triển khai đầy đủ, xem "Thiếu Sót").
   - Dự kiến: Thêm các nút/form nhập ngày, hãng, hoặc chuyến bay để lọc và hiển thị kết quả.

5. **Tìm kiếm nâng cao**:
   - (Chưa triển khai) Nhập một số ký tự để lọc chuyến bay, hãng, hoặc điểm đi/đến.

6. **Xuất dữ liệu lọc**:
   - (Chưa triển khai) Kết quả lọc sẽ được lưu vào file JSON riêng.

## Dữ Liệu Mẫu
- **data/chuyen_bay.json**:
  ```json
  [
    {
      "soHieuChuyenBay": "VN123",
      "soHieuMayBay": "MB001",
      "diemDi": "Hanoi",
      "diemDen": "Ho Chi Minh",
      "thoiGianDi": "2025-08-16 08:00",
      "thoiGianDen": "2025-08-16 10:00",
      "soThuongGia": 20,
      "soPhoThong": 100,
      "soVeDaBan": 50
    }
  ]
  ```

- **data/ve.json**:
  ```json
  [
    {
      "maVe": "VE001",
      "soHieuChuyenBay": "VN123",
      "diemDi": "Hanoi",
      "diemDen": "Ho Chi Minh",
      "thoiGianDi": "2025-08-16 08:00",
      "thoiGianDen": "2025-08-16 10:00",
      "hangVe": "Phổ thông",
      "giaVe": 1500000
    }
  ]
  ```

- **data/hanh_khach.json**:
  ```json
  [
    {
      "cccd": "123456789",
      "hoTen": "Nguyen Van A",
      "maVe": "VE001"
    }
  ]
  ```

- **data/hang_hang_khong.json**:
  ```json
  [
    {
      "maHang": "VN",
      "tenHang": "Vietnam Airlines",
      "soLuongMayBay": 50,
      "danhSachMayBay": ["MB001", "MB002"]
    }
  ]
  ```

## Các Vấn Đề và Thiếu Sót
- **Chưa triển khai đầy đủ**:
  - Chức năng sửa (edit) vé, chuyến bay, hành khách, hãng hàng không.
  - Lọc chuyến bay còn trống theo ngày/giờ.
  - Lọc chuyến bay theo hãng và ngày/giờ.
  - Hiển thị số vé còn lại (thương gia/phổ thông) theo ngày.
  - Lọc hành khách theo chuyến bay.
  - Lọc chuyến bay theo ngày đi/đến.
  - Thống kê doanh thu hãng hàng không theo tháng/năm.
  - Tìm kiếm kiểu Google (tìm kiếm theo ký tự).
  - Xuất kết quả lọc ra file JSON/Excel riêng.
  - Sắp xếp dữ liệu theo thứ tự alphabet trong bảng GUI.

- **Hạn chế**:
  - Chưa có xác thực người dùng (TaiKhoan chưa dùng).
  - Thiếu liên kết giữa hãng hàng không và chuyến bay (chỉ có số hiệu máy bay).
  - Xử lý lỗi đầu vào chưa toàn diện (cần thêm kiểm tra định dạng ngày, số vé hợp lệ, v.v.).

## Kế Hoạch Nâng Cấp
- Thêm chức năng sửa thông qua form GUI.
- Triển khai các phương thức lọc trong `QuanLyChung` (lọc theo ngày, hãng, hành khách).
- Thêm hỗ trợ xuất Excel (dùng Apache POI).
- Thêm giao diện lọc với JDateChooser và JTextField tìm kiếm động.
- Áp dụng sắp xếp alphabet trong bảng GUI (sử dụng TableRowSorter).
- Tích hợp xác thực người dùng với `TaiKhoan`.

## Người Đóng Góp
- [Tên của bạn] - Nhà phát triển chính
