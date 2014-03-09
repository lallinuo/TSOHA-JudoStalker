/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lalli
 */

@Controller
public class EtusivuController {
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String haeEtusivu(){
        return "home";
    }
    
}
