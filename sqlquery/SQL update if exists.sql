IF EXISTS (
    SELECT [BARCODE]
    FROM TicketsRS
    WHERE BARCODE = '587a4fac-c34f-4840-939b-5a92013fd20d')
BEGIN 
    UPDATE TicketsRS SET [TYPE] = 'err' WHERE BARCODE = '587a4fac-c34f-4840-939b-5a92013fd20d'
END
ELSE
BEGIN 
UPDATE TicketsG SET [TYPE] = 'errr' WHERE BARCODE = '587a4fac-c34f-4840-939b-5a92013fd20d'
END