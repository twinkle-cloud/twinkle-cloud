/**    
 *  Asmsupport is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.twinkle.cloud.asm.standard.action;

import com.twinkle.cloud.asm.standard.def.clazz.IClass;
import com.twinkle.cloud.asm.standard.def.IParam;

/**
 * 
 * Generate com.twinkle.cloud.common.asm.block.method invoke.
 * 
 * @author wensiqun(at)163.com
 *
 */
public interface MethodInvokeAction<_P extends IParam> {
    
    /**
     * <p>
     * Generate com.twinkle.cloud.common.asm.block.method call instructions. for example we generate the following code :
     * </p>
     * 
     * <p style="border:1px solid;width:550px;padding:10px;">
     * <b style="color:#FF3300">String.class.toString();</b><br/>
     * <b style="color:#FF3300">object.test("hello");</b><br/>
     * <b style="color:#FF3300">object.getOther().test("hello");</b><br/>
     * </p>
     * 
     * Corresponding the asmsupport code :
     * 
     * <pre style="border:1px solid;width:550px;padding:10px;">
     * 
     * call(AClassFactory.getType(String.class), "toString");
     * call(paraObj, "test" Value.value("hello"));
     * call(call(paraObj, "getOther"), "test" Value.value("hello"));
     * 
     * </pre>
     * 
     * <p>
     * This com.twinkle.cloud.common.asm.block.method is also to generate static com.twinkle.cloud.common.asm.block.method call. if the com.twinkle.cloud.common.asm.block.method is a static
     * </p>
     * 
     * @param objRef What's the com.twinkle.cloud.common.asm.block.method owner.
     * @param methodName The com.twinkle.cloud.common.asm.block.method name.
     * @param arguments The argument list
     * @return {@link _P}
     */
    _P call(_P objRef, String methodName, _P... arguments);

    /**
     * Generate com.twinkle.cloud.common.asm.block.method invoke, this com.twinkle.cloud.common.asm.block.method equivalence to
     * <pre>
     * call(_this(), methodName, arguments);
     * </pre>
     * 
     * @param methodName
     * @param args
     * @return
     */
    _P call(String methodName, _P... args);
    
    /**
     * 
     * <p>
     * Genreate static com.twinkle.cloud.common.asm.block.method call.
     * </p>
     * 
     * <p style="border:1px solid;width:550px;padding:10px;">
     * <b style="color:#FF3300">Thread.getAllStackTraces();</b><br/>
     * <b style="color:#FF3300">Thread.sleep(1000)</b><br/>
     * </p>
     * 
     * Corresponding the asmsupport code :
     * <pre style="border:1px solid;width:550px;padding:10px;">
     * call(AClassFactory.getType(Thread.class), "getAllStackTraces");
     * call(AClassFactory.getType(Thread.class), "sleep", Value.value(1000));
     * </pre>
     * 
     * 
     * @param owner
     * @param methodName
     * @param arguments
     * @return {@link _P}
     */
    _P call(IClass owner, String methodName, _P... arguments);
    
    /**
     * Invoke static com.twinkle.cloud.common.asm.block.method. the com.twinkle.cloud.common.asm.block.method is similar com.twinkle.cloud.common.asm.block.method {@link #call(IClass, String, _P...)}
     * 
     * @param owner
     * @param name
     * @param arguments
     * @return
     */
    _P call(Class<?> owner, String name, _P... arguments);
    
    
    /**
     * 
     * <p>
     * Generate the construction call. for example we generate the following code : 
     * </p>
     * 
     * 
     * <p style="border:1px solid;width:550px;padding:10px;">
     * String str1 = <b style="color:#FF3300">new String()</b>;<br/>
     * String str2 = <b style="color:#FF3300">new String("hello world")</b>;<br/>
     * </p>
     * 
     * Corresponding the asmsupport code :
     * <pre style="border:1px solid;width:550px;padding:10px;">
     * new_(AClassFactory.defType(String.class));
     * new_(AClassFactory.defType(String.class), Value.value("hello world"));
     * </pre>
     * 
     * 
     * @param owner
     * @param arguments
     * @return {@link _P}
     */
    _P new_(IClass owner, _P... arguments);
    
    /**
     * @param owner
     * @param arguments
     * @return
     * @see #new_(IClass, _P...)
     */
    _P new_(Class<?> owner, _P... arguments);
    
    
    /**
     * This com.twinkle.cloud.common.asm.block.method use in modify class. Generally, if you modify a com.twinkle.cloud.common.asm.block.method,
     * asmsupport will generate a proxy com.twinkle.cloud.common.asm.block.method for the com.twinkle.cloud.common.asm.block.method that's you
     * want to modify. and we can call the original com.twinkle.cloud.common.asm.block.method by this com.twinkle.cloud.common.asm.block.method.
     * for example you want modify the following com.twinkle.cloud.common.asm.block.method.
     * 
     * <pre>
     * public String test(){
     *     return "hello world".
     * }
     * <pre>
     * 
     * And now you want calculate the spend time of this com.twinkle.cloud.common.asm.block.method, you will get
     * the following code after moidfy. the com.twinkle.cloud.common.asm.block.method test@original is a original
     * com.twinkle.cloud.common.asm.block.method.
     * <pre>
     * 
     * public String test@original(){
     *     return "hello world".
     * }
     * 
     * public String test(){
     *     long start = System.currentTimeMillis();
     *     String value = test@original();
     *     System.out.println(System.currentTimeMillis() - start);
     *     return value;
     * }
     * </pre>
     * 
     * As you see the code "test@original()" in com.twinkle.cloud.common.asm.block.method test(). this statement is
     * generate call {@link #callOrig()}
     * @return {@link _P}
     */
    _P callOrig();
    
}
