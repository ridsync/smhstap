package com.rasset.shmstab.network.protocol;

/**
 * @author okc
 * @version 1.0
 * @see
 * @since 2015-12-20.
 *
 *  protocol -
 */
public class ResultCode {
    /**
     * HTTP, CLIENT PROCESS
     */
    public static final int HTTP_SUCCESS_OK = 200;
    public static final int CLIENT_ERROR = 300;
    public static final int JSON_PRASE_ERROR = 301;
    public static final int SESSION_ERROR = 400;
    public static final int SERVER_ERROR = 500;
    public static final int UNKNOWN_ERROR = -100;
    public static final int CLIENT_CANCELLED = -303;
    public static final int CONNECTION_FAIL = -300; // 서버접속실패
    public static final int CONNECTION_NOT_AVAILABLE = -909; // 인터넷 연결 불가능
    public static final int IO_EXECPTION_ERROR = -309; // ?? 서버접속실패 ??

    /**
      * Project
      */
    // Common Success 1
    public static final int API_NO_ERROR = HTTP_SUCCESS_OK;
    public static final int API_SUCCESS = 1;

    // Common Fail Code Range 21 ~
    public static final int API_AUTH_NOT_EXIST_USER = 21;
    public static final int API_AUTH_NOT_EXIST_OTHERUSER = 22;
    public static final int API_AUTH_SELF_ACTION = 23;
    public static final int API_PAY_NEED_VIPMEMBER = 31;  // (mobile not use)
    public static final int API_PAY_NEED_FREEPASS = 32;
    public static final int API_PAY_NEED_HEART = 33;
    public static final int API_CLIENT_OLD_VERSION = 41;
    public static final int API_CLIENT_PARAM_ERROR = 42;
    public static final int API_SERVER_DB_DOWN_ERROR = 51;
    public static final int API_SERVER_DB_QUERY_ERROR = 52;
    public static final int API_UNKNOWN_ERRROR = 53;
    public static final int API_WARNING_SERVERDOWN = 99;

    // FAil Code Range 1~20
    // login & Regist
    public static final int API_LOGIN_UNREGIST_USERID = 2;
    public static final int API_LOGIN_UNREGIST_LOGINID = 3;
    public static final int API_LOGIN_INCORRECT_PASSWD = 4;
    public static final int API_LOGIN_USER_BAN = 3003;
    public static final int API_ID_ALREADY_EXIST = 2;

    // Profile
    public static final int API_USER_LIKE_ALREADY = 2;
    public static final int API_USER_LIKE_HAS_CANCELED = 3;
    public static final int API_USER_LIKE_TARGETUSER_BAN_OR_REJECT = 4;

    // Kontact
    public static final int API_CONTACT_TYPE_NONE_REQUEST= 9;
    public static final int API_CONTACT_TYPE_REQUEST = 0;
    public static final int API_CONTACT_TYPE_READ = 1;
    public static final int API_CONTACT_TYPE_ACCEPT = 2;
    public static final int API_CONTACT_TYPE_REJECT = 3;
    public static final int API_CONTACT_TYPE_REJECT_AUTO = 4;
    // result PostContact
    public static final int API_CONTACT_REQ_TYPE_ALREADY = 2;
    public static final int API_CONTACT_REQ_TYPE_EMPTY_KONTACT_INFO = 4;

    // Payment
    public static final int REESULT_TYPE_PURCHASE_SUCCESS = 1;
    public static final int REESULT_TYPE_PURCHASE_NOPERMIT = 2;
    public static final int REESULT_TYPE_PURCHASE_ALREADY_SUCC = 3;
    public static final int REESULT_TYPE_CONSUME_SUCCESS = 1;
    public static final int REESULT_TYPE_CONSUME_NOPERMIT = REESULT_TYPE_PURCHASE_NOPERMIT;
    public static final int REESULT_TYPE_CONSUME_ALREADY_CONSUME = 4;
    public static final int REESULT_TYPE_CONSUME_PURCHASE_NONE_CONSUME = 5;


    // Chat
    public static final int API_POST_CHAT_SUCCESS = 1;
    public static final int API_POST_CHAT_LIMIT_ROOM_COUNT = 2;
    public static final int API_POST_CHAT_NOTENOUGH_HEART = 3;

    public static final int API_GET_CHATLIST_NEED_HEART_MALE = 1; // 남자:채팅5회이상하트컨슘필요
    public static final int API_GET_CHATLIST_HEART_CONSUMED = 2;


    // Upload Photo
    public static final int API_POST_UPLOAD_PHOTO_SUCCESS = 1;
    public static final int API_POST_UPLOAD_PHOTO_3DAY_LIMIT = 2;
}
