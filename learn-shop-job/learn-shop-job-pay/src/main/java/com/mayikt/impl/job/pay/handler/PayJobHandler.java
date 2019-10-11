package com.mayikt.impl.job.pay.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

/**
 * 使用任务调度实现自动化补偿
 */
@JobHander(value="payJobHandler")
@Component
@Slf4j
public class PayJobHandler extends IJobHandler {

	@Override
	public ReturnT<String> execute(String... strings) throws Exception {
		log.info(">>>使用任务调度实现自动化对账");
		return SUCCESS;
	}
}
