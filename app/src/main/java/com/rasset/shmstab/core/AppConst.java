package com.rasset.shmstab.core;


import com.rasset.shmstab.BuildConfig;

/**
 * Created by andman on 2015-11-30.
 */
public class AppConst {
    public enum ActionBarType {
        Transparent, Normal, Reward, Eval, Text, Post, Chat, Profile , Hide, None
    }
    public static final String PACKAGE_NAME = "com.playcorp.sqapp";

    public static final String BUILD_TYPE_DEBUG = "debug";
    public static final String BUILD_TYPE_RELEASE = "release";
    public static final String BUILD_TYPE_QA = "qa";

    public static final String LOCAL_BROADCAST_EVENT = PACKAGE_NAME + "_LOCAL_INTENT";
    public static final String LOCAL_BROADCAST_EVENT_VISITOR = PACKAGE_NAME + "_LOCAL_INTENT_VISITOR";
    public static final String LOCAL_BROADCAST_EVENT_CONTACT = PACKAGE_NAME + "_LOCAL_INTENT_CONTACT";
    public static final String LOCAL_BROADCAST_EVENT_CHAT = PACKAGE_NAME + "_LOCAL_INTENT_CHAT";
    public static final String LOCAL_BROADCAST_EVENT_CHATLIST = PACKAGE_NAME + "_LOCAL_INTENT_CHATLIST";
    public static final String LOCAL_BROADCAST_EVENT_EVALUATEME = PACKAGE_NAME + "_LOCAL_INTENT_EVALUATEME";

    public static final int MORE_LIST_LIMIT_UNIT = 30;
    public static final int LEAST_ID_CNT = 4;
    public static final int LEAST_PASSWORD_CNT = 4;

    public static final int CAPTURE_IMAGE_MAX_SIZE = 1024;
    public static final int IMAGE_MAX_SIZE = 1920;
    public static final int SUFFIX_SMALL = 0;
    public static final int SUFFIX_MEDIUM = 1;
    public static final int SUFFIX_LARGE = 2;
    public static final int SUFFIX_XLARGE = 3;


    // View
    public static final int FRAGMENT_DEFAULT = -1;
    public static final int FRAGMENT_BOTNAV_MAIN = 10000;
    public static final int FRAGMENT_USER_LIST = FRAGMENT_BOTNAV_MAIN+1;

    public static final String FRAG_NAME_DIAG_CUSTOMER_INFO = "회원 기본정보";
    public static final String FRAG_NAME_DIAG_INFO_STEP1 = "회원 예진정보";
    public static final String FRAG_NAME_DIAG_INFO_STEP2 = "회원 예진정보";
    public static final String FRAG_NAME_DIAG_COMPLETED = "FRAG_NAME_DIAG_COMPLETED";

    //
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; // "2014-11-24T16:23:00.000Z"
    public static final String COMMON_NOTIFY_REPLACE_FORMAT = "$send_login_id$"; // notify type id syntax

    public static final String SENDER_ID = "18587380099";

    public static final String PRODUCT_FREEPASS = "free";
    public static final String PRODUCT_HEART = "heart";

