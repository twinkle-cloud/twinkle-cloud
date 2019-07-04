package com.twinkle.cloud.common.asm.assembly.components;

import com.twinkle.cloud.common.asm.assembly.AbstractAssembler;
import com.twinkle.cloud.common.asm.bytecode.insn.NamedLabelNode;
import com.twinkle.cloud.common.asm.utils.InsnUtil;
import com.twinkle.cloud.common.asm.utils.UniMatcher;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Label assembler
 * <pre>
 *     &lt;LABEL_TITLE&gt;
 * </pre>
 * @author chenxj
 */
public class Label extends AbstractAssembler<LabelNode> {
	/**
	 * Matcher for the label name.
	 */
	private final static UniMatcher<String> matcher = new UniMatcher<>("^[\\w-]+$", (s -> s));

	public Label(int opcode) {super(opcode);}

	@Override
	public LabelNode parse(String text) {
		if(matcher.run(text)) {
			return new NamedLabelNode(matcher.get());
		}
		return fail(text, "Expected: <LABEL_TITLE>");
	}

	@Override
	public String generate(MethodNode method, LabelNode insn) {
		return "LABEL " + InsnUtil.labelName(insn);
	}
}