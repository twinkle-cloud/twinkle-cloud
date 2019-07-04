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
import com.twinkle.cloud.asm.standard.error.ASMSupportException;
import com.twinkle.cloud.asm.standard.utils.ASMSupportClassLoader;
import com.twinkle.cloud.asm.standard.def.var.meta.Field;
import com.twinkle.cloud.asm.standard.def.method.AMethodMeta;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public abstract class MutableClass extends BaseClass {
    
    /**
     * store bridge com.twinkle.cloud.common.asm.block.method.
     * 1. overried com.twinkle.cloud.common.asm.block.method that return type is child of super com.twinkle.cloud.common.asm.block.method return type.
     * 2. generice type com.twinkle.cloud.common.asm.block.method(implement future)
     */
    private volatile List<AMethodMeta> bridgeMethods = new ArrayList<>();
    
    /**
     * All com.twinkle.cloud.common.asm.block.method that declared in this class
     */
    protected volatile ConcurrentMap<String, AMethodMeta> declaredMethods = new ConcurrentHashMap<>();

    /**
     * All constructor that declared in this class
     */
    protected volatile ConcurrentMap<String, AMethodMeta> constructors = new ConcurrentHashMap<>();
    
    /**
     * The static com.twinkle.cloud.common.asm.block
     */
    protected volatile AMethodMeta clinitMethod;

    /**
	 * All fields
	 */
    private Set<Field> fields = new HashSet<>();
    
    //available only create enum class
    private int enumNum;

	public MutableClass(ASMSupportClassLoader classLoader) {
		super(classLoader);
	}

	@Deprecated
	public int getEnumNum() {
		return enumNum;
	}
	
	@Deprecated
	public void setEnumNum(int enumNum) {
		this.enumNum = enumNum;
	}

	/**
	 * Save bridge com.twinkle.cloud.common.asm.block.method in order to override com.twinkle.cloud.common.asm.block.method with different return type of com.twinkle.cloud.common.asm.block.method.
	 * @return
     */
    public List<AMethodMeta> getBridgeMethod() {
		return bridgeMethods;
	}
    
    /**
     * Add static com.twinkle.cloud.common.asm.block.method
     * 
     * @param clinit
     */
    public void addClinitMethod(AMethodMeta clinit) {
    	if(!AsmConstants.CLINIT.equals(clinit.getName())) {
		    throw new ASMSupportException("The static com.twinkle.cloud.common.asm.block name must be <clinit> in byte code layer.");
	    }
    	if(clinitMethod == null) {
    		synchronized (this) {
			    if(clinitMethod == null) {
			    	clinitMethod = clinit;
			    	return;
			    }	
			}
    	}
	    throw new ASMSupportException("The static com.twinkle.cloud.common.asm.block name must be <clinit> in byte code layer.");
    }
    
    /**
     * 
     * @param constructor
     */
    public void addConstructor(AMethodMeta constructor) {
    	if(!AsmConstants.INIT.equals(constructor.getName())) {
    		throw new ASMSupportException("The constructor name must be <init> in byte code layer.");
    	}
    	AMethodMeta previours = constructors.putIfAbsent(getMethodCacheKey(constructor), constructor);
    	if(previours != null) {
    		throw new ASMSupportException("The constructor " + constructor.getMethodString() + 
    				" has alread exist, override it right now!");
    	}
    }
    
    /**
     * Add com.twinkle.cloud.common.asm.block.method
     * 
     * @param method
     */
    public void addDeclaredMethod(AMethodMeta method) {
    	AMethodMeta previous = declaredMethods.putIfAbsent(getMethodCacheKey(method), method);
    	if(previous != null) {
    		throw new ASMSupportException("The com.twinkle.cloud.common.asm.block.method " + method.getMethodString() + " has already exist, override it right now!");
    	}
    } 
    
    @Override
	public boolean existStaticInitBlock() {
    	return clinitMethod != null;
	}
    
    @Override
	public AMethodMeta getDeclaredConstructor(IClass... parameterTypes) {
    	return declaredMethods.get(getMethodCacheKey(AsmConstants.CLINIT, parameterTypes));
	}

	@Override
	public Collection<AMethodMeta> getDeclaredConstructors() {
		return constructors.values();
	}
    
    @Override
	public AMethodMeta getDeclaredMethod(String name, IClass... parameterTypes) {
    	return declaredMethods.get(getMethodCacheKey(name, parameterTypes));
	}
	
    @Override
	public Collection<AMethodMeta> getDeclaredMethods() {
		return declaredMethods.values();
	}
    
	protected Set<Field> getFields() {
		return fields;
	}
    
	/**
     * 
     * add
     * @param field
     */
    public void addField(Field field) {
        getFields().add(field);
    }

    protected String getMethodCacheKey (AMethodMeta meta) {
    	return getMethodCacheKey(meta.getName(), meta.getParameterTypes());
    }

    protected String getMethodCacheKey (String name, IClass... parameterTypes) {
    	StringBuilder keySb = new StringBuilder(name);
    	if(parameterTypes != null) {
        	for(IClass type : parameterTypes) {
        		keySb.append(type.getDescription());
        	}
    	}
    	return keySb.toString();
    }
    
    
}
