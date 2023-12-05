package com.univ.billingbaselite.services.impl.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univ.billingbaselite.config.JobClassConstants;
import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import com.univ.billingbaselite.models.entities.AccountRecommendation;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.RecommendationTemplate;
import com.univ.billingbaselite.services.AccountRecommendationService;
import com.univ.billingbaselite.services.AccountService;
import com.univ.billingbaselite.services.JobService;
import com.univ.billingbaselite.services.RecommendationTemplateService;
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
public class PlanProductsRecommendationJob extends QuartzJobBean {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RecommendationTemplateService recommendationTemplateService;
    @Autowired
    private AccountRecommendationService accountRecommendationService;
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

        List<RecommendationTemplate> templates = recommendationTemplateService.getTemplates();
        for (UUID id : accountsList) {
            List<BaseProduct> recommendationProducts = accountService.getRecommendationProducts(id);
            for (BaseProduct product: recommendationProducts) {
                AccountRecommendation firstRecommendation = accountRecommendationService.createAccountRecommendations(product, templates);
                JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
                jobHistoryDTO.setJobClass(JobClassConstants.AccountNotificationJob);
                jobHistoryDTO.setInputParams(Map.of("recommendation", firstRecommendation));
                jobService.createJob(jobHistoryDTO);
            }
        }

        log.warn(String.format("Planned Recommendation Job for Accounts count: %d", accountsList.size()));
    }
}
