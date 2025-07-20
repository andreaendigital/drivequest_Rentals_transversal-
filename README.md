# Sistema para catalogar y administrar su flota de vehículos de alquiler

Consta del desarrollo de una aplicación de software de escritorio de mediano nivel, a través de técnicas basadas en los fundamentos del paradigma de programación orientado a objetos

## Descripción del Proyecto :scroll:

Caso planteado: 

La empresa DriveQuest Rentals está en la búsqueda de un sistema avanzado para catalogar y administrar su flota de vehículos de alquiler, distinguidos en dos principales categorías: vehículos para carga y vehículos para pasajeros, cada uno con especificaciones distintas como la capacidad de carga y el número máximo de pasajeros, respectivamente. El proyecto demanda la creación de una arquitectura de software basada en principios de programación orientada a objetos, empleando clases abstractas para definir los vehículos, interfaces para los cálculos fiscales y descuentos, y una clase auxiliar dedicada a la gestión integral de la flota. Se subraya la importancia de asegurar una identificación única para cada vehículo mediante su patente, la organización eficaz de la información utilizando colecciones, y la implementación de técnicas de concurrencia y sincronización para elevar la eficiencia del sistema. La solución incluirá el uso de archivos externos para la persistencia de datos y la integración de manejo de excepciones para una robusta gestión de errores, todo ello estructurado en capas definidas para el acceso a datos, la lógica de negocio, y la interfaz de usuario.

## Sobre el proyecto 🚀

### ✨ Requerimientos ✨

En otras palabras, el desarrollo de la aplicación debe responder a los siguientes requerimientos: 

-	Identificación única de vehículos: cada vehículo debe tener una patente única.
- Clases y herencia: uso de una clase padre abstracta y clases hijas sin subclases para vehículos de carga y pasajeros.
- Métodos específicos: implementación de métodos para mostrar datos de vehículos, ajustados por tipo.
- Constructores y métodos: inclusión de constructores vacíos y sobrecargados, además de métodos accesadores y mutadores.

- Cálculo de boleta: un método en la interfaz para calcular y mostrar el detalle de la boleta, incluyendo IVA y descuentos.
-    IVA: 19 %
-    DESCUENTO_CARGA: 7 %
-    DESCUENTO_PASAJEROS: 12 %

- Gestión de vehículos: una clase auxiliar para validar la no duplicidad de vehículos por patente, listar vehículos, y filtrar por duración de arriendo (cantidad de días de arriendo igual o superior a 7).
- Interfaz de usuario: agregar, listar vehículos, mostrar boletas y obtener cantidad de vehículos con arriendos largos desde la clase Main.
- También integrar excepciones ‘try-catch’ para prevenir fallos del programa.


## Visuales :mage_woman:

Esquema de archivos / paquetes

<img width="365" height="500" alt="Captura de pantalla 2025-07-19 211646" src="https://github.com/user-attachments/assets/fc2c6777-d0c7-4886-a8b2-5e3940f24a5c" />

Menu y carga inicial de archivos, si no existe, inicia con flota vacía

<img width="502" height="279" alt="Captura de pantalla 2025-07-20 004211" src="https://github.com/user-attachments/assets/13b93ccf-dc9b-45ff-99bb-a38078905ef0" />

Listado de Flota existente: 

<img width="822" height="178" alt="Captura de pantalla 2025-07-20 004222" src="https://github.com/user-attachments/assets/4b6aa1e2-31ad-4351-a106-4e31a556e542" />

Proceso para arrendar vehículo: 

<img width="842" height="300" alt="Captura de pantalla 2025-07-20 004251" src="https://github.com/user-attachments/assets/573dee89-3b2f-4ae6-94c1-bc05ca7a37e0" />

En el listado general se aprecia el estado Disponible o Arrendado:

<img width="891" height="214" alt="Captura de pantalla 2025-07-20 004308" src="https://github.com/user-attachments/assets/89ad33fb-9959-46f1-a989-0620477cd25a" />

Lista aquellos con más de 7 días 

<img width="915" height="163" alt="Captura de pantalla 2025-07-20 004317" src="https://github.com/user-attachments/assets/5455c1e4-aad0-449d-91c9-18aa37371739" />

Conteo total de autos en la flota: 

<img width="294" height="121" alt="Captura de pantalla 2025-07-20 004328" src="https://github.com/user-attachments/assets/3bac6d08-bedf-41da-bf4f-3c9b47185d70" />

Generación masiva según número que ingrese usuario

<img width="673" height="209" alt="Captura de pantalla 2025-07-20 004346" src="https://github.com/user-attachments/assets/8db78a42-ee97-4947-9d26-96b1210c663c" />

Al cerrar el programa los datos se guardan creando persistencia:

<img width="530" height="149" alt="Captura de pantalla 2025-07-20 004400" src="https://github.com/user-attachments/assets/23ceaa4a-0694-49fa-8a63-c9b65d941b43" />

Ejemplo de Manejo de errores, en este caso en la validación del patrón de la patente:

<img width="689" height="81" alt="Captura de pantalla 2025-07-20 004714" src="https://github.com/user-attachments/assets/4f8af113-898e-45c7-8ef4-4c70437ed8f0" />

## Autores ⚡ 

- **Andrea Rosero** ⚡  - [Andrea Rosero](https://github.com/andreaendigital)

