package com.emersondev.serviceimpl;

import com.emersondev.POJO.User;
import com.emersondev.constants.CafeConstants;
import com.emersondev.dao.UserDao;
import com.emersondev.service.UserService;
import com.emersondev.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;


  @Override
  public ResponseEntity<String> singUp(Map<String, String> requestMap) {
    log.info("Inside signup {} ", requestMap);
    try {
      if (validateSignUpMap(requestMap)) {
        User user = userDao.findByEmailId(requestMap.get("email"));
        if (Objects.isNull(user)) {
          userDao.save(getUserFromMap(requestMap));
          return  CafeUtils.getResponseEntity("Succesfully Registered", HttpStatus.OK);
        } else {
          return CafeUtils.getResponseEntity("User already exists", HttpStatus.BAD_REQUEST);
        }
      } else {
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception ex) {
        ex.printStackTrace();
      }
    return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private boolean validateSignUpMap(Map<String, String> requestMap) {
      return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
              && requestMap.containsKey("email") && requestMap.containsKey("password");
  }

  private User getUserFromMap(Map<String, String> requestMap) {
    User user = new User();
    user.setName(requestMap.get("name"));
    user.setContactNumber(requestMap.get("contactNumber"));
    user.setEmail(requestMap.get("email"));
    user.setPassword(requestMap.get("password"));
    user.setStatus("false");
    user.setRole("user");
    return user;
  }



}




