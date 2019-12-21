package com.twinkle.cloud.core.usermgmt.config;

import com.alibaba.fastjson.JSONObject;
import com.twinkle.cloud.common.compiler.JavaStringCompiler;
import com.twinkle.cloud.core.usermgmt.controller.TwinkleRestController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-20 22:08<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
//@Configuration
public class AutoBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private TwinkleRestController twinkleRestController;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 注册Bean定义，容器根据定义返回bean
//        log.info("Going to load customized rest controller.");
//
//        try{
//            this.addRestEndPoint();
//        } catch (Exception e) {
//            log.error("Encoutered Exception while adding the method.", e);
//        }

//        try {
//            // 获取文件流
//            ClassPathResource resource = new ClassPathResource("connector" + File.separator + "Hello2Controller.java");
//            // 获取文件
//            InputStream inputStream = resource.getInputStream();
//            File file = resource.getFile();
//            String content = FileUtils.readFileToString(file);
//            String fullName = "com.twinkle.cloud.core.usermgmt.controller.Hello2Controller";
//            String controllerJava = "Hello2Controller.java";
//            //动态编译class
//            JavaStringCompiler compiler = new JavaStringCompiler();
//            Map<String, String> tempContentMap = new HashMap<>();
//            tempContentMap.put(controllerJava, content);
//            Map<String, byte[]> results = compiler.compile(tempContentMap);
//
//            //加载class
//            Class<?> clzMul = compiler.loadClass(fullName, results);
//            clzMul.toGenericString();
//            //构造bean定义
//            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
//                    .genericBeanDefinition(clzMul);
//            BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
//            //注册bean定义
//            registry.registerBeanDefinition("hellController2", beanDefinition);
//        } catch (Exception ex) {
//            log.error("The com.twinkle.cloud.common.asm.block.exception:", ex);
//        }
//        log.info("Register hello2 successfully.");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

//    private void addRestEndPoint() throws NoSuchMethodException{
//        requestMappingHandlerMapping.registerMapping(
//                RequestMappingInfo.paths("/twinkle/{_id}/name/{_name}")
//                        .methods()
//                        .methods(RequestMethod.POST)
//                        .produces(MediaType.APPLICATION_JSON_VALUE)
//                        .build(),
//                twinkleRestController,
//                TwinkleRestController.class.getDeclaredMethod(
//                        "handerMethodWithParamsAndPathVars",
//                        Map.class, Map.class, JSONObject.class
//                )
//        );
//    }
}
