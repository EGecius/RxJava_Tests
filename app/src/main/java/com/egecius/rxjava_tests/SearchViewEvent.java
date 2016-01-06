package com.egecius.rxjava_tests;

import android.support.v7.widget.SearchView;

/**
 * Single Responsibility:
 *
 * Event related to {@link SearchView}, which is their query text change of query submission
 */
class SearchViewEvent {

	/** whether query was submitted */
	public final boolean isSubmitted;
	/** whether text changed since last event */
	public final boolean isTextChanged;
	/** text entered as a query */
	public final String text;

	/**
	 *
	 * @param isSubmitted whether query was submitted
	 * @param isTextChanged whether text changed since last event
	 * @param text text entered as a query
	 */
	public SearchViewEvent(boolean isSubmitted, boolean isTextChanged, String text) {
		this.isSubmitted = isSubmitted;
		this.isTextChanged = isTextChanged;
		this.text = text;
	}

}
