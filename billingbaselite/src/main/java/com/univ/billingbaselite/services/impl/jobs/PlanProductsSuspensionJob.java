package com.univ.billingbaselite.services.impl.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univ.billingbaselite.config.JobClassConstants;
import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import com.univ.billingbaselite.models.entities.AccountTreatment;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.TreatmentTemplate;
import com.univ.billingbaselite.services.AccountService;
import com.univ.billingbaselite.services.AccountTreatmentService;
import com.univ.billingbaselite.services.JobService;
import com.univ.billingbaselite.services.TreatmentTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class PlanProductsSuspensionJob extends QuartzJobBean {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TreatmentTemplateService treatmentTemplateService;
    @Autowired
    private AccountTreatmentService accountTreatmentService;
    @Autowired
    private JobService jobService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ObjectMapper mapper = new ObjectMapper();
        String accountsListJSON = (String) context.getJobDetail().getJobDataMap().get("accountNumbers");
        List<UUID> accountsList = new ArrayList<>();
        try {
            accountsList = List.of(mapper.readValue(accountsListJSON, UUID[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<TreatmentTemplate> templates = treatmentTemplateService.getTemplates();
        for (UUID id : accountsList) {
            List<BaseProduct> suspensionProducts = accountService.getSuspensionProducts(id);
            for (BaseProduct product: suspensionProducts) {
                AccountTreatment firstTreatment = accountTreatmentService.createAccountTreatments(product, templates);
                JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
                jobHistoryDTO.setJobClass(JobClassConstants.ProductSuspensionJob);
                jobHistoryDTO.setInputParams(Map.of("treatment", firstTreatment));
                jobService.createJob(jobHistoryDTO);
            }
        }

        log.warn(String.format("Planned Treatment Job for Accounts count: %d", accountsList.size()));
    }
}
