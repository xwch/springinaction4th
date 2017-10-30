package com.xwch.web.controller;

import com.xwch.bean.Spittle;
import com.xwch.data.SpittleRepository;
import com.xwch.web.dto.SpittleFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 1、构造器，入参SpittleRepository
 * 2、List<Spittle> spittles(long max,int count)  GET请求
 * 3、根据spittleId和GET查询spittle，并且放入Model，返回视图spittle
 * 4、POST请求，保存spittleForm，并且重定向到redirect:/spittles
 * Created by xwch on 2017/10/19.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }


/*    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model){
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE,20));
        return "spittles";
    }*/

    /**
     * 1、当处理器方法返回集合或对象时，会放到model中，model的key会根据其类型判断出来，本例是spittleList
     * 2、视图名，会根据请求路径得出。该方法处理针对“/spittles”的GET请求，因此视图名将会是spittles（去掉开头的斜线）
     * @param max
     * @param count
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(
      @RequestParam(value = "max",defaultValue = MAX_LONG_AS_STRING) long max,
      @RequestParam(value = "count",defaultValue = "20") int count){
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(value = "/{spittleId}",method = RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") long spittleId,Model model){
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(SpittleFormDTO form){
        Spittle spittle = new Spittle();
        spittle.setTime(new Date());
        spittle.setMessage(form.getMessage());
        spittle.setLatitude(form.getLatitude());
        spittle.setLongitude(form.getLongitude());
        spittleRepository.save(spittle);

        return "redirect:/spittles";
    }


}
