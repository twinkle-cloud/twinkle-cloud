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
package com.twinkle.cloud.asm.standard.block.exception;

import com.twinkle.cloud.asm.standard.block.CommonBody;
import com.twinkle.cloud.asm.standard.block.IBody;

/**
 * Representing a try com.twinkle.cloud.common.asm.block
 * 
 * @author WSQ
 *
 * @param <_Catch>
 * @param <_Finally>
 */
public interface ITry<_Catch extends IBody, _Finally extends IBody> extends CommonBody {

	/**
	 * Create an catch com.twinkle.cloud.common.asm.block from current com.twinkle.cloud.common.asm.block
	 * 
	 * @param catchBlock
	 * @return {@link _Catch}
	 */
	_Catch catch_(_Catch catchBlock);
    
	/**
	 * Create an finally com.twinkle.cloud.common.asm.block from current com.twinkle.cloud.common.asm.block
	 * 
	 * @param finallyBlock
	 * @return {@link _Finally}
	 */
    _Finally finally_(_Finally finallyBlock);
	
}
