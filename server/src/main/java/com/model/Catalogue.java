package com.model;

import com.interceptor.NewElement;
import com.jms.JMSService;
import entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import proj.CategoryTypeDto;
import proj.ElementTypeDto;
import proj.RemoteCatalogue;
import proj.UserDetails;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

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
    public Integer updateElement(Elf element) {
        int newMaybePowerLevel = new Random().nextInt(2);
        int multiplier = new Random().nextBoolean() ? 1 : -1;
        int newPowerLevel = newMaybePowerLevel * multiplier;
        element.setPower(element.getPower() + newPowerLevel);
        entityDao.addElement(element);
        return newPowerLevel;
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
        ElementType elementTypeByElementTypeId = elf.getElementTypeByElementTypeId();
        List<Elf> elvesByElementTypeId = elementTypeByElementTypeId.getElvesByElementTypeId();
        List<User> users = new ArrayList<>();

        for (Elf elf1 : elvesByElementTypeId) {
            users.add(elf1.getForestsByForestId().getUsersByUserId());
        }

//        jmsService.sendMessage("username", prepareSelector(users), "new ENEMY");
        jmsService.sendMessage("ALL", "ALL", "new ENEMY");
        return entityDao.addElement(elf);
    }

    private String prepareSelector(List<User> users) {
        StringJoiner stringJoiner = new StringJoiner("OR", "(", ")");
        for (User user : users) {
            String login = user.getLogin();
            stringJoiner.add(String.format("(username = '%s')", login));
        }
        return stringJoiner.toString();
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
