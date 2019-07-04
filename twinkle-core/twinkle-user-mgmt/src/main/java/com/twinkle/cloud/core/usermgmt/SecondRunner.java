package com.twinkle.cloud.core.usermgmt;

import com.twinkle.cloud.common.compiler.JavaStringCompiler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-20 18:26<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Order(1)
@Slf4j
public class SecondRunner implements CommandLineRunner {
    private MetadataReaderFactory metadataReaderFactory;
    @Autowired
    private GenericWebApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        Resource tempResource = this.getFileResouce();
        MetadataReader metadataReader = getMetadataReaderFactory().getMetadataReader(tempResource);
        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
        sbd.setResource(tempResource);
        sbd.setSource(tempResource);
        context.registerBeanDefinition("Hello2Controller", sbd);
        log.info("Register controller2 successfully.");
    }

    private Resource getFileResouce() throws Exception {
        log.info("Going to load customized rest controller2222.");
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

        File tempFile = new File(file.getParent() + File.separator + "Hello2Controller.class");
        tempFile.createNewFile();
        byte[] tempArray = results.get(fullName);
        if(tempArray == null) {
            log.info("The content is null.");
        }
        FileUtils.writeByteArrayToFile(tempFile, tempArray);

        Resource tempResource = new FileSystemResource(tempFile);
        tempFile.deleteOnExit();
        return tempResource;
    }

    final MetadataReaderFactory getMetadataReaderFactory() {
        if (this.metadataReaderFactory == null) {
            this.metadataReaderFactory = new CachingMetadataReaderFactory();
        }
        return this.metadataReaderFactory;
    }
}
