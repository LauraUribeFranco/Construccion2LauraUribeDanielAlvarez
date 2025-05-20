package app.adapters.rest.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private String status;
    private int code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    // Default constructor
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor for success responses
    public ApiResponse(int code, String message, T data) {
        this();
        this.status = code < 400 ? "success" : "error";
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Static factory methods for different responses
    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(201, message, data);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(401, message, null);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(403, message, null);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(404, message, null);
    }

    public static <T> ApiResponse<T> conflict(String message) {
        return new ApiResponse<>(409, message, null);
    }

    public static <T> ApiResponse<T> serverError(String message) {
        return new ApiResponse<>(500, message, null);
    }

    public static <T> ApiResponse<T> badGateway(String message) {
        return new ApiResponse<>(502, message, null);
    }

    public static <T> ApiResponse<T> serviceUnavailable(String message) {
        return new ApiResponse<>(503, message, null);
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
