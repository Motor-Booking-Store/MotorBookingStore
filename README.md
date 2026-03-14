# Cập nhật chức năng thuê xe theo khoảng thời gian và quản lý thông tin người dùng

## Thông tin nhánh phát triển

Nhánh hiện tại được tạo từ nhánh:

- `feature/UpdateInfUser`

Nhánh này tiếp tục mở rộng chức năng cập nhật thông tin người dùng và bổ sung các cải tiến liên quan đến quy trình thuê xe theo khoảng thời gian.

---

## 1. Cập nhật chức năng thuê xe theo khoảng thời gian

### Mô tả thay đổi

Khi người dùng nhấn **Rent Now**, hệ thống sẽ:

- Hiển thị form để chọn **ngày bắt đầu** và **ngày kết thúc** trước khi tạo yêu cầu thuê.
- Kiểm tra **xung đột lịch thuê** trước khi tạo đơn thuê mới.

### Logic kiểm tra xung đột

Trước khi tạo rental, hệ thống sẽ kiểm tra xem xe đã có đơn thuê trong khoảng thời gian được chọn hay chưa, bao gồm:

- Đơn thuê **Pending** (đang chờ duyệt)
- Đơn thuê **Approved** (đã được duyệt)

Nếu phát hiện trùng thời gian, hệ thống sẽ hiển thị thông báo:

> Xe này đã được đặt hoặc đang có yêu cầu thuê chờ duyệt trong khoảng thời gian đã chọn.

### Mục đích

Cập nhật này giúp:

- Tránh nhiều người dùng gửi yêu cầu thuê cho cùng một xe trong cùng khoảng thời gian
- Giảm xung đột khi admin xử lý duyệt đơn
- Cải thiện trải nghiệm người dùng bằng cách kiểm tra khả dụng ngay từ bước tạo yêu cầu thuê

---

## 2. Cải tiến trạng thái xe theo thời gian thực

Hệ thống đã được cải tiến để trạng thái xe được xác định dựa trên **thời gian thuê thực tế**, thay vì chỉ dựa trên việc xe có đơn thuê hay không.

### Ví dụ

- Một xe có thể đã được thuê vào **tuần sau**
- Nhưng vẫn **có sẵn ở thời điểm hiện tại** nếu chưa đến ngày bắt đầu thuê

Điều này giúp trạng thái hiển thị của xe chính xác hơn trong:

- Trang danh sách xe (`/user/MotorbikeList`)
- Trang chi tiết xe (`/user/MotorbikeDetail?id=...`)

---

## 3. Tự động cập nhật trạng thái đơn thuê khi hết hạn

Hệ thống bổ sung cơ chế tự động cập nhật trạng thái đơn thuê:

- Nếu đơn thuê đang ở trạng thái **Approved**
- Và đã **qua ngày kết thúc thuê**

Thì hệ thống sẽ tự động chuyển trạng thái thành:

- **Completed**

### Đồng bộ trạng thái xe

Sau khi đơn thuê được chuyển sang **Completed**, hệ thống sẽ tự động đồng bộ lại trạng thái xe để xe có thể hiển thị là **Available** nếu không còn bị chiếm dụng.

### Phạm vi áp dụng

Logic cập nhật tự động hiện được kích hoạt khi người dùng truy cập các trang:

- `/user/MotorbikeList`
- `/user/MotorbikeDetail?id=...`

---

## 4. Bổ sung chức năng upload ảnh đại diện (Avatar)

Hệ thống đã bổ sung chức năng cho phép người dùng:

- Cập nhật **ảnh đại diện (avatar)** thông qua form cập nhật thông tin cá nhân
- Upload ảnh trực tiếp từ giao diện người dùng

### Cách lưu trữ hiện tại

Ảnh avatar được lưu trong:

- **thư mục build của project**

### Lưu ý quan trọng

Do ảnh được lưu trong thư mục build nên:

> Nếu thực hiện **Clean & Build**, toàn bộ ảnh avatar đã upload có thể bị mất.

---

