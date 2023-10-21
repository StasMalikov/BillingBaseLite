package com.univ.billingbaselite.config.quartz;

import com.univ.billingbaselite.models.entities.JobHistory;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
@Slf4j
public class JobScheduleCreator {

    public CronTrigger createCronTrigger(String triggerName, Date startTime, String cronExpression, int misFireInstruction) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(misFireInstruction);
        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return factoryBean.getObject();
    }

    public SimpleTrigger createTrigger(JobHistory jobInfo) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setName(jobInfo.getJobName());
        factoryBean.setStartTime(jobInfo.getStartDate());
        factoryBean.setRepeatInterval(jobInfo.getRepeatInterval());
        factoryBean.setRepeatCount(jobInfo.getRepeatCount());
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

}
