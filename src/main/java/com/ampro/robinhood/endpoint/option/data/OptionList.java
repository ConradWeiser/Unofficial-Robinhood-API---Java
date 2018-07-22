package com.ampro.robinhood.endpoint.option.data;

import com.ampro.robinhood.endpoint.ApiElementList;

/**
 * Abstraction representing a set of options returned from the
 * {@code /options/...} Robinhood Representational State Transfer (REST)
 * endpoints. This abstraction is only intended to wrap the results of a
 * Robinhood request and should not be used directly. In order to obtain the
 * {@link Option} objects associated with this response, use the
 * {@link OptionList#getResults()} method.
 *
 * @author <a href="https://github.com/albanoj2">Justin Albano</a>
 *
 * @since 0.8.2
 */
public class OptionList extends ApiElementList<Option> {

	@Override
	public boolean requiresAuth() { return true; }

}
