package com.twinkle.cloud.core.usermgmt.config;

import com.twinkle.cloud.common.asm.data.MethodDefine;
import com.twinkle.cloud.common.asm.transformer.AddMethodTransformer;
import com.twinkle.cloud.common.asm.transformer.ClassTransformer;
import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralContentResult;
import com.twinkle.cloud.core.usermgmt.data.HelloRequest;
import com.twinkle.cloud.core.usermgmt.service.HelloWorldService;
import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.slf4j.Logger;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-29 17:35<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
public class HellworldMethodTransformer extends AddMethodTransformer {
    /**
     * Rule Chain Name.
     */
    private String ruleChainName;

    public HellworldMethodTransformer(ClassTransformer _transformer, MethodDefine _methodDefine) {
        super(_transformer, _methodDefine);
    }

    @Override
    public void packMethodNode() {
        InsnList il = this.methodNode.instructions;
        LabelNode labelA = new LabelNode();
        //Need add into the labelNodeList firstly.
        this.labelNodeList.add(labelA);
        il.add(labelA);
        //Line number for the block.
//        il.add(new LineNumberNode(39, labelA));

        FieldInsnNode logNode = new FieldInsnNode(
                Opcodes.GETSTATIC,
                this.classNode.name,
                "log",
                Type.getDescriptor(Logger.class)
        );
        il.add(logNode);
        il.add(new LdcInsnNode("The request body is: {}"));
        il.add(new VarInsnNode(Opcodes.ALOAD, 1));
        il.add(new MethodInsnNode(
                Opcodes.INVOKEINTERFACE,
                Type.getInternalName(Logger.class),
                "info",
                Type.getMethodDescriptor(Type.getType(Void.TYPE), Type.getType(String.class), Type.getType(Object.class))
        ));

        LabelNode labelB = new LabelNode();
        this.labelNodeList.add(labelB);
        il.add(labelB);
        il.add(new VarInsnNode(Opcodes.ALOAD, 0));
        FieldInsnNode serviceNode = new FieldInsnNode(
                Opcodes.GETFIELD,
                this.classNode.name,
                "helloWorldService",
                Type.getDescriptor(HelloWorldService.class)
        );
        il.add(serviceNode);
        il.add(new VarInsnNode(Opcodes.ALOAD, 1));
        il.add(new MethodInsnNode(
                Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(HelloRequest.class),
                "getUserName",
                Type.getMethodDescriptor(Type.getType(String.class))
        ));
        il.add(new MethodInsnNode(
                Opcodes.INVOKEINTERFACE,
                Type.getInternalName(HelloWorldService.class),
                "sayHello",
                Type.getMethodDescriptor(Type.getType(String.class), Type.getType(String.class))
        ));
        il.add(new VarInsnNode(Opcodes.ASTORE, 3));

        LabelNode labelC = new LabelNode();
        this.labelNodeList.add(labelC);
        il.add(labelC);
        il.add(new TypeInsnNode(
                Opcodes.NEW,
                Type.getInternalName(GeneralContentResult.class)
        ));
        il.add(new InsnNode(Opcodes.DUP));
        il.add(new MethodInsnNode(
                Opcodes.INVOKESPECIAL,
                Type.getInternalName(GeneralContentResult.class),
                "<init>",
                Type.getMethodDescriptor(Type.getType(Void.TYPE))
        ));
        il.add(new VarInsnNode(Opcodes.ASTORE, 4));

        LabelNode labelD = new LabelNode();
        this.labelNodeList.add(labelD);
        il.add(labelD);
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new LdcInsnNode(ResultCode.OPERATION_SUCCESS));
        il.add(new MethodInsnNode(
                Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(GeneralContentResult.class),
                "setResultCode",
                Type.getMethodDescriptor(Type.getType(Void.TYPE), Type.getType(String.class))
        ));

        LabelNode labelE = new LabelNode();
        this.labelNodeList.add(labelE);
        il.add(labelE);
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new VarInsnNode(Opcodes.ALOAD, 3));
        il.add(new MethodInsnNode(
                Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(GeneralContentResult.class),
                "setResultContent",
                Type.getMethodDescriptor(Type.getType(Void.TYPE), Type.getType(Object.class))
        ));

        LabelNode labelF = new LabelNode();
        this.labelNodeList.add(labelF);
        il.add(labelF);
        il.add(new VarInsnNode(Opcodes.ALOAD, 4));
        il.add(new InsnNode(Opcodes.ARETURN));

        LabelNode labelG = new LabelNode();
        this.labelNodeList.add(labelG);
        il.add(labelG);
    }
}
