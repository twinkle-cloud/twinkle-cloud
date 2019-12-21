package com.twinkle.cloud.core.usermgmt.config;

import com.twinkle.cloud.common.asm.data.*;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.core.usermgmt.data.HelloRequest;
import com.twinkle.cloud.core.usermgmt.service.HelloWorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-24 18:08<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
//@Configuration
public class AsmBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    // 注册Bean定义，容器根据定义返回bean
        log.info("Going to load customized rest controller.");
        try {
            ClassDefine tempDefine = this.packClassDefine();
            Class<?> tempClass = (new AsmClassLoader()).createClass("com.twinkle.cloud.core.usermgmt.controller.Hello2Controller", tempDefine);
            String tempStr = tempClass.toGenericString();
            log.info("The class is: {}", tempStr);
            //构造bean定义
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                    .genericBeanDefinition(tempClass);
            BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
            //注册bean定义
            registry.registerBeanDefinition("hellController2", beanDefinition);

        } catch (ClassNotFoundException ex) {
            log.warn("Class not found!");
        }
        log.info("Register hello2 successfully.");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * Pack the class definition.
     *
     * @return
     */
    private ClassDefine packClassDefine() {
        ClassDefine tempDefine = new ClassDefine(
                "Hello2Controller",
                "com/twinkle/cloud/core/usermgmt/controller/Hello2Controller"
        );
        tempDefine.setAccess(Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER);
        // Add annotations
        AnnotationDefine tempApiOprAnnotationDefine = new AnnotationDefine(Api.class);
        tempApiOprAnnotationDefine.addValueIntoMap("value", "Helloworld2");
        tempDefine.addAnnotationDefine(tempApiOprAnnotationDefine);
        tempDefine.addAnnotationDefine(new AnnotationDefine(Slf4j.class));
        tempDefine.addAnnotationDefine(new AnnotationDefine(RestController.class));

        tempDefine.addFieldDefine(this.packLogFieldDefine(), this.packServiceFieldDefine());
        MethodDefine tempMethod1 = MethodDefine.getInitMethodDefine();
        MethodDefine tempMethod2 = MethodDefine.getLogInitMethodDefine();
        MethodDefine tempMethod3 = this.packMethodDefine();
        tempMethod1.setClassDefine(tempDefine);
        tempMethod2.setClassDefine(tempDefine);
        tempMethod3.setClassDefine(tempDefine);

        tempDefine.addMethodDefine(tempMethod1, tempMethod2, tempMethod3);
        return tempDefine;
    }

    /**
     * Pack the "log" for Sl4j.log
     *
     * @return
     */
    private FieldDefine packLogFieldDefine(){
        FieldDefine tempDefine = new FieldDefine();
        tempDefine.setAccess(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL);
        tempDefine.setName("log");
        tempDefine.setTypeDefine(new TypeDefine(Logger.class));
        return tempDefine;
    }

    /**
     * Get the helloWorldService parameter definition.
     *
     * @return
     */
    private FieldDefine packServiceFieldDefine(){
        FieldDefine tempDefine = new FieldDefine();
        tempDefine.setAccess(Opcodes.ACC_PRIVATE);
        tempDefine.setName("helloWorldService");
        tempDefine.setTypeDefine(new TypeDefine(HelloWorldService.class));
        tempDefine.addAnnotationDefine(new AnnotationDefine(Autowired.class));
        return tempDefine;
    }

    /**
     * Pack the Method Define.
     *
     * @return
     */
    private MethodDefine packMethodDefine() {
        MethodDefine tempMethodDefine = new MethodDefine();
        tempMethodDefine.setAccess(Opcodes.ACC_PUBLIC);
        tempMethodDefine.setName("getTokenList");

        //Set Return Type.
        TypeDefine tempReturnType = new TypeDefine(GeneralContentResult.class);
        tempReturnType.addGenericType(new TypeDefine(String.class));
        tempMethodDefine.setReturnTypeDefine(tempReturnType);

        //Add method Annotation.
        tempMethodDefine = this.addAnnotationDefine(tempMethodDefine);
        //Add method Parameters with their annotations.
        tempMethodDefine = this.addMethodParameterDefine(tempMethodDefine);
        //Add local Parameters.
        tempMethodDefine = this.addLocalParameterDefine(tempMethodDefine);

        return tempMethodDefine;
    }

    /**
     * pack Method's Annotations.
     *
     * @param _define
     * @return
     */
    private MethodDefine addAnnotationDefine(MethodDefine _define){
        AnnotationDefine tempApiOprAnnotationDefine = new AnnotationDefine(ApiOperation.class);
        tempApiOprAnnotationDefine.addValueIntoMap("value", "Test");
        _define.addAnnotationDefine(tempApiOprAnnotationDefine);

        AnnotationDefine tempUrlAnnotationDefine = new AnnotationDefine(RequestMapping.class);
        List<String> pathList = new ArrayList<>(1);
        pathList.add("authsec/token/list");
        tempUrlAnnotationDefine.addValueIntoMap("value", pathList);
        List<EnumAnnotationValueDefine> methodList = new ArrayList<>(1);
        methodList.add(new EnumAnnotationValueDefine(
                RequestMethod.class,
                "POST"
        ));
        tempUrlAnnotationDefine.addValueIntoMap("method", methodList);

        _define.addAnnotationDefine(tempUrlAnnotationDefine);
        return _define;
    }

    /**
     * Add Method Parameters.
     *
     * @param _define
     * @return
     */
    private MethodDefine addMethodParameterDefine(MethodDefine _define) {
        ParameterDefine tempPara1 = new ParameterDefine("_request", new TypeDefine(HelloRequest.class));
        tempPara1.setAccess(Opcodes.ACC_FINAL);
        tempPara1.addAnnotationDefine(new AnnotationDefine(RequestBody.class));
        AnnotationDefine tempApiParamDefine1 = new AnnotationDefine(ApiParam.class);
        tempApiParamDefine1.addValueIntoMap("value", "请求JSON体");
        tempPara1.addAnnotationDefine(tempApiParamDefine1);
        tempPara1.setStartLabelIndex(0);
        tempPara1.setEndLabelIndex(10);

        ParameterDefine tempPara2 = new ParameterDefine("_userName", new TypeDefine(String.class));
        tempPara2.setAccess(Opcodes.ACC_FINAL);

        AnnotationDefine tempApiParamDefine2 = new AnnotationDefine(ApiParam.class);
        tempApiParamDefine2.addValueIntoMap("value", "请求用户名");

        AnnotationDefine tempApiParamDefine3 = new AnnotationDefine(RequestParam.class);
        tempApiParamDefine3.addValueIntoMap("value", "_userName");

        tempPara2.addAnnotationDefine(tempApiParamDefine2, tempApiParamDefine3);

        tempPara2.setStartLabelIndex(0);
        tempPara2.setEndLabelIndex(10);


        _define.addParameterDefine(tempPara1, tempPara2);

        return _define;
    }

    /**
     * Add Local Parameters.
     *
     * @param _define
     * @return
     */
    private MethodDefine addLocalParameterDefine(MethodDefine _define) {
        ParameterDefine tempPara1 = new ParameterDefine("tempContent", new TypeDefine(String.class));

        TypeDefine tempPara2TypeDefin = new TypeDefine(GeneralContentResult.class);
        tempPara2TypeDefin.addGenericType(new TypeDefine(String.class));
        ParameterDefine tempPara2 = new ParameterDefine("tempResult", tempPara2TypeDefin);


        _define.addLocalParameterDefine(tempPara1, tempPara2);

        return _define;
    }
}
