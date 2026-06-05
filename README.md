# Java Project - Gestor de Telecomunicaciones

Un proyecto educativo en Java que implementa un sistema de gestión para una empresa de telecomunicaciones. Este proyecto tiene como objetivo aprender y practicar conceptos fundamentales de programación en Java.

## ¿De qué trata?

El sistema permite gestionar:
- **Clientes** de la empresa
- **Cuentas** de usuario (Prepago y Postpago)
- **Llamadas** (nacionales e internacionales)
- **Recargas** de saldo
- **Reportes** de facturación y consumo

Es una aplicación de consola que simula las operaciones básicas de una empresa de telefonía.

## Cómo usar

### Requisitos
- Java 17 o superior
- Maven

### Ejecutar el programa

```bash
cd Proyecto_java
mvn clean package
java -jar target/Proyeto_java-1.0-SNAPSHOT.jar
```

### Paso importante

**Para que el programa funcione correctamente y accedas a todas sus funcionalidades, debes cargar primero el archivo `info_clientes.txt`:**

1. Al iniciar el programa, selecciona la **opción 1** en el menú principal: "Cargar clientes"
2. El sistema te pedirá la ubicación del archivo
3. Escribe la ruta donde se encuentra `info_clientes.txt` (por ejemplo: `info_clientes.txt` o la ruta completa)
4. Una vez cargado, ya podrás usar todas las demás opciones del sistema

**Sin ejecutar este paso, el sistema no tendrá clientes y no podrás realizar operaciones.**

## Opciones del menú

1. **Cargar clientes** - Carga los clientes desde el archivo `info_clientes.txt`
2. **Agregar cuenta** - Crea una nueva cuenta de prepago o postpago para un cliente
3. **Registrar llamada** - Registra una llamada nacional o internacional
4. **Agregar recarga** - Agrega una recarga de saldo a una cuenta prepago
5. **Reporte de facturación postpago** - Genera un reporte de facturación para un mes específico
6. **Reporte de recargas** - Genera un reporte de todas las recargas de un mes
7. **Guardar datos** - Guarda el estado actual del sistema en un archivo
8. **Cargar datos** - Carga un sistema guardado anteriormente
9. **Salir** - Cierra la aplicación

## Estructura del Proyecto

```
Proyecto_java/
├── src/main/java/com/puj/proyecto/
│   ├── App/                    # Punto de entrada y interfaz de usuario
│   │   ├── TestConsola.java    # Menú principal
│   │   └── Utils.java          # Funciones auxiliares
│   ├── Model/                  # Clases del modelo
│   │   ├── Cliente.java
│   │   ├── Cuenta.java
│   │   ├── Empresa.java        # Lógica principal del sistema
│   │   ├── IEmpresa.java       # Interfaz del sistema
│   │   ├── Llamada.java
│   │   ├── Llamada_nacional.java
│   │   ├── llamada_internacional.java
│   │   ├── Prepago.java
│   │   ├── Postpago.java
│   │   └── Recarga.java
│   ├── Persistencia/           # Gestión de archivos y datos
│   │   └── Manejo_archivos.java
│   ├── Comparator/             # Comparadores para ordenamiento
│   ├── Enums/                  # Enumeraciones
│   │   └── Paises_disponibles.java
│   └── Excepciones/            # Excepciones personalizadas
├── pom.xml                     # Configuración Maven
└── info_clientes.txt           # Archivo de clientes (datos de ejemplo)
```

## Concepto de Aprendizaje

Este es un proyecto educativo diseñado para practicar:
- Programación orientada a objetos (POO)
- Herencia y polimorfismo
- Gestión de archivos
- Estructuras de datos (listas, colecciones)
- Manejo de excepciones
- Interfaz de línea de comandos (CLI)
- Serialización de objetos
- Enumeraciones

## Formato del archivo `info_clientes.txt`

El archivo debe seguir este formato:

```
#CLIENTES
#nombre------------cedula---------direccion
Carlos Torres*1014862818*calle 77 No. 34-56 Bogota
Maria Roa Lopez*4123455*calle 37 No. 24-56 Cali
Oswaldo Williams*1923777*calle 7 No. 3-56 Bogota
#FIN
```

Cada cliente debe estar separado por asteriscos (*) en el formato: `nombre*cedula*direccion`

## Ejemplo de uso

1. Inicia el programa
2. Selecciona opción **1** y carga `info_clientes.txt`
3. Selecciona opción **2** para crear una cuenta para uno de los clientes
4. Selecciona opción **3** para registrar una llamada
5. Selecciona opción **5** o **6** para generar reportes

## Nota

Este es un proyecto de aprendizaje, por lo que el código puede mejorarse en aspectos como validación más robusta de datos, manejo de errores más profundo y pruebas unitarias.

---

**Autor:** Luis Cortes  
**Universidad:** Pontificia Universidad Javeriana (PUJ)
