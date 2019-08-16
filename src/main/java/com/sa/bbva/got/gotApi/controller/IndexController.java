package com.sa.bbva.got.gotApi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public RedirectView index() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("swagger-ui.html");
        return redirectView;
    }
}
