package com.interceptor;

import com.model.EntityDao;
import entity.Elf;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparingInt;

@NewElement
@Interceptor
public class NewElementInterceptor {

    @Inject
    private EntityDao elementDao;

    @AroundInvoke
    public Object newElementAuditMethod(InvocationContext invocationContext) throws Exception {
        Map<String, Object> contextData = invocationContext.getContextData();
        Object[] parameters = invocationContext.getParameters();
        Elf element = (Elf) parameters[0];
        List<Elf> elements = element.getElementTypeByElementTypeId().getElvesByElementTypeId();

        Integer maxPowerFromCategory = elements.stream()
                .filter(actualElement -> actualElement.getPower() != null)
                .max(comparingInt(Elf::getPower))
                .map(Elf::getPower)
                .orElse(null);

        if (maxPowerFromCategory != null) {
            if (maxPowerFromCategory < element.getPower()) {
                element.setPower(maxPowerFromCategory);
            }
        }
        return invocationContext.proceed();
    }
}