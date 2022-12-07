INSERT INTO public.role (id)
   SELECT temp.id FROM (
        SELECT 4 as id
        UNION ALL
        SELECT 5
        UNION ALL
        SELECT 6
        UNION ALL
        SELECT 7) AS temp
   LEFT JOIN role
   ON temp.id = role.id
   WHERE role.id IS NULL;

UPDATE public.role
	SET name='admin'
	WHERE id=4;
UPDATE public.role
	SET name='operator'
	WHERE id=5;
UPDATE public.role
	SET name='carrier'
	WHERE id=6;
UPDATE public.role
	SET name='junior carrier'
	WHERE id=7;

INSERT INTO public.users (id)
   SELECT temp.id FROM (
        SELECT 1 as id
        UNION ALL
        SELECT 2
        UNION ALL
        SELECT 3) AS temp
   LEFT JOIN users
   ON temp.id = users.id
   WHERE users.id IS NULL;

UPDATE public.users
	SET name='Ivanov', password='$2a$12$3JN9.DQavuwAyFNHFwEXweC967hIi0Da5SjL69xYxKE6xXhjrYhmG'
	WHERE id=1;
UPDATE public.users
	SET name='Petrov', password='$2a$12$1IH2FlSy.FG/mdjaNRDSM.zZ2/YMl9jNvCfKsgxq6grhBnZCt2qfC'
	WHERE id=2;
UPDATE public.users
	SET name='Smirnov', password='$2a$12$CPrqDpLNXlbP5nWY.mA8FO8SP9WDv6031MrTgKlPLjHb0CYstFv9S'
	WHERE id=3;

INSERT INTO public.contractor (id)
   SELECT temp.id FROM (
        SELECT 1 as id
        UNION ALL
        SELECT 2
        UNION ALL
        SELECT 3) AS temp
   LEFT JOIN contractor
   ON temp.id = contractor.id
   WHERE contractor.id IS NULL;

UPDATE public.contractor
	SET name='Hooves&Horns Inc.'
	WHERE id=1;
UPDATE public.contractor
	SET name='Horns&Hooves Inc.'
	WHERE id=2;
UPDATE public.contractor
	SET name='No horns only hooves Inc.'
	WHERE id=3;

INSERT INTO public.good (id)
   SELECT temp.id FROM (
        SELECT 1 as id
        UNION ALL
        SELECT 2
        UNION ALL
        SELECT 3) AS temp
   LEFT JOIN good
   ON temp.id = good.id
   WHERE good.id IS NULL;

UPDATE public.good
	SET barcode='32888', name='Axe', price=100, volume=0.2, weight=8.2
	WHERE id=1;
UPDATE public.good
	SET barcode='32892', name='Hammer', price=90, volume=0.3, weight=5.1
	WHERE id=2;
UPDATE public.good
	SET barcode='35167', name='Bag of sugar', weight=50
	WHERE id=3;

INSERT INTO public.document (id, document_type)
   SELECT temp.id,
        CASE WHEN temp.id < 7 THEN 'GoodsReceipt'
            ELSE 'Shipment'
            END
        FROM (
        SELECT 1 as id
        UNION ALL
        SELECT 2
        UNION ALL
        SELECT 3
        UNION ALL
        SELECT 4
        UNION ALL
        SELECT 5
        UNION ALL
        SELECT 6
        UNION ALL
        SELECT 7
        UNION ALL
        SELECT 8
        UNION ALL
        SELECT 9
        UNION ALL
        SELECT 10
        UNION ALL
        SELECT 11
        UNION ALL
        SELECT 12) AS temp
   LEFT JOIN document
   ON temp.id = document.id
   WHERE document.id IS NULL;

UPDATE public.document
	SET date='1973-03-03 12:46:40', document_type='GoodsReceipt'
	WHERE id=1;
UPDATE public.document
	SET date='1973-03-03 12:46:41', document_type='GoodsReceipt'
	WHERE id=2;
