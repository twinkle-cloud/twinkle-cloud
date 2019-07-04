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

import com.twinkle.cloud.asm.standard.def.IParam;

/**
 * Increment or decrement operator
 * 
 * @author wensiqun(at)163.com
 *
 */
public interface CrementAction<_P extends IParam> {
    
    /**
     * 
     * Generate seem like operator : --i
     * 
     * @param crement
     * @return {@link _P}
     */
    _P predec(_P crement);
    
    /**
     * 
     * Generate seem like operator : i--
     * 
     * @param crement
     */
    _P postdec(_P crement);
    
    /**
     * 
     * Generate seem like operator : ++i
     * 
     * @param crement
     */
    _P preinc(_P crement);
    
    /**
     * 
     * Generate seem like operator : i++
     * 
     * @param crement
     */
    _P postinc(_P crement);
    
}

