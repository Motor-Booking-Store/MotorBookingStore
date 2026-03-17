# Cập nhật chức năng lọc xe máy

## Mô tả
Đã bổ sung chức năng **lọc danh sách xe máy** tại trang `MotorbikeList` giúp người dùng tìm xe thuận tiện hơn.

## Chức năng mới
Người dùng có thể lọc theo:

- **Hãng xe**
- **Giá thuê theo ngày**
- **Trạng thái xe**

## Chi tiết cập nhật
- Thêm bộ lọc tại `component/sidebar.jsp`
- Hỗ trợ lọc động theo **brand** từ dữ liệu trong database
- Hỗ trợ lọc theo khoảng giá:
  - Dưới 150.000 VND
  - 150.000 - 200.000 VND
  - Trên 200.000 VND
- Hỗ trợ lọc theo trạng thái:
  - `Available` (Chưa thuê)
  - `Rented` (Đã thuê)
  - `Maintenance` (Bảo trì)

## Backend thay đổi
- Cập nhật `MotorbikeListController` để nhận tham số filter:
  - `brand`
  - `priceRange`
  - `status`
- Cập nhật `MotorbikeDAO`:
  - Thêm hàm lọc xe máy theo điều kiện
  - Thêm hàm lấy danh sách hãng xe (`getAllBrands()`)

## Ghi chú
- Trạng thái `Rented` / `Available` được xử lý động dựa trên dữ liệu thuê xe hiện tại.
- Bộ lọc trạng thái được áp dụng sau khi cập nhật trạng thái thực tế của xe.
