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

/**
 * 
 * Create Program Block
 * 
 * @author wensiqun(at)163.com
 *
 */
public interface CreateBlockAction<IF_TYPE, While_TYPE, DoWhile_TYPE, ForEach_TYPE, Try_TYPE, Synchronized_TYPE> {

    /**
     * Create an if program com.twinkle.cloud.common.asm.block
     */
    IF_TYPE if_(IF_TYPE ifBlock);
    
    /**
     * Create a while program com.twinkle.cloud.common.asm.block
     */
    While_TYPE while_(While_TYPE whileLoop);
    
    /**
     * Create a do...while program com.twinkle.cloud.common.asm.block
     */
    DoWhile_TYPE dowhile(DoWhile_TYPE doWhile);
    
    /**
     * Create a for each program com.twinkle.cloud.common.asm.block
     */
    ForEach_TYPE for_(final ForEach_TYPE forEach);
    
    
    /**
     * Create a try program com.twinkle.cloud.common.asm.block
     */
    Try_TYPE try_(final Try_TYPE tryPara);
    
    /**
     * Create synchronized com.twinkle.cloud.common.asm.block.
     */
    Synchronized_TYPE sync(Synchronized_TYPE sync);
	
}
