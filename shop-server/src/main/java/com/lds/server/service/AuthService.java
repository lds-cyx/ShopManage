package com.lds.server.service;

import com.lds.pojo.dto.AuthParamsDto;
import com.lds.pojo.dto.UserDto;

public interface AuthService {

    UserDto execute(AuthParamsDto authParamsDto);
}