    // gcm
    public static final String NOTIFICATION_CHANNEL_ID = "SqappChannel";
    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String REGISTRATION_COMPLETE = "gcm_regist_completed";
    public static final String INTENT_EXTRA_ISRUNNING = "running";
    public static final String INTENT_EXTRA_PUSH_TYPE = "pushtype";
    public static final String INTENT_EXTRA_PUSH_TIME = "pushtime";
    public static final String INTENT_EXTRA_IS_PROMO = "mIsPromo";
    public static final String INTENT_EXTRA_ALERT_TYPE = "alertType";
    public static final String INTENT_EXTRA_ACTION_TYPE = "target";
    public static final String INTENT_EXTRA_ISPUSH = "pushyo";
    public static final String INTENT_EXTRA_UID = "uid";
    public static final String INTENT_EXTRA_USERID = "userid";
    public static final String INTENT_EXTRA_USERNAME = "userName";
    public static final String INTENT_EXTRA_USER_GENDER = "userGender";
    public static final String INTENT_EXTRA_PUSH_TEXT = "text";
    public static final String INTENT_EXTRA_NEWMSG = "newMsg";
    public static final String INTENT_EXTRA_NEWMSG_SEQ = "newMsgseq";
    public static final String INTENT_EXTRA_USERPHOTO = "userphoto";
    public static final String INTENT_EXTRA_CHATID = "chatId";
    public static final String INTENT_EXTRA_CHATOBJ = "chatObj";
    public static final String INTENT_EXTRA_MSGTONG = "msgTong";
    public static final String INTENT_EXTRA_TOKENREFRESH = "onTokenrefresh";
    public static final String INTENT_EXTRA_PACKAGEUPDATED = "onPackageUpdate";
    public static final String INTENT_EXTRA_TOKEN_VALUE = "tokenValue";
    public static final String INTENT_EXTRA_TAB_POSITION = "tabPosi";
    public static final String INTENT_EXTRA_GOOGLE_SIGNIN = "isGoogleSignIn";
    public static final String INTENT_EXTRA_FAVORITE_DATES = "arFavDates";
    public static final String INTENT_EXTRA_USER_HOBBY = "userHobby";
    public static final String INTENT_EXTRA_USER_BIRTH = "userBirth";
    public static final String INTENT_EXTRA_SCREENON = "screenOn";
    public static final String INTENT_EXTRA_REVIEW_POINT = "reviewPoint";
    public static final String INTENT_EXTRA_USER_LOCATION = "userLoation";
    public static final String INTENT_EXTRA_REGISTED_NEWUSER = "registNewUser";
    public static final String INTENT_EXTRA_DIRECT_UPDATE_PROFILE = "directUp";

    //
    public static final int MT_VIEW_TYPE_NEW_USER = 1;
    public static final int MT_VIEW_TYPE_USERLIST = 2;
    public static final int MT_VIEW_TYPE_NEW_PHOTO = 3;
    public static final int MT_VIEW_TYPE_MESSAGE = 4;
    public static final int MT_VIEW_TYPE_EVAL = 5;
    public static final int MT_VIEW_TYPE_AD_RANCHAT = 6;

    // Push
    public static final int PUSHTYPE_PROMOTION_ALL_PUSH = 1000;
    public static final int PUSHTYPE_SYSTEM_BANUSER = 1001;

    public static final int PUSHTYPE_MESSAGE = 10;
    public static final int PUSHTYPE_LIKE = 12; // JJIM 미사용
    public static final int PUSHTYPE_VISITOR = 15;
    public static final int PUSHTYPE_VISITOR_UNCHECK = 900;
    public static final int PUSHTYPE_FACETALK = 38;
    public static final int PUSHTYPE_FACETALK_LOST = 41;
    public static final int PUSHTYPE_FACETALK_PROMOTION = 905;
    public static final int PUSHTYPE_EVALME = 43; // Evaluation
    public static final int PUSHTYPE_POPULAR_MEMBER = 70;
    public static final int PUSHTYPE_NEW_MEMBER_REGIST = 80;
    public static final int PUSHTYPE_NEW_MEMBER_LOGIN = 90;
    public static final int PUSHTYPE_NEW_MEMBER_REGIST_NEAR = 901;
    public static final int PUSHTYPE_NEW_MEMBER_REGIST_NEARAGE = 902;
    public static final int PUSHTYPE_NEW_MEMBERS_PHOTO = 903;
    public static final int PUSHTYPE_MEMBER_PHOTOS = 904;

