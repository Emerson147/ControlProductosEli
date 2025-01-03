package com.emersondev.dao;

import com.emersondev.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer>  {
}
