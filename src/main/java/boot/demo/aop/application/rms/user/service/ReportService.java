package boot.demo.aop.application.rms.user.service;

import boot.demo.aop.application.rms.common.annotation.Loggable;
import boot.demo.aop.application.rms.common.model.Response;
import boot.demo.aop.application.rms.common.model.dto.ReportInsertRequestDTO;
import boot.demo.aop.application.rms.common.repository.TbReportMstRepository;
import boot.demo.aop.application.rms.common.repository.TbRoleRepository;
import boot.demo.aop.application.rms.common.repository.TbUserMstRepository;
import boot.demo.aop.application.rms.common.repository.TbUserRoleRepository;
import boot.demo.aop.exception.AOPException;
import boot.demo.aop.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {

    private final TbUserMstRepository tbUserMstRepository;
    private final TbUserRoleRepository tbUserRoleRepository;
    private final TbRoleRepository tbRoleRepository;
    private final TbReportMstRepository tbReportMstRepository;


    @Loggable
    @Transactional
    public Response insertReport(ReportInsertRequestDTO requestDTO) {
        int result = tbReportMstRepository.insertTbReportMst(requestDTO.toEntity());

        if (result == 0) {
            throw new AOPException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return Response.SUCCESS;
    }
}
