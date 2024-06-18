package boot.demo.aop.exception;

import boot.demo.aop.aop.annotation.Loggable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class AOPErrorController implements ErrorController {

    @Loggable
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        model.addAttribute("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        return "error";
    }

}
