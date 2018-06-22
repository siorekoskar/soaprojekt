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
import java.util.*;

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
    public boolean modifyLabel(ElementTypeDto elementType) {
        Optional<ElementType> oElementType = getElementTypes().stream()
                .filter(elem -> elem.getElementTypeId().equals(elementType.getId()))
                .findFirst();

        if (oElementType.isPresent()) {
            ElementType actualElementType = oElementType.get();
            actualElementType.setIntLabel1(elementType.getIntLabel1());
            entityDao.addElementType(actualElementType);
            return true;
        } else {
            return false;
        }
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
        Set<String> users = new HashSet<>();

        for (Elf elf1 : elvesByElementTypeId) {
            users.add(elf1.getForestsByForestId().getUsersByUserId().getLogin());
        }

        jmsService.sendMessage("username", users, elf.toString());
        return entityDao.addElement(elf);
    }

    @Override
    public int changeVariables(Elf element) {
        return entityDao.addElement(element);
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
