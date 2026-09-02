MundoTech es una aplicación web desarrollada con Java y Spring Boot, utilizando Thymeleaf para el renderizado del frontend y PostgreSQL como sistema de base de datos.

El proyecto fue desarrollado con el objetivo de aplicar conceptos de desarrollo Full Stack, integrando backend, frontend, persistencia de datos, autenticación y gestión de productos dentro de una misma aplicación.

Tecnologías utilizadas:
Backend
Java 21
Spring Boot 3.5.0
Spring MVC
Spring Data JPA
Spring Security
BCrypt
PostgreSQL
---------------
Frontend
HTML5
CSS3
JavaScript
Thymeleaf
Diseño responsive
----------------
Base de datos
PostgreSQL
Neon
---------------
Deploy
Docker
Render
GitHub
---------------------------------------

MundoTech cuenta con diferentes funcionalidades orientadas a la experiencia de compra y a la administración de productos.
Para crear, editar o eliminar se debe iniciar sesion como admin: admin@admin.com 12345

Usuarios
Registro de usuarios.
Inicio de sesión.
Autenticación mediante Spring Security.
Gestión de roles.
Protección de rutas según permisos.
Contraseñas almacenadas utilizando BCrypt.
---------------------------------------
Productos
Visualización de productos.
Listado de productos.
Información detallada de cada producto.
Gestión de stock.
Gestión de precios.
Categorización de productos.
Imágenes de productos.
----------------------------------------
Carrito
Agregar productos al carrito.
Modificar cantidades.
Eliminar productos.
Visualización del total.
Gestión de los productos seleccionados.
----------------------------------------------
Compras
Gestión del proceso de compra.
Registro de pedidos.
Persistencia de la información en PostgreSQL.