    // Action (푸시화면이동)
    public static final int ACTION_TYPE_MAIN = 0;
    public static final int ACTION_TYPE_CHAT_LIST = 1;
    public static final int ACTION_TYPE_CHAT_ROOM = 2;
    public static final int ACTION_TYPE_PROFILE = 3;
    public static final int ACTION_TYPE_VISITOR = 5;
    public static final int ACTION_TYPE_ATTENTION = 9;

    // 추천이성 타입
    public static final int SUGGEST_TYPE_1 = 1;
    public static final int SUGGEST_TYPE_2 = 2;
    public static final int SUGGEST_TYPE_3 = 3;
    public static final int SUGGEST_TYPE_4 = 4;
    public static final int SUGGEST_TYPE_5 = 5;

    // Net Base URLs
    public static String API_MAIN_URL = BuildConfig.API_BASE_URL;

    // Net URLs Setting
    public static final String API_MAIN_URL_REL = "https://api.saycupid.co.kr/prod/";
    public static final String API_FILE_UPLOAD_URL_REL = "http://upload.api.saycupid.com:1377";
    public static final String API_IMAGE_URL_REL = "http://static.saycupid.com/images/member_pic/";

    public static final String API_MAIN_URL_DEV = "https://api.saycupid.co.kr/dev/";
    public static final String API_FILE_UPLOAD_URL_DEV = "http://upload.api.saycupid.com:1377";
    public static final String API_IMAGE_URL_DEV = "http://static.saycupid.com/images/member_pic/";

    public static final String API_FACETALK_URL = "http://app.api.saycupid.com:1381";

    // Bundle
    public static final int REQ_CODE_GOOGLE_ACCOUNT = 9001;
    public static final int REQ_CODE_FIND_USERINFO = 9090;
    public static final int BUNDLE_TYPE_PHOTOUP_ID_PROFILE = 1; // Profile Photoo Id
    public static final int BUNDLE_TYPE_PHOTOUP_ID_DAILY = 2; // Daily Photo Id
    public static final int BUNDLE_TYPE_PHOTOUP_ID_EVALUATE = 3; // Evaluate Photo Id
    public static final String BUNDLE_UID = "BUNDLE_UID";
    public static final String BUNDLE_USERID = "BUNDLE_USERID";
    public static final String BUNDLE_USERNAME = "BUNDLE_USERNAME";
    public static final String BUNDLE_PHOTOID = "BUNDLE_PHOTOID";
    public static final String BUNDLE_PHOTOPATH= "BUNDLE_PHOTOPATH";
    public static final String BUNDLE_GENDER = "BUNDLE_GENDER";
    public static final String BUNDLE_FOLLOWID = "BUNDLE_FOLLOWID";
    public static final String BUNDLE_CHATID = "BUNDLE_CHATID";
    public static final String BUNDLE_CAMERA_TYPE = "BUNDLE_CAMERA_TYPE";
    public static final String BUNDLE_PHOTO_TYPE = "BUNDLE_PHOTO_TYPE";
    public static final String BUNDLE_MODE_ID_PASS = "BUNDLE_PHOTO_TYPE";
    public static final String BUNDLE_SELECTED_CNT_GIFTCARD = "BUNDLE_SELECTED_CNT_GIFTCARD";

    public static final String BUNDLE_USERLIST_FRAG_MODE = "BUNDLE_USERLIST_FRAG_MODE";
    public static final String BUNDLE_ATTENTION_FRAG_MODE = "BUNDLE_ATTENTION_FRAG_MODE";
    public static final String BUNDLE_EDIT_PROFILE_NAV_MODE = "BUNDLE_EDIT_PROFILE_NAV_MODE";
    public static final String BUNDLE_KONTACT_FRAG_MODE = "BUNDLE_KONTACT_FRAG_MODE";
    public static final String BUNDLE_TERMPOLICY_FRAG_MODE = "BUNDLE_TERMPOLICY_FRAG_MODE";
    public static final String BUNDLE_NORMAL_START = "BUNDLE_NORMAL_START";
    public static final String BUNDLE_REGIST_SIGNUP_START = "BUNDLE_REGIST_SIGNUP_START";
    public static final String BUNDLE_REGIST_FIRST_START = "BUNDLE_REGIST_FIRST_START";
    public static final String BUNDLE_FILE_PATH = "BUNDLE_FILE_PATH";
    public static final String BUNDLE_FILE_PHOTOID = "BUNDLE_FILE_PHOTOID";
    public static final String BUNDLE_RESULT_CODE = "BUNDLE_RESULT_CODE";

