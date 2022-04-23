INSERT INTO purchase_invoices
    (purchaser_id, entity_id, issue_date, is_payed)
VALUES (1, 5, timestamptz('2021-04-21'), true)
--        ,(5, 1, timestamptz('2017-04-21'), true)
--        ,(6, 2, timestamptz('2021-04-21'), false)
;


INSERT INTO purchase_invoice_items
(invalidated, purchase_invoice_id, item_id, name_on_invoice, quantity, price, tax_rate, discount)
VALUES (false, 1, 1, 'Intel I3 12300', 100, 800.00, 0.23, 0.05),
       (false, 1, 2, 'Intel I3 12300', 50, 1400.00, 0.23, 0.025);
