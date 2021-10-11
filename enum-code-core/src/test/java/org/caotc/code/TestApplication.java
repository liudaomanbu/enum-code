/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited.
 * All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 *  ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */

package org.caotc.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.caotc.code"})
public class TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}
