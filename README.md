# sgcr
 Sistema de Gestion de Comercio Remoto (SGCR)

Existen 4 paquetes, cliente, distribuidor, regulador y common, que contienen el
código de las diferentes clases java relacionados directamente con ellos, y clases
de utilidad empleadas por el resto de clases, dentro del common.


A continuación se detallan las principales clases de cada uno de ellos junto con sus
funcionalidades:

Regulador:
Es la Clase que representa al regulador de la actividad comercial y la cual posee
y gestiona los dos servicios de que hacen uso los clientes y distribuidores, es
decir, el Servicio de Autenticación y el Servicio de Mercancías.

Distribuidor:
Representa al distribuidor del sistema de comercio. Posee una serie de atributos
que especifican, su nº de autenticación (id), su nombre, el tipo de actor (cliente
(0), distribuidor (1): para propósitos de autenticación) y los 3 servicios de los
que hace uso, uno local y gestionado por el mismo, el servicio de ventas, y 2
obtenidos de forma remota, el serv de autenticación y el de mercancías.

Cliente:
Representa al cliente del sistema de comercio. Cuenta con una serie de atributos
similares a los del distribuidor que especican su id (cid - client id), su nombre
(cname - client name), su tipo ( para propósitos de autenticación - cliente (0)
) y los 3 servicios de los que hace uso y que obtiene de forma remota mediante
una consulta en el registro (autenticación, mercancías, ventas).

Oferta:
Esta clases representa una oferta de un determinado producto, la cual esta
especificada por sus atributos tipo de producto, cantidad, precio. Aparte de
esto tiene un atributo constante estático que representa el nº de serie utilizado
en las tareas de marshalling y unmarshalling ya que esta clase esta denida
como serializable, de modo que en la comunicación remota se pasa el objeto
completo y no solo una interfaz remota como en el caso de los objetos Remote.

TProducto:
Es un tipo enumerado que representa los diferentes valores que puede tomar un
producto y contiene un método estático para imprimir por pantalla una lista de
los valores que contiene
