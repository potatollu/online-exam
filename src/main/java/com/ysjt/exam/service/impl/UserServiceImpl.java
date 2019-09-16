package com.ysjt.exam.service.impl;

import com.ysjt.exam.common.entity.ExamUser;
import com.ysjt.exam.common.enums.ExceptionMsg;
import com.ysjt.exam.constant.RedisKeys;
import com.ysjt.exam.exception.base.BusinessException;
import com.ysjt.exam.mapper.UserMapper;
import com.ysjt.exam.service.UserService;
import com.ysjt.exam.utils.MD5Util;
import com.ysjt.exam.utils.RedisUtils;
import com.ysjt.exam.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@Service
public class UserServiceImpl implements UserService {

    private static final int ONE_DAY_SECONDS = 24 * 3600;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<ExamUser> listAll() {
        return userMapper.selectAll();
    }

    @Override
    public String login(String username, String password) {

        ExamUser exitUser = Optional.ofNullable(userMapper.selectOneByExample(Example.builder(ExamUser.class)
                .andWhere(Sqls.custom().andEqualTo("username", username)).build()))
                .orElseThrow(() -> new BusinessException(ExceptionMsg.USER_NOT_FOUND));

        String token = TokenUtils.getToken();
        if (MD5Util.verify(password, exitUser.getPassword())) {
            //登录成功后，把原来的token清除
            Object userToken = redisUtils.get(RedisKeys.EXAM_TOKEN_USER_ID + exitUser.getId());
            if (userToken != null) {
                redisUtils.del(RedisKeys.EXAM_TOKEN_INFO + userToken, RedisKeys.EXAM_TOKEN_USER_ID + exitUser.getId());
            }
            Map<String, Object> userMap = new HashMap<>(3);
            userMap.put("userId", exitUser.getId());
            userMap.put("username", exitUser.getUsername());
            userMap.put("realName", exitUser.getRealName());
            redisUtils.hmset(RedisKeys.EXAM_TOKEN_INFO + token, userMap, ONE_DAY_SECONDS);
            redisUtils.set(RedisKeys.EXAM_TOKEN_USER_ID + exitUser.getId(), token, ONE_DAY_SECONDS);
            return token;
        }
        throw new BusinessException(ExceptionMsg.USERNAME_OR_PASSWORD_ERROR);
    }

    @Override
    public void register(ExamUser examUser) {
        if (userMapper.selectOne(ExamUser.builder().username(examUser.getUsername()).build()) != null) {
            throw new BusinessException(ExceptionMsg.USER_EXIT);
        }
        examUser.setPassword(MD5Util.generate(examUser.getPassword()));
        userMapper.insertSelective(examUser);
    }

}
