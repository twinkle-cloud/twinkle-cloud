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
package com.twinkle.cloud.asm.standard.block;

import com.twinkle.cloud.asm.standard.def.IParam;

/**
 * Indicate a program com.twinkle.cloud.common.asm.block, it's will be passes an argument(is the sub type of
 * {@link IParam}) when enter the com.twinkle.cloud.common.asm.block.
 * 
 * @author sqwen
 *
 * @param <_P>
 */
public interface IParamBody<_P extends IParam> extends IBody {

    /**
     * Enter the com.twinkle.cloud.common.asm.block with an argument
     * 
     * @param e
     */
    void body(_P e);

}
