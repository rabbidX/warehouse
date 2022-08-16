package dmitry.garyanov.warehouse.utils;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.*;
import dmitry.garyanov.warehouse.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class InitiateUtils  implements CommandLineRunner {
    private RoleService roleService;
    private UserService userService;
    private ShipmentService shipmentService;
    private ContractorService contractorService;
    private GoodService goodService;

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

        List<Shipment> shipments = createShipments(contractors);
        shipmentService.saveAll(shipments);
        printTable("Shipments", shipmentService);

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
                        contractorService.get(1L).setName("Hoofs&Horns Inc."),
                        contractorService.get(2L).setName("Horns&Hoofs Inc."),
                        contractorService.get(3L).setName("No horns only hoofs Inc.")
                )
        );
    }

    private List<Shipment> createShipments(List<Contractor> contractors) {
        List<Shipment> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Contractor contractor = contractors.get(i);
            for (int j = 0; j < 2; j++) {
                String name = "Shipment №" + (i +1);
                result.add(shipmentService.get(i*2 + j).setName(name).setContractor(contractor));
            }
        }

        return result;
    }

    private List<Good> createGoods(List<Shipment> shipments) {
        return new ArrayList<>();
    }


    private <T extends IEntity> void printTable (String tableName, IService<T> service) {
        System.out.println("\n" + tableName + " table:");
        List<T> list = service.getAll();
        for (T entity: list) {
            System.out.println(entity);
        }

    }
}
