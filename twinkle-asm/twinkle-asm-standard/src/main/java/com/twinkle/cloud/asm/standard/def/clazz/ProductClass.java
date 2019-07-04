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
package com.twinkle.cloud.asm.standard.def.clazz;

import com.twinkle.cloud.asm.standard.constants.AsmConstants;
import com.twinkle.cloud.asm.standard.def.method.AMethodMeta;
import com.twinkle.cloud.asm.standard.def.var.meta.Field;
import com.twinkle.cloud.asm.standard.error.ASMSupportException;
import com.twinkle.cloud.asm.standard.utils.ASMSupportClassLoader;
import com.twinkle.cloud.asm.standard.utils.InterfaceLooper;
import com.twinkle.cloud.asm.standard.utils.Modifiers;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;


/**
 * 
 * @author wensiqun at 163.com(Joe Wen)
 *
 */
public class ProductClass extends MutableClass {
	
    private Class<?> reallyClass;
    
    private volatile boolean searchedInClass = false;
    
    protected ProductClass(ASMSupportClassLoader classLoader){
    	super(classLoader);
    }
    
    public ProductClass(Class<?> cls, ASMSupportClassLoader classLoader) {
    	super(classLoader);
        this.name = cls.getName();
        this.mod = cls.getModifiers();
        if(cls.getSuperclass() != null) {
            this.superClass = classLoader.getType(cls.getSuperclass());
        }
        Class<?>[] itfs = cls.getInterfaces();
        this.interfaces = new IClass[itfs.length];
        for(int i = 0; i<itfs.length; i++) {
        	interfaces[i] = classLoader.getType(itfs[i]);
        }
        reallyClass = cls;
        type = Type.getType(cls);
    }
    
    @Override
    public String getDescription() {
        return Type.getDescriptor(reallyClass);
    }

    public Class<?> getReallyClass() {
        return reallyClass;
    }

    @Override
    public Field getField(final String name) throws NoSuchFieldException {
        
        final LinkedList<Field> found = new LinkedList<>();
        
        for(Field gv : getFields()){
            if(gv.getName().equals(name)){
                found.add(gv);
            }
        }
        
        if(found.isEmpty()) {
            Class<?> fieldOwner = reallyClass;
        	IClass objectType = getClassHolder().getType(Object.class);
            while(fieldOwner != null && !fieldOwner.equals(objectType)) {
            	try {
                    java.lang.reflect.Field f = fieldOwner.getDeclaredField(name);
                    found.add(new Field(this,
                    		classLoader.getType(fieldOwner),
                    		classLoader.getType(f.getType()), f.getModifiers(), name));
                    break;
                } catch (NoSuchFieldException e) {
                }
            	fieldOwner = fieldOwner.getSuperclass();
            }
        }
        
        new InterfaceLooper() {
            @Override
            protected boolean process(Class<?> inter) {
                try {
                    java.lang.reflect.Field f = inter.getDeclaredField(name);
                    found.add(new Field(ProductClass.this,
                    		classLoader.getType(inter),
                    		classLoader.getType(f.getType()), f.getModifiers(), name));
                    return true;
                } catch (NoSuchFieldException e) {
                    return false;
                }
            }
        }.loop(reallyClass.getInterfaces());
        
        if(found.size() == 0) {
            throw new NoSuchFieldException("Not found field " + name);
        } else if(found.size() == 1) {
            return found.getFirst();
        } 

        StringBuilder errorSuffix = new StringBuilder();
        for(Field field : found) {
            errorSuffix.append(field.getDeclaringClass()).append(',');
        }
        throw new ASMSupportException("The field '" + name + "' is ambiguous, found it in class [" + errorSuffix + "]");
    }

	@Override
    public boolean isPrimitive() {
        return reallyClass.isPrimitive();
    }

    @Override
    public boolean isArray() {
        return reallyClass.isArray();
    }
    
    @Override
    public int getDimension() {
        if(!reallyClass.isArray()){
            throw new ASMSupportException("The class " + getName() + " is not array");
        }
        return type.getDimensions();
    }

	@Override
	public AMethodMeta getDeclaredConstructor(IClass... parameterTypes) {
		AMethodMeta result = super.getDeclaredConstructor(parameterTypes);
		if(result == null) {
			parseRealClass();
			result = super.getDeclaredConstructor(parameterTypes);
		}
		return result;
	}

	@Override
	public Collection<AMethodMeta> getDeclaredConstructors() {
		parseRealClass();
		return super.getDeclaredConstructors();
	}

	@Override
	public AMethodMeta getDeclaredMethod(String name, IClass... parameterTypes) {
		AMethodMeta result = super.getDeclaredMethod(name, parameterTypes);
		if(result == null) {
			parseRealClass();
			result = super.getDeclaredMethod(name, parameterTypes);
		}
		return result;
	}

	@Override
	public Collection<AMethodMeta> getDeclaredMethods() {
		parseRealClass();
		return super.getDeclaredMethods();
	}
    
	private void parseRealClass() {
		if(!searchedInClass) {
			synchronized (this) {
				if(!searchedInClass) {
					ClassVisitor cv = new ClassVisitor(AsmConstants.ASM_VERSION){
						@Override
						public MethodVisitor visitMethod(int access, String name,
														 String desc, String signature, String[] exceptions) {
							Type methodType = Type.getMethodType(desc);
							Type[] argumentTypes = methodType.getArgumentTypes();
							IClass[] parameterTypes = new BaseClass[argumentTypes.length];
							for(int i = 0; i<argumentTypes.length; i++) {
								parameterTypes[i] = classLoader.getType(argumentTypes[i].getDescriptor());
							}
							
							IClass returnClass = classLoader.getType(methodType.getReturnType().getDescriptor());
							
							IClass[] exceptionTypes = new IClass[exceptions == null ? 0 : exceptions.length];
							for(int i = 0; i<exceptionTypes.length; i++) {
								exceptionTypes[i] = classLoader.getType(exceptions[i]);
							}

							AMethodMeta meta = new AMethodMeta(classLoader, name, ProductClass.this, ProductClass.this,
									parameterTypes, null, returnClass, exceptionTypes, access);
							
							if(name.equals(AsmConstants.CLINIT)){
								ProductClass.this.addClinitMethod(meta);
							} else if (name.equals(AsmConstants.INIT)) {
								ProductClass.this.addConstructor(meta);
							} else if (Modifiers.isBridge(access)){
								ProductClass.this.getBridgeMethod().add(meta);
							} else {
								ProductClass.this.addDeclaredMethod(meta);
							}
							return super.visitMethod(access, name, desc, signature, exceptions);
						}
					};
					try {
			    		ASMSupportClassLoader classLoader = ProductClass.this.getClassHolder();
			    		InputStream in = classLoader.getResourceAsStream(reallyClass.getName().replace('.', '/') + ".class");
			    		if (in != null) {
			                try {
			                    ClassReader classReader = new ClassReader(in);
			                    classReader.accept(cv, ClassReader.SKIP_DEBUG);
			                } finally {
			                    in.close();
			                }
			            }
					} catch (IOException e) {
			            throw new ASMSupportException(e);
					}
					searchedInClass = true;
				}
			}
		}
	}
	
}
