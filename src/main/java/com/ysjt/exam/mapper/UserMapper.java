package com.ysjt.exam.mapper;

import com.ysjt.exam.common.entity.ExamUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@Repository
public interface UserMapper extends Mapper<ExamUser> {
}
