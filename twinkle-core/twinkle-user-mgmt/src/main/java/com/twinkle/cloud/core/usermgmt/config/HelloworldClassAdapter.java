package com.twinkle.cloud.core.usermgmt.config;

import com.twinkle.cloud.common.asm.data.AnnotationDefine;
import com.twinkle.cloud.common.asm.data.ClassDefine;
import com.twinkle.cloud.common.asm.data.FieldDefine;
import com.twinkle.cloud.common.asm.data.MethodDefine;
import com.twinkle.cloud.common.asm.transformer.*;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.Type;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-01 16:28<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public class HelloworldClassAdapter extends ClassNode {
    /**
     *
     */
    private ClassDefine classDefine;

    public HelloworldClassAdapter(ClassVisitor cv, ClassDefine _classDefine) {
        super(Opcodes.ASM7);
        this.cv = cv;
        this.classDefine = _classDefine;
        this.initialize();
    }

    /**
     * Initialize the ClassNode.
     */
    private void initialize(){
        this.access = this.classDefine.getAccess();
        this.name = this.classDefine.getInternalName();
        this.superName = this.classDefine.getSuperInternalName();
        this.version = Opcodes.V1_8;
        this.visibleAnnotations = this.getAnnotationNode();
    }


    /**
     * Going to Pack the Annotation Node List.
     *
     * @return
     */
    private List<AnnotationNode> getAnnotationNode() {
        if (CollectionUtils.isEmpty(this.classDefine.getAnnotationDefineList())) {
            return new ArrayList<>();
        }
        return this.classDefine.getAnnotationDefineList().stream().map(this::packAnnotationNode).collect(Collectors.toList());
    }

    /**
     * Pack the Annotation Node.
     *
     * @param _define
     * @return
     */
    private AnnotationNode packAnnotationNode(AnnotationDefine _define) {
        log.debug("Going to add class's annotation {}", _define);
        AnnotationNode tempNode = new AnnotationNode(Type.getDescriptor(_define.getAnnotationClass()));
        Map<String, Object> tempItemMap = _define.getValuesMap();
        if (CollectionUtils.isEmpty(tempItemMap)) {
            return tempNode;
        }
        List<Object> tempValuesList = new ArrayList<>();
        tempItemMap.forEach((k, v) -> {tempValuesList.add(k); tempValuesList.add(v);});
        tempNode.values = tempValuesList;
        return tempNode;
    }

    @Override
    public void visitEnd() {
        // put your transformation code here

        ClassTransformer tempClassTransformer = new ClassTransformer(null);
        tempClassTransformer.transform(this);

        List<FieldDefine> tempFieldDefineList = this.classDefine.getFieldDefineList();
        if(CollectionUtils.isEmpty(tempFieldDefineList)) {
            log.debug("This class[{}] does not have parameter.", this.classDefine.getInternalName());
        } else {
            for(FieldDefine tempFieldDefine : tempFieldDefineList) {
                log.info("Going to add field [{}].", tempFieldDefine.getName());
                AddFieldTransformer addFieldTransformer = new AddFieldTransformer(tempClassTransformer, tempFieldDefine);
                addFieldTransformer.transform(this);
            }
        }

        List<MethodDefine> tempMethodDefineList = this.classDefine.getMethodDefineList();
        if(CollectionUtils.isEmpty(tempMethodDefineList)) {
            log.debug("This class[{}] does not have method.", this.classDefine.getInternalName());
        } else {
            for(MethodDefine tempMethodDefine : tempMethodDefineList) {
                log.info("Going to add Method [{}].", tempMethodDefine.getName());
                AddMethodTransformer tempMethodTransformer;
                if(tempMethodDefine.getName().equals("<init>")){
                    tempMethodTransformer = new AddInitMethodTransformer(tempClassTransformer, tempMethodDefine);
                } else if(tempMethodDefine.getName().equals("<clinit>")){
                    tempMethodTransformer = new AddZinitMethodTransformer(tempClassTransformer, tempMethodDefine);
                } else {
                    tempMethodTransformer = new HellworldMethodTransformer(tempClassTransformer, tempMethodDefine);
                }
                tempMethodTransformer.transform(this);
            }
        }

        accept(cv);
    }
}
