package com.boot.future.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author ck
 * @since 2017-12-11
 */
@TableName("login_user_data")
public class LoginUserData extends Model<LoginUserData> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("login_user_id")
    private Integer loginUserId;
    /**
     * 登录次数
     */
    @TableField("login_frequency")
    private Integer loginFrequency;
    /**
     * 登录ip
     */
    @TableField("login_ip")
    private String loginIp;
    private Date updatedate;
    private Date createdate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

    public Integer getLoginFrequency() {
        return loginFrequency;
    }

    public void setLoginFrequency(Integer loginFrequency) {
        this.loginFrequency = loginFrequency;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LoginUserData{" +
                ", id=" + id +
                ", loginUserId=" + loginUserId +
                ", loginFrequency=" + loginFrequency +
                ", loginIp=" + loginIp +
                ", updatedate=" + updatedate +
                ", createdate=" + createdate +
                "}";
    }
}
