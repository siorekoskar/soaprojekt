package com.model;

import com.gamewsdl.GameSoapServiceImpl;
import com.gamewsdl.GameSoapServiceService;
import com.gamewsdl.PowerDto;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
            sendNewValues(elementType);

            return true;
        } else {
            return false;
        }
    }

    private void sendNewValues(ElementTypeDto elementTypeDto) {

        GameSoapServiceService gameSoapServiceService = new GameSoapServiceService();
        GameSoapServiceImpl gameSoapServicePort = gameSoapServiceService.getGameSoapServicePort();

        List<Elf> elements = generateNewPowers(elementTypeDto.getId());

        List<PowerDto> powerDtos = new ArrayList<>();

        elements.forEach(element -> {
            PowerDto powerDto = new PowerDto();
            powerDto.setId(element.getElfId());
            powerDto.setPowerLevel(element.getPower());
            powerDtos.add(powerDto);
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(8);
                gameSoapServicePort.getNewPowerLevels(powerDtos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Elf> generateNewPowers(int elementType) {
        return entityDao.getElements().stream()
                .filter(element -> element.getElementTypeByElementTypeId().getElementTypeId().equals(elementType))
                .map(this::newPower)
                .collect(Collectors.toList());
    }

    private Elf newPower(Elf element) {
        int newMaybePowerLevel = new Random().nextInt(2);
        int multiplier = new Random().nextBoolean() ? 1 : -1;
        int newPowerLevel = newMaybePowerLevel * multiplier;
        element.setPower(element.getPower() + newPowerLevel);
        return element;
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
