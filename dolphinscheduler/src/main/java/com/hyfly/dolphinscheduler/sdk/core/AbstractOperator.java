package com.hyfly.dolphinscheduler.sdk.core;

import com.hyfly.dolphinscheduler.sdk.remote.DolphinsRestTemplate;
import com.hyfly.dolphinscheduler.sdk.remote.Header;

public abstract class AbstractOperator {

  protected final String dolphinAddress;

  private final String token;

  protected final DolphinsRestTemplate dolphinsRestTemplate;

  public AbstractOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    this.dolphinAddress = dolphinAddress;
    this.token = token;
    this.dolphinsRestTemplate = dolphinsRestTemplate;
  }

  /**
   * get header for dolphin scheduler
   *
   * @return
   */
  protected Header getHeader() {
    Header header = Header.newInstance();
    header.addParam("token", this.token);
    return header;
  }
}
