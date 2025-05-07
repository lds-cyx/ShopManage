package com.lds.pojo.dto;

import com.lds.pojo.po.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author lds
 * @since 2025-02-19
 */

@Data
public class UserDto extends User implements Serializable  {
    //用户权限
    List<String> permissions = new ArrayList<>();

}
