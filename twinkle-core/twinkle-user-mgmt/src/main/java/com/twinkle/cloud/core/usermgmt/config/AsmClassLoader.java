package com.twinkle.cloud.core.usermgmt.config;

import com.twinkle.cloud.common.asm.data.ClassDefine;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-07-03 14:00<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class AsmClassLoader extends ClassLoader {
    public Class createClass(String _name, ClassDefine _define) throws ClassNotFoundException {
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor ca = new HelloworldClassAdapter(cw, _define);
        ca.visitEnd();

        byte[] classByteArray = cw.toByteArray();
        return defineClass(_name, classByteArray, 0, classByteArray.length);
    }
}