    // RESULT
    public static final int RESULT_NEED_FREEPASS = 2016;
    public static final int RESULT_NEED_HEART = 2017;


    // Bundle for Alarm
    public static final String INTENT_EXTRA_ALARM_STARTTIME = "INTENT_EXTRA_ALARM_STARTTIME";
    public static final String INTENT_EXTRA_ALARM_ENDTIME = "INTENT_EXTRA_ALARM_ENDTIME";
    public static final String INTENT_EXTRA_ALARM_SCHEDULTYPE = "INTENT_EXTRA_ALARM_SCHEDULTYPE";

    // Dialog
    public static final String DIALOG_LOGIN_FAIL = "DIALOG_LOGIN_FAIL";
    public static final String DIALOG_CUSTOMER_INFO_PRIVACY = "DIALOG_CUSTOMER_INFO_PRIVACY";


    // pref
    public static final String PREFERENCE_ANALYTICS_REFERRER = "PREFERENCE_ANALYTICS_REFERRER";
    public static final String PREFERENCE_REGIST_WELCOME_SHOWED = "PREFERENCE_REGIST_WELCOME_SHOWED";
    public static final String PREFERENCE_REGIST_UP_PROFILE_COMPLETED = "PREFERENCE_REGIST_UP_PROFILE_COMPLETED";
    public static final String PREFERENCE_REGIST_USER_GOOGLEUSER = "PREFERENCE_REGIST_USER_GOOGLEUSER";
    public static final String PREFERENCE_GCM_REGIST_ID = "PREFERENCE_GCM_REGIST_ID";
    public static final String PREFERENCE_GCM_REG_USER_ID = "PREFERENCE_GCM_REG_USER_ID";
    public static final String PREFERENCE_APPVERSION = "PREFERENCE_APPVERSION";
    public static final String PREFERENCE_ACTIVEUSER_24 = "PREFERENCE_ACTIVEUSER_24";
    public static final String PREFERENCE_FIRST_START = "PREFERENCE_FIRST_START";

