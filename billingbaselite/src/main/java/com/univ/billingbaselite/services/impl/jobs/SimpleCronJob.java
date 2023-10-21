package com.univ.billingbaselite.services.impl.jobs;

import java.util.stream.IntStream;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class SimpleCronJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) {
        IntStream.range(0, 10).forEach(i -> {
            log.info("Counting - {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
