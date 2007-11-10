package org.apache.struts2.uelplugin;

import javax.el.ExpressionFactory;

import com.opensymphony.xwork2.conversion.impl.XWorkConverter;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;

/**
 * Creates JuelValueStacks.
 */
public class UelValueStackFactory implements ValueStackFactory {
	private ExpressionFactory factory;

	private XWorkConverter xworkConverter;

	@Inject
	public void setXWorkConverter(XWorkConverter conv) {
		this.xworkConverter = conv;
	}

	public void initExpressionFactory() {
		if (factory == null) {
			factory = ExpressionFactory.newInstance();
		}
	}

	public ValueStack createValueStack() {
		initExpressionFactory();
		return new UelValueStack(factory, xworkConverter);
	}

	public ValueStack createValueStack(ValueStack stack) {
		initExpressionFactory();
		return new UelValueStack(factory, xworkConverter, stack);
	}
}
