package com.als.authorize.sso.common.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

@Component
public class BeanUtils implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    public static<T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            logger.warn("获取Bean失败", e);
        }
        return null;
    }

    public static<T> T getBean(String name, Class<T> clazz) {
        try {
            return applicationContext.getBean(name, clazz);
        } catch (Exception e) {
            logger.warn("获取Bean失败", e);
        }
        return null;
    }

    public static<T> Map<String, T> getBeans(Class<T> clazz) {
        try {
            return applicationContext.getBeansOfType(clazz);
        } catch (Exception e) {
            logger.warn("获取Beans失败", e);
        }
        return null;
    }

    public static <T> T convertBean(final Object bean, Class<T> clazz) {
        return convertBean(bean, clazz, true);
    }

    public static <T> T convertBean(final Object bean, Class<T> clazz, boolean deep) {
        T target = null;
        try {
            target = clazz.newInstance();
            copyProperties(bean, target, deep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    public static  void copyProperties(Object source, Object target) {
        copyProperties(source, target, true);
    }

    public static void copyProperties(Object source, Object target, boolean deep) {
        Class<?> clazz = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(clazz);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                if (source != null) {
                    PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                    if (sourcePd != null) {
                        Method readMethod = sourcePd.getReadMethod();
                        if (readMethod != null/* && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())*/) {
                            try {
                                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
                                    readMethod.setAccessible(true);
                                Object value = readMethod.invoke(source);
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
                                    writeMethod.setAccessible(true);
                                if (isPlainObject(targetPd.getPropertyType()))
                                    value = convertForCopy(value, targetPd.getPropertyType());
                                else if (deep) value = convertBean(value, targetPd.getPropertyType());
                                else continue;
                                writeMethod.invoke(target, value);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
        return getPropertyUtils().getPropertyDescriptors(clazz);
    }

    public static Boolean isPrimitive(Class clazz) {
        try {
            return ((Class) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean isPlainObject(Class clazz) {
        if (clazz.getName().equals("java.lang.String")) return true;
        if (clazz.getName().equals("java.util.Date")) return true;
        if (clazz.getName().equals("java.math.BigDecimal")) return true;
        return isPrimitive(clazz);
    }

    private static Object convertForCopy(final Object value, final Class<?> type) {
        return (value != null) ? convert(value, type) : value;
    }

    private static Object convert(final Object value, final Class<?> type) {
        final Converter converter = getConvertUtils().lookup(type);
        return converter == null ? value : converter.convert(type, value);
    }

    private static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String name) {
        for (final PropertyDescriptor pd : getPropertyDescriptors(clazz)) {
            if (name.toLowerCase().equals(pd.getName().toLowerCase())) return pd;
        }
        return null;
    }

    private static PropertyUtilsBean getPropertyUtils() {
        return BeanUtilsBean.getInstance().getPropertyUtils();
    }

    private static ConvertUtilsBean getConvertUtils() {
        return BeanUtilsBean.getInstance().getConvertUtils();
    }
}
