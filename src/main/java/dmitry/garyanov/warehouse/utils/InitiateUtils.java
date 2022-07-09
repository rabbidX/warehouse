package dmitry.garyanov.warehouse.utils;

import com.sun.istack.NotNull;
import dmitry.garyanov.warehouse.model.*;
import dmitry.garyanov.warehouse.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

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

        List<Good> goods = createGoods(shipments);
        goodService.saveAll(goods);
        printTable("Goods", goodService);

    }
    private List<Role> createRoles(){
        return new ArrayList<Role>(
                Arrays.asList(
                        roleService.getById(4).setName("admin"),
                        roleService.getById(5).setName("operator"),
                        roleService.getById(6).setName("carrier")
                )
        );
    }

    private List<User> createUsers(List<Role> roles) {
        List<User> result = new ArrayList<>();

        return result;
    }

    private List<Contractor> createContractors() {
        return new ArrayList<>();
    }

    private List<Shipment> createShipments(List<Contractor> contractors) {
        return new ArrayList<>();
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
