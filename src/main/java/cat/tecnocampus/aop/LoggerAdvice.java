package cat.tecnocampus.aop;

import cat.tecnocampus.domain.Classroom;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggerAdvice.class);

    @Pointcut("execution(* cat.tecnocampus.controllers.ClassroomController.*(..))")
    public void pointcutAllMethods() { }

    @Before("pointcutAllMethods()")
    public void beforeAllMethods() {
        logger.info("Working with a classroom");
    }

    @Pointcut("execution(* cat.tecnocampus.controllers.ClassroomController.find*(..))")
    public void pointcutBeginWordFind(){ }

    @After("pointcutBeginWordFind()")
    public void afterBeginWordFind() {
        logger.info("Finding classrooms");
    }

    @Pointcut("execution(* cat.tecnocampus.controllers.ClassroomController.insertBatch(..))")
    public void pointcutInsertBatch() { }

    @Around("pointcutInsertBatch()")
    public int[] dealRequestParam(ProceedingJoinPoint jp) {

        try {
            logger.info("before multiple insert");
            int[] res = (int[]) jp.proceed();
            logger.info("after multiple insert");
            return res;
        } catch (Throwable throwable) {
            logger.error("Showing classrooms: Something went wrong");
            throwable.printStackTrace();
            return new int[0];
        }
    }

}
