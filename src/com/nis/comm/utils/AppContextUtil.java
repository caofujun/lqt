package com.nis.comm.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("appContextUtil")
@Scope("singleton")
public class AppContextUtil implements ApplicationContextAware {
	private BeanFactory pq;
	private static AppContextUtil pr;

	public static AppContextUtil getInstance() {
		return pr;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.pq = applicationContext;
		pr = this;
	}

	public <T> T getBean(Class<T> clas) {
		return this.pq.getBean(clas);
	}

	public Object getBean(String beanName) {
		return this.pq.getBean(beanName);
	}

	public BeanFactory getFactory() {
		return this.pq;
	}
}