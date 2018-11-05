package com.crw.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResult {

    private Message message;
    private Object data;

    @Data
    @Builder
    private static class Message {
        private int code;
        private String message;
    }

    public static ApiResult success() {
        return success(null);
    }

    public static ApiResult error() {
        return error(null);
    }

    public static ApiResult success(Object data) {
        return ApiResult.builder()
                .message(Message.builder().code(0).message("success").build())
                .data(data)
                .build();
    }

    static ApiResult error(Object data) {
        return builder()
                .message(Message.builder().code(-1).message("fail").build())
                .data(data)
                .build();
    }
}
