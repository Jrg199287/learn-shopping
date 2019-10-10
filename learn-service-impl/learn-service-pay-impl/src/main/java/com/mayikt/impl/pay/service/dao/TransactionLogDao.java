package com.mayikt.impl.pay.service.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mayikt.impl.pay.service.entity.TransactionLogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 支付交易日志表  Mapper 接口
 * </p>
 *
 * @author Xxm123
 * @since 2019-06-21
 */
@Mapper
@Repository
public interface TransactionLogDao extends BaseMapper<TransactionLogEntity> {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO `meite_member`.`payment_transaction_log`(`ID`, `SYNCH_LOG`, `ASYNC_LOG`, `CHANNEL_ID`, `TRANSACTION_ID`, `REVISION`, `CREATED_BY`, `CREATED_TIME`, `UPDATED_BY`, `UPDATED_TIME`, `untitled`) VALUES (null,#{synchLog},#{asyncLog},#{channelId},#{transactionId},0,null,null,null,null,null);")
    int isnertEntity(TransactionLogEntity logEntity);
}
