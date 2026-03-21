<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý người dùng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/listUser.css">
    </head>

    <body>
        <jsp:include page="./component/adminNavbar.jsp"/>
        <div>
            <h2>Quản lý người dùng</h2>

            <!-- THÔNG BÁO -->
            <c:if test="${not empty message}">
                <p style="color: green;">${message}</p>
            </c:if>
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>

            <!-- THÊM USER -->
            <a href="${pageContext.request.contextPath}/admin/adduser">
                <button class="btn-add">Thêm người dùng</button>
            </a>

            <br><br>

            <!-- BẢNG -->
            <table border="1" cellpadding="10" cellspacing="0" width="100%">

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên đăng nhập</th>
                        <th>Email</th>
                        <th>CCCD</th>
                        <th>Số điện thoại</th>
                        <th>Vai trò</th>
                        <th>Hành động</th>
                    </tr>
                </thead>

                <tbody>

                    <!-- Nếu list rỗng -->
                    <c:if test="${empty accountList}">
                        <tr>
                            <td colspan="7" style="text-align:center;">
                                Không có người dùng nào
                            </td>
                        </tr>
                    </c:if>

                    <!-- Loop user -->
                    <c:forEach var="u" items="${accountList}">
                        <tr>

                            <td>${u.userID}</td>

                            <td>${u.userName}</td>

                            <td>${u.email}</td>

                            <td>${u.citizenId}</td>

                            <td>${u.phoneNumber}</td>

                            <!-- ROLE -->
                            <td>
                                <c:choose>
                                    <c:when test="${u.roleId == 1}">
                                        Quản trị viên
                                    </c:when>
                                    <c:otherwise>
                                        Khách hàng
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- ACTION -->
                            <td>

                                <!-- SỬA -->
                                <a href="${pageContext.request.contextPath}/admin/EditUser?id=${u.userID}">
                                    <button class="btn-edit">Sửa</button>
                                </a>

                                <!-- XÓA -->
                                <c:if test="${u.roleId != 1}">
                                    <a href="${pageContext.request.contextPath}/admin/DeleteUser?id=${u.userID}"
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?');">
                                        <button class="btn-delete">Xóa</button>
                                    </a>
                                </c:if>

                                <!-- nếu là admin thì disable delete -->
                                <c:if test="${u.roleId == 1}">
                                    <button disabled>Xóa</button>
                                </c:if>

                            </td>

                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </div>
        <jsp:include page="../component/footer.jsp"/>
    </body>
</html>