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
package com.twinkle.cloud.asm.standard.block.branch;

import com.twinkle.cloud.asm.standard.block.CommonBody;
import com.twinkle.cloud.asm.standard.block.IBody;

/**
 * Representing an else...if com.twinkle.cloud.common.asm.block.
 * 
 * @author WSQ
 *
 * @param <_ElseIF>
 * @param <_Else>
 */
public interface IElseIF<_ElseIF extends IBody, _Else extends IBody> extends CommonBody {
	
	/**
	 * Create an else if com.twinkle.cloud.common.asm.block from current com.twinkle.cloud.common.asm.block
	 * 
	 * @param elseif
	 * @return {@link _ElseIF}
	 */
	_ElseIF elseif(_ElseIF elseif);

	/**
	 * 
	 * Create an else com.twinkle.cloud.common.asm.block from current com.twinkle.cloud.common.asm.block
	 *  
	 * @param els Else com.twinkle.cloud.common.asm.block
	 * @return {@link _Else}
	 */
	_Else else_(_Else els);
	
}
