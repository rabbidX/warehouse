package dmitry.garyanov.warehouse.utils;

import dmitry.garyanov.warehouse.conf.ApplicationSecurity;
import dmitry.garyanov.warehouse.model.*;
import dmitry.garyanov.warehouse.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@AllArgsConstructor
public class InitiateUtils  implements CommandLineRunner {
    private RoleService roleService;
    private UserService userService;
    private ShipmentService shipmentService;
    private ContractorService contractorService;
    private GoodService goodService;
    private DocumentRowService documentRowService;
    private GoodReceiptService goodReceiptService;
    private RemainingService remainingService;
    @Override
    public void run(String... args) throws Exception {

        List<Role> roles = createRoles();
        roleService.saveAll(roles);
        printTable("Roles", roleService);

        List<User> users = createUsers(roles);
        userService.saveAll(users);
        printTable("Users", userService);

        List<Contractor> contractors = createContractors();
        contractorService.saveAll(contractors);
        printTable("Contractor", contractorService);

        List<Good> goods = createGoods();
        goodService.saveAll(goods);
        printTable("Goods", goodService);

        List<GoodsReceipt> goodsReceipts = createGoodReceipts();
        goodReceiptService.saveAll(goodsReceipts);
        printTable("Good receipts", goodReceiptService);

        List<Shipment> shipments = createShipments(contractors);
        shipmentService.saveAll(shipments);
        printTable("Shipments", shipmentService);

        List<DocumentRow> goodReceiptRows = createGoodReceiptsRows(goodsReceipts);
        documentRowService.saveAll(goodReceiptRows);

        List<DocumentRow> shipmentRows = createShipmentRows(shipments, goodReceiptRows.size());
        documentRowService.saveAll(shipmentRows);

        printTable("Document Rows", documentRowService);

        printTable("Contractor", contractorService);


    }

    private List<Role> createRoles() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new ArrayList<Role>(
                Arrays.asList(
                        getEntity(roleService, Role.class, 4L).setName("admin"),
                        getEntity(roleService, Role.class, 5L).setName("operator"),
                        getEntity(roleService, Role.class, 6L).setName("carrier")
                )
        );
    }



    private List<User> createUsers(List<Role> roles) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<User> result = new ArrayList<>();
        String[] userNames = {"Ivanov", "Petrov", "Smirnov"};
        for (int i = 0; i < userNames.length && i < roles.size(); i++) {
            User user = getEntity(userService, User.class, i + 1).setName(userNames[i]);
            user.setRoles(new ArrayList<>(Arrays.asList(roles.get(i))));
            user.setPassword(ApplicationSecurity.passwordEncoder().encode("pass"+i));
            result.add(user);
        }
        return result;
    }

    private List<Contractor> createContractors() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new ArrayList<Contractor>(
                Arrays.asList(
                        getEntity(contractorService, Contractor.class, 1L).setName("Hooves&Horns Inc."),
                        getEntity(contractorService, Contractor.class,2L).setName("Horns&Hooves Inc."),
                        getEntity(contractorService, Contractor.class,3L).setName("No horns only hooves Inc.")
                )
        );
    }

    private List<GoodsReceipt> createGoodReceipts() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<GoodsReceipt> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                String name = "Good receipt \u2116 " + (i + 1);
                result.add(getEntity(goodReceiptService, GoodsReceipt.class, i*2 + j).setDate(new Date(100000000000l)));
            }
        }

        return result;
    }
    private List<Shipment> createShipments(List<Contractor> contractors) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Shipment> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Contractor contractor = contractors.get(i);
            for (int j = 1; j < 3; j++) {
                String name = "Shipment \u2116 " + (i + 1);
                result.add(getEntity(shipmentService, Shipment.class,i*2 + j).setName(name).setContractor(contractor).setDate(new Date(150000000000l)));
            }
        }

        return result;
    }

    private List<DocumentRow> createGoodReceiptsRows(List<GoodsReceipt> goodsReceipts) throws Exception {
        List<DocumentRow> result = new ArrayList<>();
        int i = 1;
        Good good = goodService.getById(1L);
        for (GoodsReceipt goodsReceipt: goodsReceipts) {
            result.add(getEntity(documentRowService, DocumentRow.class, i)
                    .setGoodsReceipt(goodsReceipt)
                    .setGood(good)
                    .setDate(goodsReceipt.getDate()));
            i++;
        }
        return result;
    }

    private List<DocumentRow> createShipmentRows(List<Shipment> shipments, int shift) throws Exception {
        List<DocumentRow> result = new ArrayList<>();
        int i = 1;
        Good good = goodService.getById(1L);
        for (Shipment shipment: shipments) {
            result.add(getEntity(documentRowService, DocumentRow.class,i + shift)
                    .setShipment(shipment)
                    .setDate(shipment.getDate())
                    .setGood(good)
            );
            i++;
        }
        return result;
    }
    private List<Good> createGoods() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Good> goods = new ArrayList<>();
        goods.add(getEntity(goodService, Good.class,1).setName("Bag of sugar").setWeight(50.0));
        return goods;
    }


    private <T extends IEntity> void printTable (String tableName, IService<T> service) {
        System.out.println("\n" + tableName + " table:");
        List<T> list = service.getAll();
        Collections.sort(list, Comparator.comparingLong(IEntity::getId));
        for (T entity: list) {
            System.out.println(entity);
        }

    }

    private <T extends IEntity> T getEntity(IService service, Class<T> tClass, long id) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            return (T)service.getById(id);
        } catch (NoSuchElementException e) {
            return tClass.getDeclaredConstructor(tClass).newInstance();
        }
    }
}
