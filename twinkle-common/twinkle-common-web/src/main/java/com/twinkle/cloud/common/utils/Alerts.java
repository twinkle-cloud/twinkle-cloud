/**
 * Project Name:liz-common-com.twinkle.cloud.common.asm.utils
 * File Name:Alerts.java
 * Package Name:com.twinkle.cloud.common.com.twinkle.cloud.common.asm.utils
 * Date:Jun 6, 20169:33:32 PM
 * Copyright (c) 2016, chenxj All Rights Reserved.
 *
*/

package com.twinkle.cloud.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ClassName:Alerts <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     Jun 6, 2016 9:33:32 PM <br/>
 * @author   chenxj
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Alerts{

	/** Model key used during view rendering. */
	public static final String MODEL_KEY = "alerts";
	/**
	 * ClassName: Alert <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON -- Optional. <br/>
	 * date: Jun 6, 2016 9:34:12 PM <br/>
	 *
	 * @author chenxj
	 * @version Alerts
	 * @since JDK 1.8
	 */
	public static class Alert{

		private final String code;

		private final Object[] arguments;

		public Alert(String code, Object... arguments) {
			super();
			this.code = code;
			this.arguments = arguments;
		}

		public String getCode() {
			return code;
		}

		public Object[] getArguments() {
			return arguments.clone();
		}

	}

	private final List<Alert> successes = new ArrayList<Alert>();

	private final List<Alert> infos = new ArrayList<Alert>();

	private final List<Alert> warnings = new ArrayList<Alert>();

	private final List<Alert> errors = new ArrayList<Alert>();

	private final List<List<Alert>> alerts = new ArrayList<List<Alert>>();

	public Alerts() {
		alerts.add(successes);
		alerts.add(infos);
		alerts.add(warnings);
		alerts.add(errors);
	}

	public static Alerts create() {
		return new Alerts();
	}

	public Alerts addSuccess(String message, Object... arguments) {
		successes.add(new Alert(message, arguments));
		return this;
	}

	public Alerts addInfo(String message, Object... arguments) {
		infos.add(new Alert(message, arguments));
		return this;
	}

	public Alerts addError(String message, Object... arguments) {
		errors.add(new Alert(message, arguments));
		return this;
	}

	public Alerts addWarning(String message, Object... arguments) {
		warnings.add(new Alert(message, arguments));
		return this;
	}

	public List<Alert> getInfos() {
		return infos;
	}

	public List<Alert> getErrors() {
		return errors;
	}

	public List<Alert> getWarnings() {
		return warnings;
	}

	public List<Alert> getSuccesses() {
		return successes;
	}

	public boolean isEmpty() {
		for (List<Alert> message : alerts) {
			if (!message.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public List<Alert> getAlerts() {
		List<Alert> concat = new ArrayList<Alert>();
		for (List<Alert> alertList : alerts) {
			concat.addAll(alertList);
		}
		return concat;
	}

	public void embed(Model model) {
		if (model.containsAttribute(MODEL_KEY)) {
			throw new IllegalArgumentException(
					"Model key is already taken. Key: " + MODEL_KEY);
		}
		model.addAttribute(MODEL_KEY, this);
	}

	public void embed(ModelMap model) {
		if (model.containsAttribute(MODEL_KEY)) {
			throw new IllegalArgumentException(
					"Model key is already taken. Key: " + MODEL_KEY);
		}
		model.addAttribute(MODEL_KEY, this);
	}

	public void embed(RedirectAttributes redirectAttributes) {
		if (redirectAttributes.containsAttribute(MODEL_KEY)) {
			throw new IllegalArgumentException(
					"Model key is already taken. Key: " + MODEL_KEY);
		}
		redirectAttributes.addFlashAttribute(MODEL_KEY, this);
	}
}

