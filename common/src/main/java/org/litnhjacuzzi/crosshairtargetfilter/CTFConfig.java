package org.litnhjacuzzi.crosshairtargetfilter;

public interface CTFConfig {
	default void toggleEntityFilter() {};
	default void toggleBlockFilter() {};
	boolean isEntityFilterEnabled();
	boolean isBlockFilterEnabled();
	FilterType getEntityFilterType();
	FilterType getBlockFilterType();
}
