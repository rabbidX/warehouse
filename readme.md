Goods(ID, Barcode, weight, volume, price, owner, shipment);
Shipment 1-n Goods;
Contractor 1-n Shipment;
User 1-n Role;
Role;

Goods API
GetByID

Shipment API
getAllGoodsByID

Contractor API
create
getAll

spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true