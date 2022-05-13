package com.axway.currency.constant;

import java.util.HashMap;

public class ErrorConstants {
    /**
     *
     */
    public static final int NORMAL = 0;
    /**
     * resolve the args error
     */
    public static final int ARGS_ERROR = 1;
    /**
     * call remote api error
     */
    public static final int CALL_API_ERROR = 2;

    public static final HashMap<Integer, String> ERROR_MAP = new HashMap<Integer, String>() {{
        put(ARGS_ERROR, "resolve the args error!");
        put(CALL_API_ERROR, "call remote api error!");

    }};
}