    public static final String PREFERENCE_USERINFO_ID = "PREFERENCE_USERINFO_ID";
    public static final String PREFERENCE_USERINFO_NAME = "PREFERENCE_USERINFO_NAME";
    public static final String PREFERENCE_USERINFO_PASSWD = "PREFERENCE_USERINFO_PASSWD";
    public static final String PREFERENCE_USERINFO_GENDER = "PREFERENCE_USERINFO_GENDER";
    public static final String PREFERENCE_USERINFO_AGE = "PREFERENCE_USERINFO_AGE";
    public static final String PREFERENCE_USERINFO_PHOTO = "PREFERENCE_USERINFO_PHOTO";
    public static final String PREFERENCE_USERINFO_ESSAY = "PREFERENCE_USERINFO_ESSAY";
    public static final String PREFERENCE_USERINFO_EMAIL = "PREFERENCE_USERINFO_SEL_EMAIL";
    public static final String PREFERENCE_USERINFO_LOCATION = "PREFERENCE_USERINFO_LOCATION";
    public static final String PREFERENCE_USERINFO_JOB = "PREFERENCE_USERINFO_JOB";
    public static final String PREFERENCE_USERINFO_JOB_CODE = "PREFERENCE_USERINFO_JOB_CODE";
    public static final String PREFERENCE_USERINFO_CHARACTER = "PREFERENCE_USERINFO_CHARACTER";
    public static final String PREFERENCE_USERINFO_SHAPE_TYPE = "PREFERENCE_USERINFO_SHAPE_TYPE";
    public static final String PREFERENCE_USERINFO_BLOOD_TYPE = "PREFERENCE_USERINFO_BLOOD_TYPE";
    public static final String PREFERENCE_USERINFO_IDEAL_TYPE = "PREFERENCE_USERINFO_IDEAL_TYPE";
    public static final String PREFERENCE_USERINFO_KAKAO = "PREFERENCE_USERINFO_KAKAO";
    public static final String PREFERENCE_USERINFO_CONTACT = "PREFERENCE_USERINFO_CONTACT";
    public static final String PREFERENCE_USERINFO_HEART = "PREFERENCE_USERINFO_HEART";
    public static final String PREFERENCE_USERINFO_FREEPASS = "PREFERENCE_USERINFO_FREEPASS";
    public static final String PREFERENCE_USERINFO_FREEPASS_EXP_DATE = "PREFERENCE_USERINFO_FREEPASS_EXP_DATE";
    public static final String PREFERENCE_USERINFO_SPOTLIGHT_EXP_DATE = "PREFERENCE_USERINFO_SPOTLIGHT_EXP_DATE";
    public static final String PREFERENCE_USERINFO_SUBS_STATUS = "PREFERENCE_USERINFO_SUBS_STATUS";
    public static final String PREFERENCE_USERINFO_HOBBY = "PREFERENCE_USERINFO_HOBBY";
    public static final String PREFERENCE_USERINFO_UPDATE_PROFILE = "PREFERENCE_USERINFO_UPDATE_PROFILE";
    public static final String PREFERENCE_USERINFO_UPDATE_USERPHOTO = "PREFERENCE_USERINFO_UPDATE_USERPHOTO";
    public static final String PREFERENCE_USERINFO_ACCOUNT_TYPE = "PREFERENCE_USERINFO_ACCOUNT_TYPE";

    public static final String PREFERENCE_PUSH_NEW_ALLPUSH_COUNT = "PREFERENCE_PUSH_NEW_ALLPUSH_COUNT";
    public static final String PREFERENCE_PUSH_NEW_NOTIFY_COUNT = "PREFERENCE_PUSH_NEW_NOTIFY_COUNT";
    public static final String PREFERENCE_PUSH_NEW_CHAT_MSG_COUNT = "PREFERENCE_PUSH_NEW_CHAT_MSG_COUNT";
    public static final String PREFERENCE_PUSH_NEW_VISITOR_COUNT = "PREFERENCE_PUSH_NEW_VISITOR_COUNT";
    public static final String PREFERENCE_PUSH_NEW_EVALME_COUNT = "PREFERENCE_PUSH_NEW_EVALME_COUNT";

    public static final String PREFERENCE_FREE_SUBS_EXIST = "PREFERENCE_FREE_SUBS_EXIST";
    public static final String PREFERENCE_FREE_CONSUME_EXIST = "PREFERENCE_FREE_CONSUME_EXIST";
    public static final String PREFERENCE_FREE_ITEM_TYPE = "PREFERENCE_FREE_ITEM_TYPE";
    public static final String PREFERENCE_FREE_ORIGIN_JSON = "PREFERENCE_FREE_ORIGIN_JSON";
    public static final String PREFERENCE_FREE_SIGNATURE = "PREFERENCE_FREE_SIGNATURE";
    public static final String PREFERENCE_HEART_CONSUME_EXSIT = "PREFERENCE_HEART_CONSUME_EXSIT";
    public static final String PREFERENCE_HEART_ITEM_TYPE = "PREFERENCE_HEART_ITEM_TYPE";
    public static final String PREFERENCE_HEART_ORIGIN_JSON = "PREFERENCE_HEART_ORIGIN_JSON";
    public static final String PREFERENCE_HEART_SIGNATURE = "PREFERENCE_HEART_SIGNATURE";
    public static final String PREFERENCE_DEVELOPER_VERIFICATION = "PREFERENCE_DEVELOPER_VERIFICATION";
    public static final String PREFERENCE_DEVELOPER_VERIFICATION_HEART = "PREFERENCE_DEVELOPER_VERIFICATION_HEART";
    public static final String PREFERENCE_SHOW_SWIPTE_GUIDE = "PREFERENCE_SHOW_SWIPTE_GUIDE";
    public static final String PREFERENCE_SHOW_TAP_SWIPER_GUIDE = "PREFERENCE_SHOW_TAP_SWIPER_GUIDE";

