package com.raymond.quickstart.domain;

import lombok.Data;

/**
 * Common web result entity
 *
 * @author raymondmuzzi
 * @since 2023-10-12 22:15:14
 */
@Data
public class R {
    private Object data;
    private String msg;
    private Long timestamp = System.currentTimeMillis();
    private Boolean flag = false;

    public R() {
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Object data) {
        this.data = data;
    }
}
