package com.qf.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: WangXi
 * @Date: 2019/6/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean implements Serializable {
    private String statusCode;

    private Object data;
}
