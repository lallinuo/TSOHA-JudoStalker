/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.service;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

/**
 *
 * @author lalli
 */
@Component
public class AuthService {

    public boolean isLogged(HttpSession session) {
        return session.getAttribute("kirjautunut") != null;

    }

    public boolean isLoggedWithId(HttpSession session, int id) {
        System.out.println(session.getAttributeNames());
        System.out.println(id);
        String sessionid = (String)session.getAttribute("kirjautunut");
        System.out.println(id);
        System.out.println(sessionid);
        return sessionid.equals(id + "");
    }
}