UPDATE public.document
	SET date='1973-03-03 12:46:42', document_type='GoodsReceipt'
	WHERE id=3;
UPDATE public.document
	SET date='1973-03-03 12:46:43', document_type='GoodsReceipt'
	WHERE id=4;
UPDATE public.document
	SET date='1973-03-03 12:46:44', document_type='GoodsReceipt'
	WHERE id=5;
UPDATE public.document
	SET date='1973-03-03 12:46:45', document_type='GoodsReceipt'
	WHERE id=6;

UPDATE public.document
	SET date='1983-03-03 12:46:40', name='Shipment #1', contractor_id=1, document_type='Shipment'
	WHERE id=7;
UPDATE public.document
	SET date='1983-03-03 12:46:41', name='Shipment #2', contractor_id=1, document_type='Shipment'
	WHERE id=8;
UPDATE public.document
	SET date='1983-03-03 12:46:42', name='Shipment #3', contractor_id=2, document_type='Shipment'
	WHERE id=9;
UPDATE public.document
	SET date='1983-03-03 12:46:43', name='Shipment #4', contractor_id=2, document_type='Shipment'
	WHERE id=10;
UPDATE public.document
	SET date='1983-03-03 12:46:44', name='Shipment #5', contractor_id=3, document_type='Shipment'
	WHERE id=11;
UPDATE public.document
	SET date='1983-03-03 12:46:45', name='Shipment #6', contractor_id=3, document_type='Shipment'
	WHERE id=12;

INSERT INTO public.document_row (id)
   SELECT temp.id FROM (
        SELECT 1 as id
        UNION ALL
        SELECT 2
        UNION ALL
        SELECT 3
        UNION ALL
        SELECT 4
        UNION ALL
        SELECT 5
        UNION ALL
        SELECT 6
        UNION ALL
        SELECT 7
        UNION ALL
        SELECT 8
        UNION ALL
        SELECT 9
        UNION ALL
        SELECT 10
        UNION ALL
        SELECT 11
        UNION ALL
        SELECT 12) AS temp
   LEFT JOIN document_row
   ON temp.id = document_row.id
   WHERE document_row.id IS NULL;

UPDATE public.document_row
	SET quantity=1, good_id=1, document_id=1, date='1973-03-03 12:46:40'
	WHERE id=1;
UPDATE public.document_row
	SET quantity=2, good_id=1, document_id=2, date='1973-03-03 12:46:41'
	WHERE id=2;
UPDATE public.document_row
	SET quantity=3, good_id=1, document_id=3, date='1973-03-03 12:46:42'
	WHERE id=3;
UPDATE public.document_row
	SET quantity=4, good_id=1, document_id=4, date='1973-03-03 12:46:43'
	WHERE id=4;
UPDATE public.document_row
	SET quantity=5, good_id=1, document_id=5, date='1973-03-03 12:46:44'
	WHERE id=5;
UPDATE public.document_row
	SET quantity=6, good_id=1, document_id=6, date='1973-03-03 12:46:45'
	WHERE id=6;
UPDATE public.document_row
	SET quantity=6, good_id=1, document_id=7, date='1983-03-03 12:46:40'
	WHERE id=7;
UPDATE public.document_row
	SET quantity=5, good_id=1, document_id=8, date='1983-03-03 12:46:41'
	WHERE id=8;
UPDATE public.document_row
	SET quantity=4, good_id=1, document_id=9, date='1983-03-03 12:46:42'
	WHERE id=9;
UPDATE public.document_row
	SET quantity=3, good_id=1, document_id=10, date='1983-03-03 12:46:43'
	WHERE id=10;
UPDATE public.document_row
	SET quantity=2, good_id=1, document_id=11, date='1983-03-03 12:46:44'
	WHERE id=11;
UPDATE public.document_row
	SET quantity=1, good_id=1, document_id=12, date='1983-03-03 12:46:45'
	WHERE id=12;