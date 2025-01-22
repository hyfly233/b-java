package com.hyfly.dolphinscheduler.sdk.core;

public class DolphinClientConstant {

  public static class Page {
    public static final Integer DEFAULT_PAGE = 1;
    public static final Integer DEFAULT_SIZE = 10;
  }

  public static class LogLimit {
    public static final Integer DEFAULT_SKIP = 0;
    public static final Integer DEFAULT_LIMIT = 50;
  }

  public static class Resource {
    public static final String TYPE_FILE = "FILE";
    public static final String TYPE_UDF = "UDF";
    public static final String DEFAULT_PID_FILE = "-1";
    public static final String DEFAULT_CURRENT_DIR = "/";
  }
}
