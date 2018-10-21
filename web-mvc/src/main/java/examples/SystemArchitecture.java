package examples;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {

    /**
     * 声明切入点
     */
    @Pointcut("execution(* examples.*.abc(..))")
    private void pointcut() {
    }

    @Before("pointcut()")
    private void anyPublicOperation() {
        System.out.println("切面:方法前调用");
    }

    @After("pointcut()")
    public void doReleaseLock() {
        System.out.println("切面:方法后调用");
    }

    @AfterReturning(pointcut = "pointcut()", returning = "retVal")
    public void doAccessCheck(Object retVal) {
        System.out.println("正常返回" + retVal);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doRecoveryActions(RuntimeException e) {
        System.out.println("捕获抛出异常" + e.getMessage());
    }

    @Around("pointcut()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
//        return "拦截方法并返回方法返回的值";
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }

}
