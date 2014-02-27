/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker;

import com.pro.judostalker.model.Judoka;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lalli
 */
public class JudokaController {

    @RequestMapping(value = "/Judoka", method = RequestMethod.GET)
    public @ResponseBody
    Judoka Judoka() {
        return new Judoka();
    }

    @RequestMapping(value = "/Judoka", method = RequestMethod.GET)
    public @ResponseBody
    Judoka DeleteJudoka() {
        return new Judoka();
    }

    @RequestMapping(value = "/Judoka", method = RequestMethod.GET)
    public @ResponseBody
    Judoka AddJudoka() {
        return new Judoka();
    }

    @RequestMapping(value = "/Judoka", method = RequestMethod.GET)
    public @ResponseBody
    Judoka UpdateJudoka() {
        return new Judoka();
    }
}
