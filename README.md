make vietnamsee,profesion readme.md
add this is branch created from feature/UpdateInfUser
add avatar image upload using form

# Cập nhật chức năng thuê xe theo khoảng thời gian

## Mô tả thay đổi

Khi người dùng nhấn **Rent Now**, hệ thống sẽ:

- Hiển thị form để chọn **ngày bắt đầu** và **ngày kết thúc** trước khi tạo yêu cầu thuê.
- Kiểm tra **xung đột lịch thuê** trước khi tạo đơn thuê mới.

## Logic kiểm tra xung đột

Trước khi tạo rental, hệ thống sẽ kiểm tra xem xe đã có đơn thuê trong khoảng thời gian được chọn hay chưa, bao gồm:

- Đơn thuê **Pending** (đang chờ duyệt)
- Đơn thuê **Approved** (đã được duyệt)

Nếu có đơn bị trùng thời gian, hệ thống sẽ hiển thị thông báo:

> Xe này đã được đặt hoặc đang có yêu cầu thuê chờ duyệt trong khoảng thời gian đã chọn.

## Cải tiến trạng thái xe theo thời gian thực

Hệ thống đã được cải tiến để trạng thái xe được xác định dựa trên **thời gian thuê thực tế**, không chỉ dựa vào việc xe có đơn thuê hay không.

Ví dụ:

- Một xe có thể đã được thuê vào **tuần sau**
- Nhưng vẫn **có sẵn hôm nay** nếu chưa đến ngày bắt đầu thuê

Điều này giúp trạng thái hiển thị của xe chính xác hơn trong trang danh sách và trang chi tiết.

## Tự động cập nhật trạng thái đơn thuê

Hệ thống bổ sung cơ chế tự động cập nhật:

- Nếu đơn thuê đang ở trạng thái **Approved**
- Và đã **qua ngày kết thúc thuê**

Thì hệ thống sẽ tự động chuyển trạng thái thành:

- **Completed**

## Đồng bộ lại trạng thái xe

Sau khi đơn thuê hết hạn và được chuyển sang **Completed**, hệ thống sẽ tự động đồng bộ lại để xe có thể hiển thị là **Available** nếu không còn bị chiếm dụng.

## Phạm vi áp dụng

Logic này hiện được áp dụng khi người dùng truy cập các trang:

- `/user/MotorbikeList`
- `/user/MotorbikeDetail?id=...`

## Mục đích

Cập nhật này giúp:

- Tránh nhiều người dùng đặt cùng một xe trong cùng khoảng thời gian
- Giảm xung đột khi admin xử lý duyệt đơn
- Hiển thị đúng trạng thái khả dụng của xe theo thời gian thực
- Tự động cập nhật đơn thuê hết hạn mà không phụ thuộc vào admin đăng nhập
