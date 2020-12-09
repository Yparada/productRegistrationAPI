create database carritodb;
use carritodb;

create table Cliente(
	idCliente int not null auto_increment primary key,
    nombre varchar(50),
    apellido varchar(50),
    dni int,
    telefono int,
    email varchar(50)
);

create table Producto(
	idProducto int not null auto_increment primary key,
    nombre varchar(50),
    precio int
);

create table DetalleVenta(
	idDetalleVenta int not null auto_increment primary key,
    idVenta int not null,
    idProducto int not null,
    foreign key(idProducto) references Producto(idProducto)
);

create table Venta(
	idVenta int not null auto_increment primary key,
    idCliente int not null,
    fecha datetime default now(),
    idDetalleVenta int not null,
    foreign key(idCliente) references Cliente(idCliente),
    foreign key(idDetalleVenta) references DetalleVenta(idDetalleVenta)
);





drop table DetalleVenta;
drop table Producto;
drop table Venta;


create user 'user'@'localhost' identified by 'root';
grant all privileges on * . * to 'user'@'localhost';
flush privileges;

commit;



