package com.twinkle.cloud.asm.standard.constants;

import org.springframework.asm.Opcodes;

import java.util.UUID;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-21 15:37<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface AsmConstants {
    String CLINIT = "<clinit>";
    String CLINIT_PROXY = "&clinit&";

    String INIT = "<init>";
    String INIT_PROXY = "&init&";
    String SUPER = "super";
    String THIS = "this";
    String METHOD_PROXY_SUFFIX = "@original_" + UUID.randomUUID().toString().replace('-', '$');

    int ASM_VERSION = Opcodes.ASM5;
}
