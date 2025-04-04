package com.mylibrary.enums;

public enum BookStatus {
    AVAILABLE(1, "This book is available!"),
    UNAVAILABLE(2, "This book is unavailable!");

    private final int code;
    private final String msg;

    BookStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
