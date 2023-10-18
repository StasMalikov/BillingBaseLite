package com.univ.billingbaselite.services.impl.jobs;


import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class SimpleJob extends QuartzJobBean implements InterruptableJob {
    private Thread thread;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        thread = Thread.currentThread();

        IntStream.range(0, 5).forEach(i -> {
            log.info("Counting - {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
    }


    @Override
    public void interrupt() {
        log.info("interrupting thread = {}", thread.getName());
        thread.interrupt();
    }
}
