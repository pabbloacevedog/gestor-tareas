# Gestor de tareas

### Prueba tecnica Apiux-Coopeuch
Crear applicacion web que permita realizar registro de tareas, con las siguientes funcionalidades:
-listar tareas
-Agregar tareas
-Remover tareas
-Editar tareas

### Consideraciones

Front
-Utilizar las librerias de React + Redux
-Validar que los controles de entrada sean obligatorios
-Crear pruebas unitarias

Back
-Construir utilizando el framework Spring Boot
-Exponer mediante Api Rest
-Utilizar swagger para documentar las API
-Validar que en las acciones agregar/editar sean obligatorios, los campos de entrada
-Utilizar para persistencia lo que mas acomode ORM o JDBC
-Crear pruebas unitarias

###  Inicializar aplicacion
0. Crear base de datos tareas del archivo tareas_tareas.sql
1. Instalar node para utilizar react (frontend) y maven para utlizar spring boot (backend).
2. Ir al directorio /front, para iniciar el frontend

`cd /front`

`npm install`

`npm run start`

3. Ir al directorio /back iniciar spring boot usando maven

`cd /back`

`mvn spring-boot:run`

4. Abrir el navegador y copiar http://localhost:3001
### 

http://localhost:3001/swagger-ui/index.html#/