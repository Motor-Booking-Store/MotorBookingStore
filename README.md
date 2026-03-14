# ⚠️ KHÔNG MERGE NHÁNH NÀY

## Mô tả thay đổi

Khi người dùng nhấn nút **Rent Now**, hệ thống đã được cập nhật để:

* Hiển thị form chọn **ngày bắt đầu** và **ngày kết thúc** trước khi tạo yêu cầu thuê xe.
* Thực hiện kiểm tra xung đột lịch thuê trước khi tạo rental.

## Logic kiểm tra đã bổ sung

Trước khi tạo yêu cầu thuê, hệ thống sẽ kiểm tra xem xe có đang rơi vào một trong các trường hợp sau hay không:

* Đã có **đơn thuê đang chờ duyệt (Pending)** trong khoảng thời gian được chọn.
* Đã có **đơn thuê đã được duyệt (Approved)** trùng với khoảng thời gian được chọn.

Nếu phát hiện xung đột, hệ thống sẽ hiển thị thông báo:

> **Xe này đã được đặt hoặc đang có yêu cầu thuê chờ duyệt trong khoảng thời gian đã chọn.**

## Mục đích

Cập nhật này nhằm đảm bảo:

* Tránh nhiều người dùng cùng gửi yêu cầu thuê cho cùng một xe trong cùng khoảng thời gian.
* Hạn chế xung đột dữ liệu khi admin xử lý duyệt đơn thuê.
* Cải thiện trải nghiệm người dùng bằng cách kiểm tra tính khả dụng của xe ngay từ bước tạo yêu cầu thuê.

## Lưu ý

* **Không merge nhánh này ở thời điểm hiện tại.**
* Nhánh này đang phục vụ cho việc kiểm tra logic rental trước khi hoàn thiện luồng duyệt đơn.

---

**Trạng thái:** Chỉ dùng để test / review nội bộ.

