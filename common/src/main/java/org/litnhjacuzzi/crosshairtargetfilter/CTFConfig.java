package org.litnhjacuzzi.crosshairtargetfilter;

public interface CTFConfig {
	boolean isEntityFilterEnabled();
	boolean isBlockFilterEnabled();
	FilterType getEntityFilterType();
	FilterType getBlockFilterType();
}
