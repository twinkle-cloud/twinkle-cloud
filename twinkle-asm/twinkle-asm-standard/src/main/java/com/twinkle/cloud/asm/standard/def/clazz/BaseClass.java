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

import com.twinkle.cloud.asm.standard.def.method.AMethodMeta;
import com.twinkle.cloud.asm.standard.def.var.meta.Field;
import com.twinkle.cloud.asm.standard.utils.ASMSupportClassLoader;
import com.twinkle.cloud.asm.standard.utils.jls.TypeUtils;
import org.springframework.asm.Opcodes;
import org.springframework.asm.Type;

import java.util.Collection;


/**
 * Indicate a java class.
 * 
 * @author wensiqun at 163.com(Joe Wen)
 *
 */
public abstract class BaseClass implements IClass {
	
	protected ASMSupportClassLoader classLoader;
	
    protected String name;

    protected int version;

    protected int mod;

    protected IClass superClass;

    protected IClass[] interfaces;
    
    protected Type type;
    
    protected String pkg;
    
    public BaseClass(ASMSupportClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
    public final String getPackage() {
        if(pkg == null){
            pkg = getPackageName(name);
        }
        return pkg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getModifiers() {
        return mod;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public IClass getSuperclass() {
        return superClass;
    }

    @Override
    public final IClass[] getInterfaces() {
    	if(interfaces == null){
    		interfaces = new IClass[0];
    	}
    	IClass[] copy = new IClass[interfaces.length];
    	System.arraycopy(interfaces, 0, copy, 0, interfaces.length);
        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if(obj instanceof BaseClass){
            if(name.equals(((BaseClass)obj).name)){
                return true;
            }
        }else{
            return false;
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return getDescription().hashCode();
    }

    @Override
    public final Type getType() {
        if(type == null){
            type = Type.getType(getDescription());
        }
        return type;
    }

    @Override
    public final boolean isInterface() {
        return (getModifiers() & Opcodes.ACC_INTERFACE) != 0;
    }

    @Override
    public final boolean isAbstract() {
        return (getModifiers() & Opcodes.ACC_ABSTRACT) != 0;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public final int getCastOrder() {
        int order = 9;
        if (type == Type.BOOLEAN_TYPE || name.equals(Boolean.class.getName())) {
            order = 1;
        } else if (type == Type.CHAR_TYPE || name.equals(Character.class.getName())) {
            order = 2;
        } else if (type == Type.BYTE_TYPE || name.equals(Byte.class.getName())) {
            order = 3;
        } else if (type == Type.SHORT_TYPE || name.equals(Short.class.getName())) {
            order = 4;
        } else if (type == Type.INT_TYPE || name.equals(Integer.class.getName())) {
            order = 5;
        } else if (type == Type.LONG_TYPE || name.equals(Long.class.getName())) {
            order = 6;
        } else if (type == Type.FLOAT_TYPE || name.equals(Float.class.getName())) {
            order = 7;
        } else if (type == Type.DOUBLE_TYPE || name.equals(Double.class.getName())) {
            order = 8;
        }
        return order;
    }

    
    @Override
	public ASMSupportClassLoader getClassHolder() {
		return classLoader;
	}

	/**
     * <p>Gets the package name from a {@code String}.</p>
     *
     * <p>The string passed in is assumed to be a class name - it is not checked.</p>
     * <p>If the class is unpackaged, return an empty string.</p>
     *
     * @param className  the className to get the package name for, may be {@code null}
     * @return the package name or an empty string
     */
    public static String getPackageName(String className) {
        if (className == null || className.length() == 0) {
            return "";
        }

        // Strip array encoding
        while (className.charAt(0) == '[') {
            className = className.substring(1);
        }
        // Strip Object type encoding
        if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';') {
            className = className.substring(1);
        }

        final int i = className.lastIndexOf('.');
        if (i == -1) {
            return "";
        }
        return className.substring(0, i);
    }
    
	@Override
	public final boolean isChildOrEqual(IClass otherType) {
        return TypeUtils.isSubtyping(this, otherType);
	}

	@Override
	public IClass getNextDimType() {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean isArray() {
        throw new UnsupportedOperationException();
	}

	@Override
	public int getDimension() {
        throw new UnsupportedOperationException();
	}

	@Override
	public String getDescription() {
        throw new UnsupportedOperationException();
	}

	@Override
	public Field getField(String name) throws NoSuchFieldException {
        throw new UnsupportedOperationException();
	}
	
	@Override
	public Collection<AMethodMeta> getDeclaredConstructors() {
        throw new UnsupportedOperationException();
	}

	@Override
	public AMethodMeta getDeclaredConstructor(IClass... parameterTypes) {
        throw new UnsupportedOperationException();
	}

	@Override
	public Collection<AMethodMeta> getDeclaredMethods() {
        throw new UnsupportedOperationException();
	}

	@Override
	public AMethodMeta getDeclaredMethod(String name, IClass... parameterTypes) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPrimitive() {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean existStaticInitBlock() {
        throw new UnsupportedOperationException();
	}

	@Override
	public IClass getRootComponentClass() {
        throw new UnsupportedOperationException();
	}
    
    
}
