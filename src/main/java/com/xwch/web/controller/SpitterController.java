package com.xwch.web.controller;

import com.xwch.bean.Spitter;
import com.xwch.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by xwch on 2017/10/22.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository){
        this.spitterRepository =spitterRepository;
    }

    /**
     * 跳转到注册页面
     * 由于跳转的registerForm页面使用了模型绑定，所以模型中需要有一个key=spitter的对象
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String showRegistrationForm(Model model){
        model.addAttribute("spitter",new Spitter());
        return "registerForm";
    }

    /**
     * 处理提交的注册信息
     * @param spitter  @Valid表示启用校验
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processRegistration(@Valid Spitter spitter, Errors errors){

        /**
         * 如果校验出错，则返回表单
         */
        if (errors.hasErrors()){
            System.out.println("注册表单校验出错-->"+errors.toString());
            return "registerForm";
        }

        spitterRepository.save(spitter);
        System.out.println("save success-->"+spitter.toString());
        return "redirect:/spitter/"+spitter.getUsername();
    }

    /**
     * 展示个人信息
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Model model){
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute("spitter",spitter);
        return "profile";
    }

}
