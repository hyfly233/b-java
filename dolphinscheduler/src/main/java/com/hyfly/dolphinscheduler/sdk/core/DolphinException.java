package com.hyfly.dolphinscheduler.sdk.core;

public class DolphinException extends RuntimeException {

  public DolphinException() {
    super();
  }

  public DolphinException(String message) {
    super(message);
  }

  public DolphinException(String message, Throwable e) {
    super(message, e);
  }
}
