package com.twinkle.cloud.core.usermgmt;

import com.twinkle.cloud.common.compiler.JavaStringCompiler;
import com.twinkle.cloud.core.usermgmt.config.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-20 18:05<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Order(1)
@Slf4j
public class StartupRunner implements CommandLineRunner {
    public final static String PREFIX_CLASSPATH = "";
    @Override
    public void run(String... args) throws Exception {
        log.info("Going to load customized rest controller.");
        // 获取文件流
        ClassPathResource resource = new ClassPathResource("connector" + File.separator + "Hello2Controller.java");
        // 获取文件
        InputStream inputStream = resource.getInputStream();
        File file = resource.getFile();
        String content = FileUtils.readFileToString(file);
        String fullName = "com.twinkle.cloud.core.usermgmt.controller.Hello2Controller";
        String controllerJava = "Hello2Controller.java";
        //动态编译class
        JavaStringCompiler compiler = new JavaStringCompiler();
        Map<String, String> tempContentMap = new HashMap<>();
        tempContentMap.put(controllerJava, content);
        Map<String, byte[]> results = compiler.compile(tempContentMap);
        //加载class
        Class<?> clzMul = compiler.loadClass(fullName, results);

        //获取spring的applicationContext
        ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
        //注册接口到注册中心
        controlCenter(clzMul, applicationContext, 1);
        log.info("Register hello2 successfully.");
    }

    public static void controlCenter(Class<?> controllerClass, ApplicationContext Context, Integer type) throws IllegalAccessException, Exception {
        //获取RequestMappingHandlerMapping
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) Context.getBean("requestMappingHandlerMapping");
        Method getMappingForMethod = ReflectionUtils.findMethod(RequestMappingHandlerMapping.class, "getMappingForMethod", Method.class, Class.class);
        //设置私有属性为可见
        getMappingForMethod.setAccessible(true);
        //获取类中的方法
        Method[] method_arr = controllerClass.getMethods();
        for (Method method : method_arr) {
            //判断方法上是否有注解RequestMapping
            if (method.getAnnotation(RequestMapping.class) != null) {
                //获取到类的RequestMappingInfo
                RequestMappingInfo mappingInfo = (RequestMappingInfo) getMappingForMethod.invoke(requestMappingHandlerMapping, method, controllerClass);
                if (type == 1) {
                    //注册
                    registerMapping(requestMappingHandlerMapping, mappingInfo, controllerClass, method);
                } else if (type == 2) {
                    //取消注册
                    unRegisterMapping(requestMappingHandlerMapping, mappingInfo);
                    registerMapping(requestMappingHandlerMapping, mappingInfo, controllerClass, method);
                } else if (type == 3) {
                    unRegisterMapping(requestMappingHandlerMapping, mappingInfo);
                }

            }
        }
    }

    public static void registerMapping(RequestMappingHandlerMapping requestMappingHandlerMapping, RequestMappingInfo mappingInfo, Class<?> controllerClass, Method method) throws Exception, IllegalAccessException {
        requestMappingHandlerMapping.registerMapping(mappingInfo, controllerClass.newInstance(), method);
    }

    public static void unRegisterMapping(RequestMappingHandlerMapping requestMappingHandlerMapping, RequestMappingInfo mappingInfo) throws Exception, IllegalAccessException {
        requestMappingHandlerMapping.unregisterMapping(mappingInfo);
    }
}
