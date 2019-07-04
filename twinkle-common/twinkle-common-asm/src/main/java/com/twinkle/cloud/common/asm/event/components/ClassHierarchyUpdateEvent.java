package com.twinkle.cloud.common.asm.event.components;

import com.twinkle.cloud.common.asm.event.Event;

/**
 * Event for when a class hierarchy is changed. Can be done when a class is renamed, a super is
 * changed, and when an interface list is modified.
 *
 * @author Matt
 */
public class ClassHierarchyUpdateEvent extends Event {}