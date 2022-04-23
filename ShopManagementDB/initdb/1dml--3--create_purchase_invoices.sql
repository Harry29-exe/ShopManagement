INSERT INTO sales_invoices
    (seller_id, entity_id, issue_date, is_payed)
VALUES
    (1, 1, timestamptz('2021-04-21'), false),
    (2, 1, timestamptz('2017-04-21'), true),
    (2, 2, timestamptz('2021-04-21'), false);

INSERT INTO sales_invoice_items
(sales_invoice_id, invalidated, item_id, name_on_invoice, quantity, price, tax_rate, discount)
VALUES (1, false, 1, 'Intel I3 12300', 3, 800, 23, 0),
       (1, false, 2, 'Intel I5 12500', 1, 1400, 23, 0),

       (2, false, 1, 'Intel I3 12300', 5, 800, 23, 0),
       (2, false, 2, 'Intel I5 12500', 10, 1400, 23, 50),
       (2, false, 3, 'Intel I7 12700', 2, 2100, 23, 0),

       (3, false, 4, 'AMD R3 5400', 2, 750, 23, 0),
       (3, false, 5, 'AMD R5 5600', 1, 1200, 23, 0)
;

