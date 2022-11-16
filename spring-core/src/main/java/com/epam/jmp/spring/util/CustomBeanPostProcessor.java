package com.epam.jmp.spring.util;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.epam.jmp.spring.exception.ApplicationException;
import com.epam.jmp.spring.model.to.EventTo;
import com.epam.jmp.spring.model.to.TicketTo;
import com.epam.jmp.spring.model.to.UserTo;
import com.epam.jmp.spring.storage.InMemoryStorage;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private String userDataPath;
    private String ticketDataPath;
    private String eventDataPath;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Execute postProcessBeforeInitialization for bean {}", beanName);
        if (bean instanceof InMemoryStorage storageService) {
            try {
                storageService.loadData(UserTo.class, userDataPath);
                storageService.loadData(TicketTo.class, ticketDataPath);
                storageService.loadData(EventTo.class, eventDataPath);
            } catch (IOException e) {
                throw new ApplicationException("Error in bean post processor");
            }
        }
        return bean;
    }
}
