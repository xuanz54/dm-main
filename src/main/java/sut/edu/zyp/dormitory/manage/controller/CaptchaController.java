package sut.edu.zyp.dormitory.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sut.edu.zyp.dormitory.manage.service.CaptchaService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/***
 * 该类是一个Spring MVC的控制器类,实现验证码的生成和输出。主要包含获取验证码图片、生成验证码、输出验证码图片等功能。 其作用是在登录时生成并输出验证码图片,作为后续登录时验证码校验的源。
 */

@Controller
public class CaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    /**
     * 注入验证码服务
     */
    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取验证码图片
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, path = "/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成4位验证码
        String verifyCode = captchaService.genCaptha(4);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute("cpacha", verifyCode + "_" + System.currentTimeMillis());
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");

        //生成验证码图片,包含3条干扰线
        BufferedImage bufferedImage = captchaService.genCapthaImageByVerifyCode(verifyCode, 3);

        //写给浏览器
        try {
            ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            //后台返回验证码图片失败
            logger.error("", e);
        }
    }
}