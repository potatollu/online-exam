package com.ysjt.exam.service;

import com.ysjt.exam.common.entity.ExamUser;
import com.ysjt.exam.exception.base.BusinessException;

import java.util.List;

public interface UserService {

    /**
     * get all user
     * @return
     */
    List<ExamUser> listAll();

    /**
     * login
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * user register
     * @param examUser
     */
    void register(ExamUser examUser);
}
