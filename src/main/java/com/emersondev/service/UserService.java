package com.emersondev.service;

import com.emersondev.POJO.User;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

  ResponseEntity<String> singUp(Map<String, String> requestMap);
}
