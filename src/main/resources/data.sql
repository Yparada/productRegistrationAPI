INSERT INTO Cliente (id_cliente, nombre, apellido, dni, telefono, email )
VALUES (1, 'Andres', 'Ramirez', 555, 123456789, 'Aramirez54@yahoo.es');

INSERT INTO Cliente (id_cliente, nombre, apellido, dni, telefono, email )
VALUES (2, 'Carlos', 'Silva', 444, 987654321, 'CSilva@yahoo.es');


INSERT INTO Producto (id_producto, nombre, precio)
VALUES (1, 'Panela', '2500');

INSERT INTO Producto (id_producto, nombre, precio)
VALUES (2, 'Chocolatina jet', '2100');

INSERT INTO detalle_Venta (id_detalle_venta, id_venta, id_producto)
VALUES (1, 1, 1);

INSERT INTO detalle_venta (id_detalle_venta, id_venta, id_producto)
VALUES (2, 2, 2);


INSERT INTO Venta (id_venta, id_cliente, fecha, id_detalle_venta)
VALUES (1, 1, '2020-11-01',1);


INSERT INTO venta (id_venta, id_cliente, fecha, id_detalle_venta)
VALUES (2, 1, '2020-11-02',2);


