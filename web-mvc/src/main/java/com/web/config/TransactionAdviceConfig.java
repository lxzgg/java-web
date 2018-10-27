package com.web.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
@Configuration
public class TransactionAdviceConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        DefaultTransactionAttribute tx_required = new DefaultTransactionAttribute();
        tx_required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute tx_required_readOnly = new DefaultTransactionAttribute();
        tx_required_readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        tx_required_readOnly.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", tx_required);
        source.addTransactionalMethod("set*", tx_required);
        source.addTransactionalMethod("put*", tx_required);
        source.addTransactionalMethod("del*", tx_required);
        source.addTransactionalMethod("save*", tx_required);
        source.addTransactionalMethod("exec*", tx_required);
        source.addTransactionalMethod("insert*", tx_required);
        source.addTransactionalMethod("update*", tx_required);
        source.addTransactionalMethod("is*", tx_required_readOnly);
        source.addTransactionalMethod("get*", tx_required_readOnly);
        source.addTransactionalMethod("find*", tx_required_readOnly);
        source.addTransactionalMethod("list*", tx_required_readOnly);
        source.addTransactionalMethod("query*", tx_required_readOnly);
        source.addTransactionalMethod("count*", tx_required_readOnly);
        source.addTransactionalMethod("select*", tx_required_readOnly);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public DefaultPointcutAdvisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com..service..*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
