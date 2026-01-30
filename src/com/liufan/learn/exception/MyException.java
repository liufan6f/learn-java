package com.liufan.learn.exception;

/**
 * 自定义异常，查看{@linkplain com.liufan.learn.exception.BaseException}
 * <p>
 * 抛出异常时，尽量复用JDK已定义的异常类型。
 */
public class MyException extends BaseException {
    public MyException() {
        super();
    }
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyException(String message) {
        super(message);
    }
    public MyException(Throwable cause) {
        super(cause);
    }
}
