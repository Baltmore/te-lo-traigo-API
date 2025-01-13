# API Te lo Traigo!

Este proyecto es una API REST desarrollada en Java utilizando el framework Spring Boot. Proporciona endpoints para realizar diversas operaciones y gestionar datos de forma eficiente.

---

## Requisitos previos

Antes de iniciar el proyecto, asegúrate de tener instalados los siguientes requisitos:

- **Java Development Kit (JDK)**: Versión 11 o superior.
- **Maven**: Para gestionar las dependencias y construir el proyecto.
- **PostgreSQL/MySQL** (o cualquier base de datos que uses): Configura una base de datos accesible para la aplicación.
- **IDE** (opcional): IntelliJ IDEA, Eclipse, VS Code con soporte para Java.

---

## Configuración inicial

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
   cd tu_repositorio
   ```
   - Descomprimir el la carpeta y archivos.

2. **Configurar .env**
    - Crear el archivo .env en la raiz de el proyecto
    - Copiar los datos que hay dentro de .env.example en el .env
    - Nota: si se cambia el nombre de BD tambien cambiarlo en el SPRING_DATASOURCE_URL: ...5433/{{NOMBRE_BD}}

3. **Instalar dependencias**
   - Ejecuta el siguiente comando para descargar las dependencias del proyecto:
     ```bash
     mvn clean install
     ```

4. **Configurar la base de datos**
   - Ejecutar Docker en nuestra maquina
   - Ejecutar en terminal CMD justo en la raiz del proyecto  '''docker-compose up -d'''
   - Usando cualquier administrador de BD se puede conectar a la BD en el ```
        Host: localhost
        puerto: 5433
        usuario: (el definido en el .env)
        contraseña: (el definido en el .env)
        base de datos: (el definido en el .env)
     ```

4. **Instalar dependencias con maven**
   - Ejecutar ``` mvn install ```
---

## Ejecución de la API

1. **Iniciar el servidor**
   - Desde tu IDE Busca el archivo "ApirestAppication.java" dentro del proyecto y ejecutalo como cualquier archivo .java .
   - Si usas la terminal, ejecuta:
     ```bash
     mvn spring-boot:run
     ```

2. **Acceso a la API**
   - Una vez iniciada, la API estará disponible en `http://localhost:3000` por defecto.

---

## Endpoints principales

| Método | Endpoint             | Descripción                                       |
|--------|----------------------|---------------------------------------------------|
| GET    | /cliente             | Obtiene todas las cliente                         | 
| GET    | /cliente/{id}        | Obtiene una cliente por su ID                     |
| POST   | /cliente             | Crea una nueva cliente                            |
| PUT    | /cliente/{id}        | Actualiza una cliente existente por ID            |
| DELETE | /cliente/{id}        | Elimina una cliente por ID                        |
| GET    | /cotizacion          | Obtiene todas las cotizacion                      |
| GET    | /cotizacion/{id}     | Obtiene una cotizacion por su ID                  |
| GET    | /cotizacion/detalle  | Obtiene una detalle de contizacion y otras tablas |
| POST   | /cotizacion          | Crea una nueva cotizacion                         |
| PUT    | /cotizacion/{id}     | Actualiza una cotizacion existente por ID         |
| DELETE | /cotizacion/{id}     | Elimina una cotizacion por ID                     |
| GET    | /tipo/producto       | Obtiene todas las tipo producto                   |
| GET    | /tipo/producto/{id}  | Obtiene una tipo producto por su ID               |
| POST   | /tipo/producto       | Crea una nueva tipo producto                      |
| PUT    | /tipo/producto/{id}  | Actualiza una tipo producto existente por ID      |
| DELETE | /tipo/producto/{id}  | Elimina una tipo producto por ID                  |
| GET    | /usuario/admin       | Obtiene todas las usuario admin                   |
| GET    | /usuario/admin/{id}  | Obtiene una usuario admin por su ID               |
| POST   | /usuario/admin       | Crea una nueva usuario admin                      |
| PUT    | /usuario/admin/{id}  | Actualiza una usuario admin existente por ID      |
| DELETE | /usuario/admin/{id}  | Elimina una usuario admin por ID                  |

| GET    | /usuario/admin/      | Se utiliza para llenar dator por default de la    |
|        |    inicializar/datos | tabla  tipo producto y ingresar un usuario admin. |

---

## Tests

Para ejecutar los tests del proyecto:
```
    Se agrego un archivo de Insomnia de prueba de Endpoints.
    -- Por default el archivo de insomnia esta configurado para que funcione en el puerto 3000 la API si usted lo cambio puede hacer el cambio en las configuraciones
        grobles de la coleccion de peticiones y cambiarlo al que usted coloco.
    - Para hacer pruebas se tiene que tener levantada la API, y tener instalado Insomnia "https://insomnia.rest/download".
    - Hacer un import de el archivo de pruebas en la aplicacion de Insomnia.
    - Hacer pruebas de cada endpoint como se desee.
```

---

## Licencia

Este proyecto es (FREE LICENSE).

---

## Autor

Desarrollado por [Balmore_Miranda]. Si tienes preguntas, no dudes en contactarme en [miranda6764@gmail.com].

