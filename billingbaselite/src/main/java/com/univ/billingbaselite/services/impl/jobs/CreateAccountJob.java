package com.univ.billingbaselite.services.impl.jobs;

import com.univ.billingbaselite.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;


@Slf4j
public class CreateAccountJob extends QuartzJobBean implements InterruptableJob {

    @Autowired
    private AccountService accountService;

    private Thread thread;

    @Override
    protected void executeInternal(JobExecutionContext context) {

//        try {
//            thread = Thread.currentThread();
//
//            ObjectMapper mapper = new ObjectMapper();
//            String accountInfoDTOJSON = (String) context.getJobDetail().
//                    getJobDataMap().get(GlobalConstants.JOB_IP_ACCOUNT_INFO_DTO);
//            AccountInfoDTO accountInfoDTO = mapper.readValue(accountInfoDTOJSON, AccountInfoDTO.class);
//            accountService.createAccount(accountInfoDTO);
//        } catch (JsonProcessingException e) {
//            throw new CMBusinessException(String.format(
//                    ErrorMessages.QRTZ_FAILED_DESERIALIZE_INPUT_PARAM, GlobalConstants.JOB_IP_ACCOUNT_INFO_DTO));
//        }
    }

    @Override
    public void interrupt() {
        log.info("interrupting thread = {}", thread.getName());
        thread.interrupt();
    }
}
