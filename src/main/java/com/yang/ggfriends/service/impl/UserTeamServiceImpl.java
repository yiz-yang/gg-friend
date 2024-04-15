package com.yang.ggfriends.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.ggfriends.mapper.UserTeamMapper;
import com.yang.ggfriends.service.UserTeamService;
import com.yang.ggfriends.model.domain.UserTeam;
import org.springframework.stereotype.Service;

/**
 * 用户队伍服务实现类
 */
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
        implements UserTeamService {

}