    public static final String PREFERENCE_SERVER_NOTICE_ID = "PREFERENCE_SERVER_NOTICE_ID";
    public static final String PREFERENCE_TERMPOLICY_COMPLETED = "PREFERENCE_TERMPOLICY_COMPLETED";

    public static final String PREFERENCE_PERMENT_HAD_STARTED_APP = "PREFERENCE_PERMENT_HAD_STARTED_APP";

    // setting Push
    public static final String PREFERENCE_SETTING_NOTIFY_ALL = "PREFERENCE_SETTING_NOTIFY_ALL";
    public static final String PREFERENCE_SETTING_NOTIFY_MESSAGE = "PREFERENCE_SETTING_NOTIFY_MESSAGE";
    public static final String PREFERENCE_SETTING_NOTIFY_VISITOR = "PREFERENCE_SETTING_NOTIFY_VISITOR";
    public static final String PREFERENCE_SETTING_NOTIFY_FACETALK = "PREFERENCE_SETTING_NOTIFY_FACETALK";
    public static final String PREFERENCE_SETTING_NOTIFY_EVALME = "PREFERENCE_SETTING_NOTIFY_EVALME";

    public static final String PREFERENCE_SETTING_API_MODE = "PREFERENCE_SETTING_API_MODE";

    // FireBase Analytics
    public static final String FA_PARAM_SIGNUP_NORMAL = "normal";
    public static final String FA_PARAM_SIGNUP_FB = "facebook";
    public static final String FA_PARAM_SIGNUP_GOOGLE = "google";
    public static final String FA_PARAM_IOEXECTION = "exection_type";
    public static final String FA_PARAM_PUSH_TYPE = "push_type";
    public static final String FA_PARAM_PRODUCT_TYPE = "product_type";
    public static final String FA_PARAM_PRODUCT_ID = "product_id";
    public static final String FA_PARAM_REQUEST_TYPE = "request_type";
    public static final String FA_PARAM_FRAG_TYPE = "frag_type";

    // ANALYTICS
    public static final int ANALYTICS_PR_TYPE_GCM_PUSH = 1; //대량발송 프로모션 푸시이벤트
    public static final int ANALYTICS_PR_TYPE_GCN_REGIST= 2; // 가입 프로모션 푸시이벤트
    public static final int ANALYTICS_PR_TYPE_LOCAL = 3; // 로컬 알림 푸시이벤트
    public static final int ANALYTICS_PR_TYPE_LOCAL_FAIL = 4; // 로컬 알림 API실패 푸시이벤트

    public static final String ANALYTICS_EVENT_CONNECTION_ERROR = "connection_error";
    public static final String ANALYTICS_EVENT_FT_CONNECTION_ERROR = "facetalk_con_error";
    public static final String ANALYTICS_EVENT_ACTIVEUSER_24 = "활성사용자_in24";
    public static final String ANALYTICS_EVENT_JOIN_COMPLETED = "회원가입";
    public static final String ANALYTICS_EVENT_JOIN_G_COMPLETED = "회원가입_구글";
    public static final String ANALYTICS_EVENT_UPDATE_PROFILE = "필수정보입력완료";
    public static final String ANALYTICS_EVENT_UPDATE_PROFILE_DIRECT = "필수정보입력완료_가입직후";
    public static final String ANALYTICS_EVENT_UPLOAD_PHOTO = "사진업로드완료";
    public static final String ANALYTICS_EVENT_EVALUATE_ACTION = "유저평가액션";

