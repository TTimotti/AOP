package boot.demo.aop.common.exception;

public class AOPException extends RuntimeException{
    private final ErrorCode errorCode;

    public AOPException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
