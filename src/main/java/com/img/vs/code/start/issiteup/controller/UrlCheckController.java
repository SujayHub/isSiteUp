package com.img.vs.code.start.issiteup.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UrlCheckController {

  private static final String IS_SITE_UP = "Yeh! site is up!";
  private static final String SITE_IS_DOWN = "Sorry! Site is down!";
  private static final String INCORRECT_URL = "Url is incorrect!";

  @GetMapping("/statusCheck")
  public ResponseEntity<String> getStatus() {
    return ResponseEntity.ok().body("Hey welcome to our new project developed using vs code");
  }

  @GetMapping("/urlStatusCheck")
  public String getUrlStatusCheck(@RequestParam String url) {

    String returnMessage;

    try {
      URL urlObject = new URL(url);
      HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
      if (HttpStatus.valueOf(conn.getResponseCode()).is4xxClientError()
          || HttpStatus.valueOf(conn.getResponseCode()).is5xxServerError()) {
        returnMessage = SITE_IS_DOWN;
      } else {
        returnMessage = IS_SITE_UP;
      }
    } catch (MalformedURLException e) {
      returnMessage = INCORRECT_URL;
    } catch (IOException e) {
      returnMessage = SITE_IS_DOWN;
    }

    return returnMessage;
  }
}