    public static final String ANALYTICS_EVENT_UP_SIGNUP_ENTER = "심플가입화면진입";
    public static final String ANALYTICS_EVENT_UP_GOOGLE_SIGNUP = "가입클릭_구글";
    public static final String ANALYTICS_EVENT_UP_NORMAL_SIGNUP = "가입클릭_일반";
    public static final String ANALYTICS_EVENT_UP_GOOGLE_PHOTOUP = "사진업로드_구글가입자";
    public static final String ANALYTICS_EVENT_UP_NORMAL_PHOTOUP = "사진업로드_일반가입자";
    public static final String ANALYTICS_EVENT_UP_NORMAL_SIGNUP_COMPLETE = "일반가입완료클릭";

    public static final String ANALYTICS_EVENT_PAYMENT_LEAD_OUT = "결제유도팝업확인_자유이용권";
    public static final String ANALYTICS_EVENT_EVALME_PUSH = "나를높게평가팝업_노출";
    public static final String ANALYTICS_EVENT_EVALME_PUSH_PRESS = "나를높게평가팝업_확인";

    public static final String ANALYTICS_EVENT_PAYMENT_ALL = "결제페이지진입_전체";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_PROFILE_CHAT = "결제페이지진입_FROM_프로필_채팅";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_PROFILE_FACETALK = "결제페이지진입_FROM_프로필_영상채팅";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_CHAT = "결제페이지진입_FROM_채팅";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_EVALME = "결제페이지진입_FROM_관심이성_나를높게평가";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_VISITOR = "결제페이지진입_FROM_관심이성_방문자";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_EVALOTHER = "결제페이지진입_FROM_관심이성_내가평가";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_SETTING = "결제페이지진입_FROM_설정";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_MAIN = "결제페이지진입_FROM_메인";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_MYPROFILE = "결제페이지진입_FROM_내프로필";
    public static final String ANALYTICS_EVENT_PAYMENT_FROM_SPOTLIGHT = "결제페이지진입_FROM_스포트라이트";

    public static final String ANALYTICS_EVENT_PAYMENT_COMPLETED_FREEPASS = "결제완료_자유이용권";
    public static final String ANALYTICS_EVENT_PAYMENT_COMPLETED_FREEPASS_ENG = "purchase_complete_freepass";
    public static final String ANALYTICS_EVENT_PAYMENT_COMPLETED_APP_LAUNCHED_FREE = "결제완료_앱실행자_자유";
    public static final String ANALYTICS_EVENT_PAYMENT_COMPLETED_PUSH_LAUNCHED_FREE = "결제완료_푸시실행자_자유";

    public static final String ANALYTICS_CATEGORY_PUSH_ARRIVE = "일반푸시_도착";
    public static final String ANALYTICS_CATEGORY_PUSH_REACT = "일반푸시_유저클릭";
    public static final String ANALYTICS_CATEGORY_JOIN_PUSH_ARRIVE = "가입푸시_도착";
    public static final String ANALYTICS_CATEGORY_JOIN_PUSH_REACT = "가입푸시_유저클릭";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_ARRIVE = "광고푸시_도착";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_REACT = "광고푸시_유저클릭";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_ARRIVE_AM = "광고푸시_도착_아침";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_REACT_AM = "광고푸시_유저클릭_아침";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_ARRIVE_PM = "광고푸시_도착_점심";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_REACT_PM = "광고푸시_유저클릭_점심";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_ARRIVE_EV = "광고푸시_도착_저녁";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_REACT_EV = "광고푸시_유저클릭_저녁";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_ARRIVE_UNREG = "광고푸시_도착_미가입자";
    public static final String ANALYTICS_CATEGORY_PR_PUSH_REACT_UNREG = "광고푸시_유저클릭_미가입자";
    public static final String ANALYTICS_CATEGORY_PR_LOCAL_PUSH_ARRIVE = "로컬푸시_도착";
    public static final String ANALYTICS_CATEGORY_PR_LOCAL_PUSH_REACT = "로컬푸시_유저클릭";
    public static final String ANALYTICS_CATEGORY_PR_LOCAL_FAIL_PUSH_ARRIVE = "로컬푸시_Fail_도착";
    public static final String ANALYTICS_CATEGORY_PR_LOCAL_FAIL_PUSH_REACT = "로컬푸시_Fail_유저클릭";

