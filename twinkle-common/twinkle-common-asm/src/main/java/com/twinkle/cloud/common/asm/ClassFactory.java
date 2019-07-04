package com.twinkle.cloud.common.asm;

import com.twinkle.cloud.common.asm.event.Bus;
import com.twinkle.cloud.common.asm.event.components.ClassHierarchyUpdateEvent;
import com.twinkle.cloud.common.asm.event.components.ClassReloadEvent;
import com.twinkle.cloud.common.asm.event.components.ClassRenameEvent;
import com.twinkle.cloud.common.compiler.JavaStringCompiler;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-25 18:13<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public class ClassFactory {
    private final static String STANDARD_CLASS_FULL_NAME="com.twinkle.cloud.standard.StandardClass";
    private final static String STANDARD_CLASS_PATH_NAME="com/twinkle/cloud/standard/StandardClass.class";
    private final static String STANDARD_CLASS_SHORT_NAME="StandardClass.java";
    private String className;
    private byte[] classContent;

    public void createClass(String _className) {
        this.className = _className;
        //动态编译class
        JavaStringCompiler compiler = new JavaStringCompiler();
        Map<String, String> tempContentMap = new HashMap<>(8);
        tempContentMap.put(STANDARD_CLASS_SHORT_NAME, this.getStandardClassBody());

        try {
            Map<String, byte[]> results = compiler.compile(tempContentMap);
            AsmInput tempInput = new AsmInput(STANDARD_CLASS_PATH_NAME, results.get(STANDARD_CLASS_FULL_NAME));

            ClassNode tempNode = tempInput.getClasses().get(STANDARD_CLASS_PATH_NAME);

            Bus.post(new ClassRenameEvent(tempNode, tempNode.name, this.className));
            Bus.post(new ClassReloadEvent(tempNode.name, this.className));
            Bus.post(new ClassHierarchyUpdateEvent());
            log.info("Create standard class and change to new dest name successfully.");
        } catch(IOException ex) {
            log.error("Encountered error while compiling the standard class.", ex);
        }
    }

    public void addMethod() {

    }

    public void addField() {}

    public void addClassAnnotation(){}

//    public Class<?> exportClass(){
//
//    }

    private String getStandardClassBody(){
        StringBuilder tempBuilder = new StringBuilder();
        tempBuilder.append("package com.twinkle.cloud.standard;");
        tempBuilder.append("import lombok.extern.slf4j.Slf4j;");
        tempBuilder.append("@Slf4j");
        tempBuilder.append("public class StandardClass{");
        tempBuilder.append("}");
        return tempBuilder.toString();
    }
}
