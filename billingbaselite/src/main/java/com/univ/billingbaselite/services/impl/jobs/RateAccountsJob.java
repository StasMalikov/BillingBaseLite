package com.univ.billingbaselite.services.impl.jobs;

import com.univ.billingbaselite.config.JobClassConstants;
import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import com.univ.billingbaselite.models.entities.Account;
import com.univ.billingbaselite.services.AccountService;
import com.univ.billingbaselite.services.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@DisallowConcurrentExecution
public class RateAccountsJob extends QuartzJobBean {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JobService jobService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Account> positiveBalanceAccounts = accountService.getAllNonRatedAccountsWithPositiveBalance();
        processPositiveAccounts(positiveBalanceAccounts);
        List<Account> negativeBalanceAccounts = accountService.getAllNonRatedAccountsWithNegativeBalance();
        processNegativeAccounts(negativeBalanceAccounts);
    }

    private void processPositiveAccounts (List<Account> accounts) {
        accounts.forEach(a -> a.setAccountStatus(Account.AccountStatus.ON_POSITIVE_RECOMMENDATION));
        List<UUID> ids = accounts.stream().map(Account::getId).toList();
        accountService.updateAccounts(accounts);
        scheduleRecommendationJob(ids);
        log.warn(String.format("Rated as Positive Accounts count: %d", ids.size()));
    }

    private void processNegativeAccounts (List<Account> accounts) {
        List<Account> mediumNegativeAccounts = accounts.stream().filter(a -> accountService.checkProductsDowngrade(a.getId().toString())).toList();
        accounts.removeAll(mediumNegativeAccounts);
        processMediumNegativeAccounts(mediumNegativeAccounts);

        accounts.forEach(a -> a.setAccountStatus(Account.AccountStatus.ON_SUSPENSION));
        List<UUID> ids = accounts.stream().map(Account::getId).toList();
        accountService.updateAccounts(accounts);
        scheduleSuspensionJob(ids);
        log.warn(String.format("Rated as Negative Accounts count: %d", ids.size()));
    }

    private void processMediumNegativeAccounts (List<Account> accounts) {
        accounts.forEach(a -> a.setAccountStatus(Account.AccountStatus.ON_SUSPENSION_AND_RECOMMENDATION));
        List<UUID> ids = accounts.stream().map(Account::getId).toList();
        accountService.updateAccounts(accounts);
        scheduleSuspensionJob(ids);
        scheduleRecommendationJob(ids);
        log.warn(String.format("Rated as Medium Negative Accounts count: %d", ids.size()));
    }

    private void scheduleSuspensionJob(List<UUID> ids) {
        JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
        jobHistoryDTO.setJobClass(JobClassConstants.PlanProductsSuspensionJob);
        jobHistoryDTO.setInputParams(Map.of("accountNumbers", ids));
        jobService.createJob(jobHistoryDTO);
    }

    private void scheduleRecommendationJob(List<UUID> ids) {
        JobHistoryDTO jobHistoryDTO = new JobHistoryDTO();
        jobHistoryDTO.setJobClass(JobClassConstants.PlanProductsRecommendationJob);
        jobHistoryDTO.setInputParams(Map.of("accountNumbers", ids));
        jobService.createJob(jobHistoryDTO);
    }
}
