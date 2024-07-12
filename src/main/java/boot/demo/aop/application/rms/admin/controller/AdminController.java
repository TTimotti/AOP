package boot.demo.aop.application.rms.admin.controller;

import java.util.List;

import boot.demo.aop.application.rms.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import boot.demo.aop.application.rms.common.model.Pagination;
import boot.demo.aop.application.rms.common.model.dto.AdminListResponseDTO;
import boot.demo.aop.application.rms.common.model.entity.TbRole;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminService adminService;

    @GetMapping("/insert")
    public String adminInsertPage(Model model) {
        List<TbRole> roleList = adminService.getRoleList();
        model.addAttribute("roleList", roleList);

        return "application/admin/insert";
    }

    @GetMapping("/list")
    public String adminListPage(Model model, Pagination pagination) {
    	pagination.setTotalCount(1020);
        List<AdminListResponseDTO> adminList = adminService.getAllAdmin();
        model.addAttribute("adminList", adminList);
        model.addAttribute("pagination", pagination);
        log.info("pagination: {}", pagination);

        return "application/admin/list";
    }

}