    public static final String ANALYTICS_ACTION_JJIM_ME = "나를찜한";
    public static final String ANALYTICS_ACTION_VISITOR = "내프로필방문";
    public static final String ANALYTICS_ACTION_MESSAGE = "새로운메세지";
    public static final String ANALYTICS_ACTION_MOBILE_REACT = "연락처요청_수락/거절";
    public static final String ANALYTICS_ACTION_MOBILE_SEND = "연락처요청_보냄";
    public static final String ANALYTICS_ACTION_KAKAO_REACT = "카톡요청_수락/거절";
    public static final String ANALYTICS_ACTION_KAKAO_SEND = "카톡요청_보냄";
    public static final String ANALYTICS_ACTION_MY_JJIM_LOGIN = "내가 찜-접속";
    public static final String ANALYTICS_ACTION_MY_JJIM_PROFIILE_PHOTO_UP = "내가 찜_기본사진변경";
    public static final String ANALYTICS_ACTION_MY_JJIM_PHOTOUP = "내가 찜_사진첩사진게시";
    public static final String ANALYTICS_ACTION_MY_JJIM_ESSAY_UP = "내가 찜_자기소개작성";

    public static final String ANALYTICS_CATEGORY_PROFILE = "프로필화면이벤트";
    public static final String ANALYTICS_ACTION_JJIM_REQ = "찜하기";
    public static final String ANALYTICS_ACTION_KAKAO_REQ = "카톡하기";
    public static final String ANALYTICS_ACTION_MOBILE_REQ = "통화하기";
    public static final String ANALYTICS_ACTION_CHAT_REQ = "대화하기";

    public static final String ANALYTICS_CATEGORY_PERMISSION_REQ = "권한요청이벤트";
    public static final String ANALYTICS_ACTION_PERM_PHOTOUP = "저장소권한(사진업로드)";
    public static final String ANALYTICS_ACTION_PERM_ACCOUNT = "연락처권한";
    public static final String ANALYTICS_LABEL_PERM_REQUEST = "권한요청";
    public static final String ANALYTICS_LABEL_PERM_ACCEPT = "권한허용";
    public static final String ANALYTICS_LABEL_PERM_DENIED = "권한거부";

    public static final String ANALYTICS_CATEGORY_FIRST_START_COUNT = "최초앱실행카운트";
    public static final String ANALYTICS_ACTION_FIRST_START_COUNT = "최초앱실행카운트";


    public static final String ANALYTICS_CATEGORY_NET_ERROR_COUNT = "[Net] 네트워크오류 카운트!!";
    public static final String ANALYTICS_ACTION_IOEXECPTION = "[Net] IOEXECPTION_CONNECTION_FAIL !!";
    public static final String ANALYTICS_ACTION_EXECPTION = "[Net] EXECPTION_CONNECTION_FAIL !!";


    public static final String SCREEN_NAMING_ALERTACTIVITY = "AlertActivity";
    public static final String SCREEN_NAMING_FACETALKACTIVITY = "CallActivity";

    public static final String R_CONFIG_KEY_ABTEST_FREEPASS = "abtest_freepass_price";
}
