package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by EriclLee on 15/12/29.
 */
@Controller
@RequestMapping(value = "/data")
public class DataController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        return null;
    }
    @RequestMapping(value = "/datadownload", method = RequestMethod.GET)
    public ModelAndView datadownload(){
        return null;
    }
}
