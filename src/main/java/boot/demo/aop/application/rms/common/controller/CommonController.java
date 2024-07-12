package boot.demo.aop.application.rms.common.controller;

import boot.demo.aop.application.rms.common.model.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("")
public class CommonController {

    @GetMapping({"/main", "/"})
    public String userMainPage(Model model, Pagination pagination) {
        model.addAttribute("pagination", pagination);

        return "application/user/main";
    }

}
