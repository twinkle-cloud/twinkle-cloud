package com.twinkle.cloud.common.asm.utils;

import com.twinkle.cloud.common.asm.event.Listener;
import com.twinkle.cloud.common.asm.event.components.ResourceUpdateEvent;

import java.util.HashMap;

/**
 * FileMap for input resource files.
 *
 * @author Matt
 */
public class ResourcesMap extends HashMap<String, byte[]> {
	@Listener
	private void onUpdate(ResourceUpdateEvent event) {
		if(event.getResource() != null) {
			put(event.getResourceName(), event.getResource());
		} else {
			remove(event.getResourceName());
		}
	}
}
