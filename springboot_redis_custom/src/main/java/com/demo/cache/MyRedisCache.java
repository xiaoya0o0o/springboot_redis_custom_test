package com.demo.cache;

import com.demo.entity.DicZoneAllEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
@Aspect
public class MyRedisCache {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.demo.cache.MyReidsAn)")
    public void cachePointCut(){}

    @Around("cachePointCut()")
    public Object doCache(ProceedingJoinPoint pdjp ) throws Throwable {
        Object obj = null;
        String key = null;
//        读取注解上面的生成规则
        MethodSignature signature = (MethodSignature) pdjp.getSignature();
        Method method = pdjp.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
        MyReidsAn myReidsAn = method.getAnnotation(MyReidsAn.class);
        String keyEL = myReidsAn.key();
//      解析过程
        ExpressionParser parser = new SpelExpressionParser();
        Expression ep = parser.parseExpression(keyEL);
//      设置解析上下文(有哪些占位符,以及每种占位符的值)
        EvaluationContext ec = new StandardEvaluationContext(); // 参数
        // 获取参数
        Object[] pdjpArgs = pdjp.getArgs();
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i=0;i<parameterNames.length;i++){
            ec.setVariable(parameterNames[i],pdjpArgs[i]);
        }
        // 解析
        key = ep.getValue(ec).toString();
        System.out.println("--key---"+key);
        obj = redisTemplate.opsForValue().get(key);
        //       1 先查看缓存里是否有数据
        if (obj!=null){
            System.out.println("---query redis----"+key);
            return obj;
        }
        //       2 缓存里没有，调用目标方法
        obj = pdjp.proceed();  // 正式的业务代码
        //       3 存入缓存
        redisTemplate.opsForValue().set(key,obj);
        return obj;
    }

}
