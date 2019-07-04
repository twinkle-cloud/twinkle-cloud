package com.twinkle.cloud.common.asm.assembly.components;

import com.twinkle.cloud.common.asm.assembly.AbstractAssembler;
import com.twinkle.cloud.common.asm.bytecode.insn.NamedJumpInsnNode;
import com.twinkle.cloud.common.asm.utils.InsnUtil;
import com.twinkle.cloud.common.asm.utils.OpcodeUtil;
import com.twinkle.cloud.common.asm.utils.UniMatcher;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Jump essembler
 * <pre>
 *     &lt;LABEL_TITLE&gt;
 * </pre>
 *
 * @author Matt
 */
public class Jump extends AbstractAssembler<JumpInsnNode> {
	/**
	 * Matcher for the label name.
	 */
	private final static UniMatcher<String> matcher
			= new UniMatcher<>("^[\\w-]+$", (s -> s));

	public Jump(int opcode) {super(opcode);}

	@Override
	public JumpInsnNode parse(String text) {
		if(matcher.run(text)) {
			return new NamedJumpInsnNode(opcode, matcher.get());
		}
		return fail(text, "Expected: <LABEL_TITLE>");
	}

	@Override
	public String generate(MethodNode method, JumpInsnNode insn) {
		return OpcodeUtil.opcodeToName(opcode) + " " + InsnUtil.labelName(insn.label);
	}
}