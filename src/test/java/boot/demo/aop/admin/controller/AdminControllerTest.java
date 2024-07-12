package boot.demo.aop.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import boot.demo.aop.application.rms.admin.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import boot.demo.aop.application.rms.admin.service.AdminService;
import boot.demo.aop.application.rms.common.model.entity.TbRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(AdminController.class)
class AdminControllerTest {
	
	@Autowired
    MockMvc mockMvc;
    
    @MockBean
    private AdminService mockAdminService;

	@Test
	void testAdminInsertPage() throws Exception {
		log.info("ADMIN INSERT TEST");
		List<TbRole> roleList = mockAdminService.getRoleList();
		mockMvc.perform(
				get("/admin/insert")			
				.contentType(MediaType.APPLICATION_JSON)
				.content("re")
				)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));			
	}

//	@Test
	void testAdminListPage() {
		log.info("ADMIN LIST TEST");
	}

//	@Test
	void testAdminController() {
		log.info("ADMIN CONTROLLER TEST");
	}

}
