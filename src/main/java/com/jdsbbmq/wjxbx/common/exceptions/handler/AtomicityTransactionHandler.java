package com.jdsbbmq.wjxbx.common.exceptions.handler;

import com.jdsbbmq.wjxbx.common.exceptions.AtomicityException;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
public class AtomicityTransactionHandler implements ExceptionHandler {
    public void handleException(Throwable ex) throws Throwable {
        if (ex instanceof AtomicityException) {
            // 如果抛出的异常是UserNotFoundException，则回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw ex;
        } else {
            // 对其他异常进行默认的处理
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void handleException(CommandAcceptanceException e) {

    }
}
