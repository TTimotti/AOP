package boot.demo.aop.application.rms.user.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReportController {
	
	private static final Logger log = LoggerFactory.getLogger(ReportController.class);

//    private final UserService adminService;
	@GetMapping("/report/insert")
	public String reportInsertPage() {

		return "application/user/report/insert";

	}


}
