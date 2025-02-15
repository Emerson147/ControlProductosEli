package com.emersondev.restimpl;

import com.emersondev.constants.CafeConstants;
import com.emersondev.rest.UserRest;
import com.emersondev.service.UserService;
import com.emersondev.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

  @Autowired
  UserService userService;

  @Override
  public ResponseEntity<String> signup(Map<String, String> requestMap) {
    try {
      return userService.singUp(requestMap);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
