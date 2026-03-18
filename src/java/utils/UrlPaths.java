/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author nguye
 */
public class UrlPaths {
    public static final String USER_HOME = "/user/Home";
    public static final String ADMIN_HOME = "/admin/Home";
    public static final String LOGIN = "/Login";
    
    public static final String Id_MotorbikeDetail = "/MotorbikeDetail?id=";
    
    public static final String PENDING_RENTALS = "/admin/pending-rentals";
    public static final String ADD_USER = "/admin/adduser";
    
    public static final String USER_DETAIL = "/user/UserDetail";
    
    public static String url(HttpServletRequest request, String path) {
        return request.getContextPath() + path;
    }
}
