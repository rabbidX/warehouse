package dmitry.garyanov.warehouse.utils;

import dmitry.garyanov.warehouse.model.*;
import dmitry.garyanov.warehouse.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

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

        List<GoodsReceipt> goodsReceipts = createGoodReceipts();
        goodReceiptService.saveAll(goodsReceipts);
        printTable("Good receipts", goodReceiptService);

        List<Shipment> shipments = createShipments(contractors);
        shipmentService.saveAll(shipments);
        printTable("Shipments", shipmentService);

        List<DocumentRow> documentRows = createGoodReceiptsRows(goodsReceipts);
        List<DocumentRow> shipmentRows = createShipmentRows(shipments, documentRows.size());
        documentRows.addAll(shipmentRows);

        documentRowService.saveAll(documentRows);
        printTable("Document Rows", documentRowService);

        printTable("Contractor", contractorService);

        List<Good> goods = createGoods(shipments);
        goodService.saveAll(goods);
        printTable("Goods", goodService);

    }

    private List<Role> createRoles(){
        return new ArrayList<Role>(
                Arrays.asList(
                        roleService.get(4L).setName("admin"),
                        roleService.get(5L).setName("operator"),
                        roleService.get(6L).setName("carrier")
                )
        );
    }

    private List<User> createUsers(List<Role> roles) {
        List<User> result = new ArrayList<>();
        String[] userNames = {"Ivanov", "Petrov", "Smirnov"};
        for (int i = 0; i < userNames.length && i < roles.size(); i++) {
            User user = userService.get(i + 1).setName(userNames[i]);
            user.setRoles(new ArrayList<>(Arrays.asList(roles.get(i))));
            user.setPassword("pass"+i);
            result.add(user);
        }
        return result;
    }

    private List<Contractor> createContractors() {
        return new ArrayList<Contractor>(
                Arrays.asList(
                        contractorService.get(1L).setName("Hooves&Horns Inc."),
                        contractorService.get(2L).setName("Horns&Hooves Inc."),
                        contractorService.get(3L).setName("No horns only hooves Inc.")
                )
        );
    }

    private List<GoodsReceipt> createGoodReceipts() {
        List<GoodsReceipt> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                String name = "Good receipt \u2116 " + (i + 1);
                result.add(goodReceiptService.get(i*2 + j).setDate(new Date(100000000000l)));
            }
        }

        return result;
    }
    private List<Shipment> createShipments(List<Contractor> contractors) {
        List<Shipment> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Contractor contractor = contractors.get(i);
            for (int j = 1; j < 3; j++) {
                String name = "Shipment \u2116 " + (i + 1);
                result.add(shipmentService.get(i*2 + j).setName(name).setContractor(contractor));
            }
        }

        return result;
    }

    private List<DocumentRow> createGoodReceiptsRows(List<GoodsReceipt> goodsReceipts) throws Exception {
        List<DocumentRow> result = new ArrayList<>();
        int i = 0;
        for (GoodsReceipt goodsReceipt: goodsReceipts) {
            result.add(documentRowService.get(i).setGoodsReceipt(goodsReceipt));
        }
        return result;
    }

    private List<DocumentRow> createShipmentRows(List<Shipment> shipments, int shift) throws Exception {
        List<DocumentRow> result = new ArrayList<>();
        int i = 0;
        for (Shipment shipment: shipments) {
            result.add(documentRowService.get(i + shift).setShipment(shipment));
        }
        return result;
    }
    private List<Good> createGoods(List<Shipment> shipments) {
        return new ArrayList<>();
    }


    private <T extends IEntity> void printTable (String tableName, IService<T> service) {
        System.out.println("\n" + tableName + " table:");
        List<T> list = service.getAll();
        Collections.sort(list, Comparator.comparingLong(IEntity::getId));
        for (T entity: list) {
            System.out.println(entity);
        }

    }
}
