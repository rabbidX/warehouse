INSERT INTO public.role (id, name)
VALUES (4, 'admin')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;

INSERT INTO public.role (id, name)
VALUES (5, 'operator')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;

INSERT INTO public.role (id, name)
VALUES (6, 'carrier')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;

INSERT INTO public.role (id, name)
VALUES (7, 'junior_carrier')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;
-----------------------------------------

INSERT INTO public.users (id, name, password)
VALUES (1, 'Ivanov', '$2a$12$3JN9.DQavuwAyFNHFwEXweC967hIi0Da5SjL69xYxKE6xXhjrYhmG')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name, password=EXCLUDED.password;

INSERT INTO public.users (id, name, password)
VALUES (2, 'Petrov', '$2a$12$1IH2FlSy.FG/mdjaNRDSM.zZ2/YMl9jNvCfKsgxq6grhBnZCt2qfC')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name, password=EXCLUDED.password;

INSERT INTO public.users (id, name, password)
VALUES (3, 'Smirnov', '$2a$12$CPrqDpLNXlbP5nWY.mA8FO8SP9WDv6031MrTgKlPLjHb0CYstFv9S')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name, password=EXCLUDED.password;
---------------------------------------

INSERT INTO public.contractor (id, name)
VALUES (1, 'Hooves&Horns Inc.')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;

INSERT INTO public.contractor (id, name)
VALUES (2, 'Horns&Hooves Inc.')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;

INSERT INTO public.contractor (id, name)
VALUES (3, 'No horns only hooves Inc.')
ON CONFLICT (id) DO UPDATE SET
name=EXCLUDED.name;
-------------------------------------

INSERT INTO public.good (id, barcode, name, price, volume, weight)
VALUES (1, '32888', 'Axe', 100, 0.2, 8.2)
ON CONFLICT (id) DO UPDATE SET
barcode=EXCLUDED.barcode, name=EXCLUDED.name, price=EXCLUDED.price, volume=EXCLUDED.volume, weight=EXCLUDED.weight;

INSERT INTO public.good (id, barcode, name, price, volume, weight)
VALUES (2, '32892', 'Hammer', 90, 0.1, 7.8)
ON CONFLICT (id) DO UPDATE SET
barcode=EXCLUDED.barcode, name=EXCLUDED.name, price=EXCLUDED.price, volume=EXCLUDED.volume, weight=EXCLUDED.weight;

INSERT INTO public.good (id, barcode, name, weight)
VALUES (3, '35167', 'Bag of sugar', 100)
ON CONFLICT (id) DO UPDATE SET
barcode=EXCLUDED.barcode, name=EXCLUDED.name, weight=EXCLUDED.weight;
-------------------------------------------

INSERT INTO public.document (id, date, document_type)
VALUES (1, '1973-03-03 12:46:40', 'GoodsReceipt')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, document_type)
VALUES (2, '1973-03-03 12:46:41', 'GoodsReceipt')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, document_type)
VALUES (3, '1973-03-03 12:46:42', 'GoodsReceipt')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, document_type)
VALUES (4, '1973-03-03 12:46:43', 'GoodsReceipt')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, document_type)
VALUES (5, '1973-03-03 12:46:43', 'GoodsReceipt')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, document_type)
VALUES (6, '1973-03-03 12:46:45', 'GoodsReceipt')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, name, contractor_id, document_type)
VALUES (7, '1983-03-03 12:46:40', 'Shipment #1', 1, 'Shipment')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, name=EXCLUDED.name, contractor_id=EXCLUDED.contractor_id, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, name, contractor_id, document_type)
VALUES (8, '1983-03-03 12:46:41', 'Shipment #2', 1, 'Shipment')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, name=EXCLUDED.name, contractor_id=EXCLUDED.contractor_id, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, name, contractor_id, document_type)
VALUES (9, '1983-03-03 12:46:42', 'Shipment #3', 2, 'Shipment')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, name=EXCLUDED.name, contractor_id=EXCLUDED.contractor_id, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, name, contractor_id, document_type)
VALUES (10, '1983-03-03 12:46:43', 'Shipment #4', 2, 'Shipment')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, name=EXCLUDED.name, contractor_id=EXCLUDED.contractor_id, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, name, contractor_id, document_type)
VALUES (11, '1983-03-03 12:46:44', 'Shipment #5', 3, 'Shipment')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, name=EXCLUDED.name, contractor_id=EXCLUDED.contractor_id, document_type=EXCLUDED.document_type;

INSERT INTO public.document (id, date, name, contractor_id, document_type)
VALUES (12, '1983-03-03 12:46:45', 'Shipment #6', 3, 'Shipment')
ON CONFLICT (id) DO UPDATE SET
date=EXCLUDED.date, name=EXCLUDED.name, contractor_id=EXCLUDED.contractor_id, document_type=EXCLUDED.document_type;
------------------------------------------

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (1, 1, 1, 1, '1973-03-03 12:46:40', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (2, 2, 1, 2, '1973-03-03 12:46:41', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (3, 3, 1, 3, '1973-03-03 12:46:42', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (4, 4, 1, 4, '1973-03-03 12:46:43', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (5, 5, 1, 5, '1973-03-03 12:46:44', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (6, 6, 1, 6, '1973-03-03 12:46:45', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (7, 6, 1, 7, '1983-03-03 12:46:40', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (8, 5, 1, 8, '1983-03-03 12:46:41', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (9, 4, 1, 9, '1983-03-03 12:46:42', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (10, 3, 1, 10, '1983-03-03 12:46:43', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (11, 2, 1, 11, '1983-03-03 12:46:44', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;

INSERT INTO public.document_row (id, quantity, good_id, document_id, date, worth)
VALUES (12, 1, 1, 12, '11983-03-03 12:46:46', 0)
ON CONFLICT (id) DO UPDATE SET
quantity=EXCLUDED.quantity, good_id=EXCLUDED.good_id, document_id=EXCLUDED.document_id, date=EXCLUDED.date, worth=EXCLUDED.worth;
