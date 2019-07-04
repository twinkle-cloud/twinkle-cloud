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
package com.twinkle.cloud.asm.standard.utils.jls15_12_2;

import com.twinkle.cloud.asm.standard.def.clazz.IClass;
import com.twinkle.cloud.asm.standard.def.method.AMethodMeta;

import java.util.List;
import java.util.Map;

public interface DetermineMethodSignature {
    
	/**
	 * <p>jsl3 := The Java™ Language Specification Third Edition<br>
	 * A member com.twinkle.cloud.common.asm.block.method is potentially applicable to a com.twinkle.cloud.common.asm.block.method invocation if and only if all of the following are true:
	 * <ol>
	 * <li>The name of the member is identical to the name of the com.twinkle.cloud.common.asm.block.method in the com.twinkle.cloud.common.asm.block.method invocation.</li>
	 * <li>The member is accessible (jsl3 6.6) to the class or interface in which the com.twinkle.cloud.common.asm.block.method invocation appears.</li>
	 * <li>The arity of the member is lesser or equal to the arity of the com.twinkle.cloud.common.asm.block.method invocation.</li>
	 * <li>If the member is a variable arity com.twinkle.cloud.common.asm.block.method with arity n, the arity of the com.twinkle.cloud.common.asm.block.method invocation is greater or equal to n-1.</li>
	 * <li>If the member is a fixed arity com.twinkle.cloud.common.asm.block.method with arity n, the arity of the com.twinkle.cloud.common.asm.block.method invocation is equal to n.</li>
	 * <li>If the com.twinkle.cloud.common.asm.block.method invocation includes explicit type parameters, and the member is a generic com.twinkle.cloud.common.asm.block.method,
	 *   then the number of actual type parameters is equal to the number of formal type parameters.</li>
	 * </ol>
	 * </p>
	 * @return
	 */
	Map<IClass, List<AMethodMeta>> identifyPotentiallyApplicableMethods();
	
	/**
	 * <p>
     * Phase 1 Identify Matching Arity Methods Applicable by Subtyping <br>
     * </p>
     * <b>reference to : The Java™ Language Specification 15.12.2.2</b>
     * @return
     */
    AMethodMeta firstPhase();
    
    /**
	 * <p>
     * Phase 2: Identify Matching Arity Methods Applicable by Method Invocation Conversion
     * </p>
     * <b>reference to : The Java™ Language Specification 15.12.2.3</b>
     * @return
     */
    AMethodMeta secondPhase();
   
    /**
	 * <p>
     * Phase 3: Identify Applicable Variable Arity Methods
     * </p>
     * <b>reference to : The Java™ Language Specification 15.12.2.4</b>
     * @return
     */
    AMethodMeta thirdPhase();
    
    /**
	 * <p>
     * Choosing the Most Specific Method <br>
     * </p>
     * <b>reference to : The Java™ Language Specification 15.12.2.5</b>
     * @return
     */
    AMethodMeta choosingTheMostSpecificMethod(List<AMethodMeta> entities);
}
