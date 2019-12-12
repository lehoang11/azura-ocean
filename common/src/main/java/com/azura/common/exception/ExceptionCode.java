package com.azura.common.exception;

public interface ExceptionCode {
    public static final Integer BAD_REQUEST = 300;
    public static final Integer CONSTRAINT_VIOLATION = 301;
    public static final Integer METHOD_ARGUMENT_TYPE_MISMATCH = 304;
    public static final Integer ERROR_RUNTIME = 305;
    public static final Integer NOT_FOUND = 404;
    public static final Integer ERROR_SERVER = 500;


    // From 400
    public interface Authentication {
        public static final Integer AUTHENTICATION_USER_PASSWORD_INVALID = 400;
        public static final Integer AUTHENTICATION_USER_INACTIVE = 401;
        public static final Integer AUTHENTICATION_CATPCHAR_IS_REQUIRED = 402;
        public static final Integer AUTHENTICATION_CATPCHAR_IS_INVALID = 403;
        public static final Integer REQUEST_INVALID = 404;
        public static final Integer REQUEST_SESSION_IS_INVALID = 405;
        public static final Integer AUTHENTICATION_TOKEN_INVALID = 406;
    }

    // From 500
    public interface User {
        public static final Integer USER_NOT_FOUND = 404;
    }

    // From 600
    public interface UploadFile {
        public static final Integer UPLOAD_FILE_ERROR = 600;
    }

    public  interface Edu {
        public static final Integer EDU_NOT_FOUND = 404;

    }

}

