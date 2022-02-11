package com.example.practice.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

/**
 * @author gaopengtao
 * @version 执行器demo
 */
@Slf4j
@Component
public class JobHandlerDemo {

    @XxlJob("jobDemo")
    public ReturnT<String> jobDemo(String s) {
        log.info("XXL-JOB,HELLO WORLD");
        return SUCCESS;
    }

    @XxlJob("jobDemo1")
    public ReturnT<String> jobDemo1(String s) {
        log.info("jobDemo1,HELLO WORLD");
        return SUCCESS;
    }
}
