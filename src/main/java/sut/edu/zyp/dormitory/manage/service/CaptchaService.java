package sut.edu.zyp.dormitory.manage.service;

import java.awt.image.BufferedImage;

public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @param number 验证码位数
     * @return 生成的随机验证码
     */
    String genCaptha(int number);

    /**
     * 根据验证码生成验证码图片
     *
     * @param verifyCode 验证码
     * @param interferenceLine 干扰线数量
     * @return 验证码图片
     */
    BufferedImage genCapthaImageByVerifyCode(String verifyCode,int interferenceLine);
}
