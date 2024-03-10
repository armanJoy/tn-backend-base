package com.technonext.transport.common.constant;

public class ApplicationConstant {
    /**
     * change status
     */
    public static final String STATUS_CHANGED =  "Status Changed Successfully !";
    /**
     * trace id constant
     */
    public static final String TRACE_ID = "traceId";
    /**
     * errorCode json file name
     */
    public static final String ERROR_CODE_JSON_FILE = "error/error_code.json";


    public static final String AUTHORIZATION_TYPE_BEARER = "Bearer";

    /**
     * Default page size while paginated query
     */
    public static final int DEFAULT_SIZE = 10;
    public static final String DEFAULT_SORT = "id";
    /**
     * Minimum character is 8, only alphabet & number. It also allows symbol. and within character & number & symbol, at least use 2 item mandatory.
     */
    public static final String VALID_PASSWORD_REGEX = "^((((?=.*?[A-Z])|(?=.*?[a-z]))(?=.*?[0-9]))" +
            "|((?=.*?[0-9])(?=.*?[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]))|" +
            "((?=.*?[!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~])((?=.*?[A-Z])|(?=.*?[a-z])))).{8,256}$";
    /**
     * Email validation regex
     */
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public static final String DATE_TYPE_VALIDATION_REGEX = "[-/.]";
    public static final String REPLACE_DATE_TYPE_VALIDATION_REGEX = "[-/]*";
    public static final String ALL_INTEGER_VALIDATION_REGEX = "[0-9]+";
    public static final String SHELF_LIFE_VALIDATION_REGEX = "[.\\/ \\-,]";
    /**
     * Default page number.
     */
    public static final String DEFAULT_PAGE_NUMBER = "1";
    /**
     * Default page size.
     */
    public static final String DEFAULT_PAGE_SIZE = "20";
    /**
     * deletion message
     */
    public static final String DELETED_SUCCESSFULLY = "Deleted Successfully";

    public static final int FIRST_INDEX = 0;
    public static final String EMPTY_STRING = "";
    public static final String ENTITY_NAME = "name";
    public static final String ENTITY_EMAIL = "email";
    public static final Long ANONYMOUS_USER = -1L;
    public static final String SUBMODULE_ITEM_ID = "subModuleItemId";
    public static final String ID = "id";
    public static final String MESSAGE_SEPARATOR = "###";


    public static final String DATE_TYPE = "dateType";

    public static final String DASH = "-";
    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String REVISED = "R";
    public static final char SLASH_CHAR = '/';
    public static final String FOUR_DIGIT_FORMAT = "%04d";



    public static class NUMBERS{
        public static final Integer ZERO = 0;
        public static final Integer ONE = 1;
        public static final Integer TWO = 2;
        public static final Integer THREE = 3;
        public static final Integer FOUR = 4;
        public static final Integer FIVE = 5;

    }

    public static final String ROLE_NAME_DUPLICATE_MSG = "Role name already exist.";

    public static final String ROLE = "Role";
    public static final Boolean ACTIVE_TRUE = true;

    public static final String ACCESS_RIGHTS_FILE_PATH = "access_rights.json";
    public static final String CREATED_DATE = "createdAt";
    public static final String DOT_REGEX = "\\.";
}
