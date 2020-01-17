# indra-finance-service

Requerimientos tecnológicos para la aplicación

1. Definir las capas de aplicación y describir la responsabilidad de su uso

El proyecto es full backend , su estructura comprende un servidor de configuraciones con spring cloud llamado indra-config-service, un microservicio para la parte financiera (este servicio puede ser aun mas especifico en su funcionalidad dando una caracteristica mas hacia un microservicio pero para efectos practicos y de tiempo se establecio un microservicio para clientes, obligaciones y pagos) y una base de datos mongoDB corriendo sobre un contenedor de docker.

El proyecto contiene varios paquetes cuya funcionalidad es la siguiente :

config : configuraciones del proyecto y creacion de algunos beans necesarios. (Aqui iria la documentacion de swagger2).

controller : en este paquete se establecieron los controladores para cliente, obligaciones y pago los cuales son los encargados de recibir y responder las peticiones http que les sean hechas desde cualquier front.

dto: en este paquete se situan los dtos que tienen como funcion trasportar informacion hacia el servicio y desde el servicio, estos para no exponer los modelos que estan directamente relacionados con la capa de persistencia.

error: aqui se almacenan en un enum los errores que seran lanzados por la clase de excepciones.

exception : aqui se establecio una clase para el manejo de excepciones la cual hereda de Exception y permite lanzar los errores localizados en el parquete de error

helpers : en este paquete se establecen las herramientas que nos seran de ayuda como : enums, utils, builders etc.

model: aqui se situan las entidades cuyo proposito tienen llevar la informacion que sera persistida a traves de los repositorios.

repository: aqui esta la capa de persistencia cuyo trabajo es almacenar la informacion en la base de datos mongo con ayuda de la interfaz mongorepository y sus metodos.

service : aqui se ubica la capa en la cual va la logica de negocio y en donde se implementan las interfaces definidas para cliente, obligaciones y pago.

resources : aqui se ubican las configuraciones y las variables traidas desde el servidor de configuraciones.


2. Modelado del modelo relacional o no relacional según su criterio, motor de base de datos de su selección, debe generar los script para ese motor.

Se escogio un modelo no relacional, con una base de datos mongoDB en un contenedor de docker. Se elige esta base de datos sobre una relacional dado a la facil implementacion de la misma, me hubiese gustado trabajar con hibernate y oracledb pero por diferentes motivos de facilidad y gestion de la base de datos se elige mongoDB. En el modelo de datos se escogio crear 3 modelos con el fin de realizar un crud tanto para clientes como para obligaciones y el otro modelo para almacenar informacion de los pagos realizados.



3. Es de su selección framework para capa de presentación(preferiblemente Angular, Html5, CSS, JQuery)

Este proyecto no posee capa de presentacion por lo cual es enfocado a backend, lo anterior dado a que mi experiencia se ha llevado acabo mas del lado del servidor que del client.

4. La construcción de los proyectos debe ser gestionada por Maven o Gradle a su selección.

El proyecto esta construido con base en Maven.

5. La conexión entre frontend y backend es a través de http REST

Aunque no posee un frontend, este backend es REST y puede ser consumido a traves de http.

6. Si hace uso de patrones de diseño(Preferible pero no obligatorio) describa la responsabilidad de su uso

Se usa el patron DAO para la utilizacion y creacion de metodos de persistencia a partir de la interfaz mongorepository. 
Se definen interfaces para la modulacion y para que sea mas facil realizar multiples implementaciones de un metodo y de ser necesario se utilice el patron FABRICA


7. Hacer uso de buenas prácticas de construcción de software (TDD, BDD,Pruebas unitarias, Builder, Setters, Gettes, constructores con Lombok, Logs, validaciones en frontend o cualquier otra. )


Se realiza manejo de excepciones, gestion con lombok para constructores, getters, setters logs y pruebas de integracion con peticiones al controlador y con una base de datos embebida mongo.

Tambien se definen variables con nombres que sean intuitivos a la funcion que realizan y se definen varios paquetes para el ordenado de codigo, asi como tambien DTOs para el trasporte de informacion con el fin de que los modelos solo esten presentes en la capa de persistencia y logica.


DESPLIEGUE :



1. Clonar repositorios (servidor de configuraciones y microservicio)

2. Realizar un pull con docker de la imagen mongo 4.2 y realizar un docker run para iniciar el contenedor de docker con esa imagen : docker run -d -p 27017-27019:27017-27019 --name mongodb mongo:4.2

3. conectarse a mongodb mongo  localhost:27017/admin y realizar la creacion de usuario con los dos siguientes comandos :
-use finance
-db.createUser({
   user: 'indra',
   pwd: 'p4ssw0rd',
   roles: [{ role: 'readWrite', db:'finance'}],
   passwordDigestor : 'server'
})

4. importar los dos proyectos en su IDE en mi caso eclipse, poner a correr el servidor de configuraciones y posteriormente el microservicio.

