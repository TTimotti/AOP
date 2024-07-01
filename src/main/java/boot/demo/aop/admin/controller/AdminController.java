package boot.demo.aop.admin.controller;

import boot.demo.aop.common.model.dto.AdminListResponseDTO;
import boot.demo.aop.common.model.entity.TbRole;
import boot.demo.aop.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/insert")
    public String adminInsertPage(Model model) {
        List<TbRole> roleList = adminService.getRoleList();
        model.addAttribute("roleList", roleList);

        return "admin/insert";
    }

    @GetMapping("/list")
    public String adminListPage(Model model) {
        List<AdminListResponseDTO> adminList = adminService.getAllAdmin();
        model.addAttribute("adminList", adminList);

        return "admin/list";
    }

}
