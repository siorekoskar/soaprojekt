package com.model;

import com.interceptor.NewElement;
import com.jms.JMSService;
import com.jms.MessageController;
import entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import proj.CategoryTypeDto;
import proj.ElementTypeDto;
import proj.RemoteCatalogue;
import proj.UserDetails;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Remote(RemoteCatalogue.class)
@Stateless
public class Catalogue implements RemoteCatalogue {

    @Inject
    private EntityDao entityDao;

    @Inject
    private JMSService jmsService;

    @Override
    public List<Forest> getForests() {
        return entityDao.getCategories();
    }

    @Override
    public List<CategoryType> getCategoryTypes() {
        return entityDao.getAllCategories();
    }

    @Override
    public List<ElementType> getElementTypes() {
        return entityDao.getElementTypes();
    }

    @Override
    public void sendCategoryType(CategoryTypeDto categoryTypeDto) {
        entityDao.addCategoryType(CategoryTypeDto.categoryTypeBuilder(categoryTypeDto));
    }

    @Override
    public void sendElementType(ElementTypeDto elementTypeDto) {
        entityDao.addElementType(ElementTypeDto.ElementTypeBuilder(elementTypeDto));
    }

    @Override
    public List<Elf> getElves() {
        return entityDao.getElements();
    }

    @Override
    public void addForest(Forest category) {
        entityDao.addCategory(category);
    }

    @Override
    @NewElement
    public int addElf(Elf elf) {
//        jmsService.sendMessageToTopic("ALL", "siema");
        return entityDao.addElement(elf);
    }

    @Override
    public void removeElf(Elf elf) {
        entityDao.removeElement(elf);
    }

    @Override
    public void removeForest(Forest forest) {
        entityDao.removeCategory(forest);
    }

    @Override
    public List<User> getUsers() {
        return entityDao.getUsers();
    }

    @Override
    public boolean changePassword(UserDetails userDetails) {
        User user = entityDao.findUser(userDetails.getUserName());
        String newUserPasswordHash = DigestUtils.sha256Hex(userDetails.getNewPassword());
        String oldUserPasswordHash = DigestUtils.sha256Hex(userDetails.getOldPassword());

        if (user != null && oldUserPasswordHash.equals(user.getPassword())) {
            user.setPassword(newUserPasswordHash);
            entityDao.changePasswordForUser(user);
            return true;
        } else {
            return false;
        }
    }
}
