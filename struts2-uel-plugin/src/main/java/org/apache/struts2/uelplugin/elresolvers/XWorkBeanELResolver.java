package org.apache.struts2.uelplugin.elresolvers;

import com.opensymphony.xwork2.conversion.impl.XWorkConverter;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.util.reflection.ReflectionContextState;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.StrutsException;

import javax.el.ELContext;
import java.util.Map;

public class XWorkBeanELResolver extends AbstractELResolver {
    public XWorkBeanELResolver(Container container) {
        super(container);
    }

    public Object getValue(ELContext elContext, Object target, Object property) {
        if (target != null && property != null) {
            Map<String, Object> reflectionContext = (Map<String, Object>) elContext.getContext(XWorkValueStackContext.class);
            String propertyName = property.toString();
            Class targetType = target.getClass();

            //only handle this if there is such a property
            if (PropertyUtils.isReadable(target, propertyName)) {
                try {
                    Object obj = reflectionProvider.getValue(propertyName, reflectionContext, target);

                    reflectionContext.put(XWorkConverter.LAST_BEAN_CLASS_ACCESSED, targetType);
                    reflectionContext.put(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED, propertyName);

                    //if object is null, and create objects is enabled, lets do it
                    if (obj == null && ReflectionContextState.isCreatingNullObjects(reflectionContext)) {
                        obj = nullHandler.nullPropertyValue(reflectionContext, target, property);
                        PropertyUtils.setProperty(target, propertyName, obj);
                    }

                    elContext.setPropertyResolved(true);
                    return obj;
                } catch (Exception e) {
                    throw new StrutsException(e);
                }
            }
        }

        return null;
    }

    public void setValue(ELContext elContext, Object target, Object property, Object value) {
        if (target != null && property != null) {
            try {
                Map<String, Object> reflectionContext = (Map<String, Object>) elContext.getContext(XWorkValueStackContext.class);
                String propertyName = property.toString();
                Class targetType = target.getClass();

                //only handle this if there is such a property
                if (PropertyUtils.isReadable(target, propertyName)) {

                    Class expectedType = reflectionProvider.getPropertyDescriptor(targetType, propertyName).getWriteMethod().getParameterTypes()[0];
                    Class valueType = value.getClass();

                    //convert value, if needed
                    if (!expectedType.isAssignableFrom(valueType)) {
                        value = xworkConverter.convertValue(reflectionContext, value, expectedType);
                    }
                    reflectionProvider.setValue(propertyName, reflectionContext, target, value);
                    elContext.setPropertyResolved(true);
                }
            } catch (Exception e) {
                throw new StrutsException(e);
            }
        }
    }
}
