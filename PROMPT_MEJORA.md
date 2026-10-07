# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/accountmanagement/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/accountmanagement/AccountManagementApplication.java` — `reactor.core.publisher`: El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/domain/port/in/CreateAccountPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/domain/port/in/ModifyAccountPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/domain/port/in/DeleteAccountPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/domain/port/out/AccountPersistencePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/domain/port/out/NotificationPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/domain/port/out/ValidationPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/NotificationAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/NotificationAdapter.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/ValidationAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/ValidationAdapter.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/exception/GlobalExceptionHandler.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `DeleteAccountCommand.getClientId`: Se invoca `getClientId` sobre `DeleteAccountCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `DeleteAccountCommand.getOperationId`: Se invoca `getOperationId` sobre `DeleteAccountCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `ValidationPort.validateAccountType`: Se invoca `validateAccountType` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `DeleteAccountCommand.getAccountType`: Se invoca `getAccountType` sobre `DeleteAccountCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `NotificationPort.sendAccountCreatedNotification`: Se invoca `sendAccountCreatedNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `AccountPersistencePort.findByAccountId`: Se invoca `findByAccountId` sobre `AccountPersistencePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `NotificationPort.sendAccountModifiedNotification`: Se invoca `sendAccountModifiedNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `ValidationPort.validateAccountCanBeDeleted`: Se invoca `validateAccountCanBeDeleted` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `NotificationPort.sendAccountDeletedNotification`: Se invoca `sendAccountDeletedNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/exception/AccountOperationException.java` — `ErrorType.name`: Se invoca `name` sobre `ErrorType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/exception/AccountOperationException.java` — `ErrorType.getDefaultMessage`: Se invoca `getDefaultMessage` sobre `ErrorType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountRequest.getClientId`: Se invoca `getClientId` sobre `ModifyAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `AccountMapper.toCommand`: Se invoca `toCommand` sobre `AccountMapper`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `AccountMapper.toResponse`: Se invoca `toResponse` sobre `AccountMapper`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.findByAccountId`: Se invoca `findByAccountId` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.findByClientId`: Se invoca `findByClientId` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.findActiveAccounts`: Se invoca `findActiveAccounts` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.patchAccount`: Se invoca `patchAccount` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `ValidationPort.validateClient`: Se invoca `validateClient` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `NotificationPort.sendNotification`: Se invoca `sendNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `ValidationPort.validateAccount`: Se invoca `validateAccount` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.save`: Se invoca `save` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.update`: Se invoca `update` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.deleteByAccountId`: Se invoca `deleteByAccountId` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.findByAccountId`: Se invoca `findByAccountId` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.existsByAccountId`: Se invoca `existsByAccountId` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `CreateAccountRequest.setBalance`: Se invoca `setBalance` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `CreateAccountRequest.setStatus`: Se invoca `setStatus` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `AccountService.getAccount`: Se invoca `getAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`: io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Master

### Brecha de conocimiento
Ha trabajado identificando los atributos de calidad más importantes para los requerimientos de una cuenta / proyecto y ha diseñado arquitecturas de software que favorezcan estos atributos de calidad

### Misión / candidato
Candidato con experiencia como Master en Backend

### Reto
- Tema: Diseño de arquitectura
- Seniority: master-l3
- Tipo: mixed
- Título: Diseño de arquitectura de un sistema de gestión de cuentas
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de atributos de calidad — objetivo: Identificar los atributos de calidad más importantes para el sistema de gestión de cuentas. — entregable (NO resolver): Lista de atributos de calidad priorizados para el sistema de gestión de cuentas.
- Fase 2: Diseño de la arquitectura — objetivo: Diseñar una arquitectura que favorezca los atributos de calidad identificados. — entregable (NO resolver): Diagrama de relaciones del sistema de gestión de cuentas y descripción de las decisiones de diseño tomadas.
- Fase 3: Evaluación de trade-offs — objetivo: Evaluar y justificar las decisiones de diseño tomadas en términos de trade-offs. — entregable (NO resolver): Descripción de al menos dos trade-offs significativos en el diseño de la arquitectura y justificación de las decisiones tomadas.
- Fase 4: Comunicación de la arquitectura — objetivo: Comunicar la arquitectura diseñada a diferentes audiencias. — entregable (NO resolver): Presentación de la arquitectura diseñada, adaptada a diferentes audiencias.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>account-management</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>account-management</name>
    <description>Sistema de gestión de cuentas con arquitectura hexagonal y manejo reactivo</description>

    <properties>
        <java.version>21</java.version>
        <spring-boot.version>3.5.6</spring-boot.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <r2dbc.version>1.0.5.RELEASE</r2dbc.version>
        <lombok.version>1.18.34</lombok.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
            <version>3.2.3</version>
        </dependency>

        <!-- R2DBC -->
        <dependency>
            <groupId>io.r2dbc</groupId>
            <artifactId>r2dbc-postgresql</artifactId>
            <version>${r2dbc.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
            <version>2.6.0</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
            <optional>true</optional>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.20.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>kafka</artifactId>
            <version>1.20.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/AccountManagementApplication.java ===
package com.pragma.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Hooks;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
public class AccountManagementApplication {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(AccountManagementApplication.class, args);
    }

    @Bean
    public CircuitBreakerConfig defaultCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .recordExceptions(
                    org.springframework.dao.DataAccessResourceFailureException.class,
                    java.util.concurrent.TimeoutException.class,
                    java.io.IOException.class
                )
                .build();
    }

    @Bean
    public TimeLimiterConfig defaultTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(2000))
                .cancelRunningFuture(true)
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: account-management-service
  profiles:
    active: dev
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/account_db
    username: postgres
    password: postgres
    pool:
      enabled: true
      initial-size: 5
      max-size: 20
      max-idle-time: 30m
      validation-query: SELECT 1
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: account-management-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
      acks: all
      retries: 3
      properties:
        enable.idempotence: true

server:
  port: 8080
  shutdown: graceful

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,info,prometheus
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
  metrics:
    tags:
      application: ${spring.application.name}

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.dao.DataAccessResourceFailureException
          - java.util.concurrent.TimeoutException
          - java.io.IOException
    instances:
      accountPersistence:
        baseConfig: default
        waitDurationInOpenState: 10s
        failureRateThreshold: 60
  timelimiter:
    configs:
      default:
        timeoutDuration: 2s
        cancelRunningFuture: true
    instances:
      accountPersistence:
        baseConfig: default

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method
    tagsSorter: alpha
    docExpansion: none

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/model/Account.java ===
package com.pragma.accountmanagement.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public final class Account {
    private final String accountId;
    private final String clientId;
    private final AccountType accountType;
    private final BigDecimal balance;
    private final String currency;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastUpdated;
    private final AccountStatus status;
    private final String operationId;

    public enum AccountType {
        SAVINGS, CHECKING, INVESTMENT
    }

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    private Account(Builder builder) {
        this.accountId = builder.accountId;
        this.clientId = builder.clientId;
        this.accountType = builder.accountType;
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.createdAt = builder.createdAt;
        this.lastUpdated = builder.lastUpdated;
        this.status = builder.status;
        this.operationId = builder.operationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) && 
               Objects.equals(operationId, account.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, operationId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                ", status=" + status +
                ", operationId='" + operationId + '\'' +
                '}';
    }

    public static final class Builder {
        private String accountId;
        private String clientId;
        private AccountType accountType;
        private BigDecimal balance;
        private String currency;
        private LocalDateTime createdAt;
        private LocalDateTime lastUpdated;
        private AccountStatus status;
        private String operationId;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder clientId(@NotNull String clientId) {
            this.clientId = Objects.requireNonNull(clientId, "clientId cannot be null");
            return this;
        }

        public Builder accountType(@NotNull AccountType accountType) {
            this.accountType = Objects.requireNonNull(accountType, "accountType cannot be null");
            return this;
        }

        public Builder balance(@NotNull @Positive BigDecimal balance) {
            this.balance = Objects.requireNonNull(balance, "balance cannot be null");
            if (balance.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("balance must be positive");
            }
            return this;
        }

        public Builder currency(@NotNull @Size(min = 3, max = 3) String currency) {
            this.currency = Objects.requireNonNull(currency, "currency cannot be null");
            if (currency.length() != 3) {
                throw new IllegalArgumentException("currency must be a 3-letter code");
            }
            return this;
        }

        public Builder createdAt(@NotNull LocalDateTime createdAt) {
            this.createdAt = Objects.requireNonNull(createdAt, "createdAt cannot be null");
            return this;
        }

        public Builder lastUpdated(@NotNull LocalDateTime lastUpdated) {
            this.lastUpdated = Objects.requireNonNull(lastUpdated, "lastUpdated cannot be null");
            return this;
        }

        public Builder status(@NotNull AccountStatus status) {
            this.status = Objects.requireNonNull(status, "status cannot be null");
            return this;
        }

        public Builder operationId(@NotNull String operationId) {
            this.operationId = Objects.requireNonNull(operationId, "operationId cannot be null");
            return this;
        }

        public Account build() {
            Objects.requireNonNull(clientId, "clientId cannot be null");
            Objects.requireNonNull(accountType, "accountType cannot be null");
            Objects.requireNonNull(balance, "balance cannot be null");
            Objects.requireNonNull(currency, "currency cannot be null");
            Objects.requireNonNull(createdAt, "createdAt cannot be null");
            Objects.requireNonNull(lastUpdated, "lastUpdated cannot be null");
            Objects.requireNonNull(status, "status cannot be null");
            Objects.requireNonNull(operationId, "operationId cannot be null");
            
            if (accountId == null) {
                this.accountId = java.util.UUID.randomUUID().toString();
            }
            
            return new Account(this);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/model/OperationId.java ===
package com.pragma.accountmanagement.domain.model;

import java.util.Objects;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;

public final class OperationId {
    private final String value;

    private OperationId(String value) {
        this.value = value;
    }

    public static OperationId generate() {
        return new OperationId(UUID.randomUUID().toString());
    }

    public static OperationId from(@NotNull String value) {
        Objects.requireNonNull(value, "OperationId value cannot be null");
        if (!value.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")) {
            throw new IllegalArgumentException("Invalid OperationId format");
        }
        return new OperationId(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationId that = (OperationId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/port/in/CreateAccountPort.java ===
package com.pragma.accountmanagement.domain.port.in;


import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

public interface CreateAccountPort {
    /**
     * Crea una nueva cuenta para un cliente.
     * 
     * @param command Comando que contiene los datos necesarios para crear la cuenta.
     * @param operationId Identificador único de la operación para garantizar idempotencia.
     * @return Mono que emite la cuenta creada o un error si la operación falla.
     */
    Mono<Account> createAccount(CreateAccountCommand command, OperationId operationId);
    
    /**
     * Comando que encapsula los datos necesarios para crear una cuenta.
     */
    final class CreateAccountCommand {
        private final String clientId;
        private final Account.AccountType accountType;
        private final String currency;
        private final String initialDeposit;

        public CreateAccountCommand(String clientId, Account.AccountType accountType, String currency, String initialDeposit) {
            this.clientId = clientId;
            this.accountType = accountType;
            this.currency = currency;
            this.initialDeposit = initialDeposit;
        }

        public String getClientId() {
            return clientId;
        }

        public Account.AccountType getAccountType() {
            return accountType;
        }

        public String getCurrency() {
            return currency;
        }

        public String getInitialDeposit() {
            return initialDeposit;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/port/in/ModifyAccountPort.java ===
package com.pragma.accountmanagement.domain.port.in;



import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

public interface ModifyAccountPort {
    
    Mono<Account> modifyAccount(ModifyAccountCommand command, OperationId operationId);
    
    final class ModifyAccountCommand {
        private final String accountId;
        private final String clientId;
        private final Account.AccountType accountType;
        private final java.math.BigDecimal balance;
        private final String currency;
        private final Account.AccountStatus status;
        
        private ModifyAccountCommand(Builder builder) {
            this.accountId = builder.accountId;
            this.clientId = builder.clientId;
            this.accountType = builder.accountType;
            this.balance = builder.balance;
            this.currency = builder.currency;
            this.status = builder.status;
        }
        
        public String getAccountId() {
            return accountId;
        }
        
        public String getClientId() {
            return clientId;
        }
        
        public Account.AccountType getAccountType() {
            return accountType;
        }
        
        public java.math.BigDecimal getBalance() {
            return balance;
        }
        
        public String getCurrency() {
            return currency;
        }
        
        public Account.AccountStatus getStatus() {
            return status;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder {
            private String accountId;
            private String clientId;
            private Account.AccountType accountType;
            private java.math.BigDecimal balance;
            private String currency;
            private Account.AccountStatus status;
            
            private Builder() {}
            
            public Builder accountId(String accountId) {
                this.accountId = accountId;
                return this;
            }
            
            public Builder clientId(String clientId) {
                this.clientId = clientId;
                return this;
            }
            
            public Builder accountType(Account.AccountType accountType) {
                this.accountType = accountType;
                return this;
            }
            
            public Builder balance(java.math.BigDecimal balance) {
                this.balance = balance;
                return this;
            }
            
            public Builder currency(String currency) {
                this.currency = currency;
                return this;
            }
            
            public Builder status(Account.AccountStatus status) {
                this.status = status;
                return this;
            }
            
            public ModifyAccountCommand build() {
                return new ModifyAccountCommand(this);
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/port/in/DeleteAccountPort.java ===
package com.pragma.accountmanagement.domain.port.in;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

public interface DeleteAccountPort {
    
    Mono<Account> deleteAccount(DeleteAccountCommand command, OperationId operationId);
    
    final class DeleteAccountCommand {
        private final String accountId;
        private final String reason;
        
        private DeleteAccountCommand(Builder builder) {
            this.accountId = builder.accountId;
            this.reason = builder.reason;
        }
        
        public String getAccountId() {
            return accountId;
        }
        
        public String getReason() {
            return reason;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder {
            private String accountId;
            private String reason;
            
            private Builder() {}
            
            public Builder accountId(String accountId) {
                this.accountId = accountId;
                return this;
            }
            
            public Builder reason(String reason) {
                this.reason = reason;
                return this;
            }
            
            public DeleteAccountCommand build() {
                return new DeleteAccountCommand(this);
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/port/out/AccountPersistencePort.java ===
package com.pragma.accountmanagement.domain.port.out;


import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface AccountPersistencePort {
    
    Mono<Account> save(Account account);
    
    Mono<Account> findById(String accountId);
    
    Flux<Account> findByClientId(String clientId);
    
    Mono<Boolean> existsById(String accountId);
    
    Mono<Boolean> existsByClientIdAndAccountType(String clientId, Account.AccountType accountType);
    
    Mono<Void> deleteById(String accountId);
    
    Mono<Account> update(Account account);
    
    Mono<Long> count();
    
    Flux<Account> findAll();
    
    Mono<Account> findByOperationId(String operationId);
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/port/out/NotificationPort.java ===
package com.pragma.accountmanagement.domain.port.out;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para el sistema de notificaciones.
 * Define la interfaz que el dominio usa para notificar eventos
 * relacionados con cuentas sin conocer la implementación concreta.
 */
public interface NotificationPort {

    /**
     * Notifica la creación exitosa de una cuenta.
     * @param account La cuenta creada
     * @param operationId Identificador de la operación para idempotencia
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyAccountCreated(Account account, OperationId operationId);

    /**
     * Notifica la modificación exitosa de una cuenta.
     * @param account La cuenta modificada
     * @param operationId Identificador de la operación para idempotencia
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyAccountUpdated(Account account, OperationId operationId);

    /**
     * Notifica la eliminación de una cuenta.
     * @param accountId Identificador de la cuenta eliminada
     * @param operationId Identificador de la operación para idempotencia
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyAccountDeleted(String accountId, OperationId operationId);

    /**
     * Notifica un fallo en la operación de cuenta.
     * @param operationId Identificador de la operación que falló
     * @param reason Razón del fallo
     * @return Mono vacío que indica completación exitosa
     */
    Mono<Void> notifyOperationFailed(OperationId operationId, String reason);
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/domain/port/out/ValidationPort.java ===
package com.pragma.accountmanagement.domain.port.out;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

/**
 * Puerto de salida para validación de datos externos.
 * Permite al dominio validar reglas de negocio que dependen
 * de sistemas externos sin acoplar la implementación.
 */
public interface ValidationPort {

    /**
     * Valida que el cliente existe y está activo en el sistema externo.
     * @param clientId Identificador del cliente a validar
     * @return Mono que emite verdadero si el cliente es válido
     */
    Mono<Boolean> validateClientExists(String clientId);

    /**
     * Valida que el tipo de cuenta es permitido para el cliente.
     * @param clientId Identificador del cliente
     * @param accountType Tipo de cuenta solicitado
     * @return Mono que emite verdadero si el tipo es permitido
     */
    Mono<Boolean> validateAccountTypeAllowed(String clientId, AccountType accountType);

    /**
     * Valida que el saldo inicial cumple con los requisitos del tipo de cuenta.
     * @param accountType Tipo de cuenta
     * @param initialBalance Saldo inicial propuesto
     * @return Mono que emite verdadero si el saldo es válido
     */
    Mono<Boolean> validateInitialBalance(AccountType accountType, java.math.BigDecimal initialBalance);

    /**
     * Valida que la moneda es soportada por el sistema.
     * @param currency Código de moneda ISO 4217
     * @return Mono que emite verdadero si la moneda es válida
     */
    Mono<Boolean> validateCurrencySupported(String currency);

    /**
     * Valida el límite de cuentas por cliente.
     * Un cliente no puede tener más de N cuentas activas.
     * @param clientId Identificador del cliente
     * @return Mono que emite verdadero si el cliente puede abrir más cuentas
     */
    Mono<Boolean> validateAccountLimitNotExceeded(String clientId);

    /**
     * Obtiene las cuentas activas de un cliente para validación cruzada.
     * @param clientId Identificador del cliente
     * @return Flux de cuentas activas del cliente
     */
    Flux<Account> getActiveAccountsByClient(String clientId);

    /**
     * Valida la integridad de los datos de la cuenta antes de persistir.
     * @param account Cuenta a validar
     * @return Mono que completa si la validación es exitosa
     * @throws AccountOperationException si la validación falla
     */
    Mono<Void> validateAccountIntegrity(Account account);
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/rest/dto/CreateAccountRequest.java ===
package com.pragma.accountmanagement.infrastructure.rest.dto;

import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO de solicitud para la creación de cuentas.
 * Utiliza Jakarta Validation para garantizar la integridad de los datos
 * recibidos en las peticiones de creación.
 */
public class CreateAccountRequest {

    @NotBlank(message = "El identificador del cliente es obligatorio")
    @Size(min = 1, max = 50, message = "El identificador del cliente debe tener entre 1 y 50 caracteres")
    private String clientId;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private AccountType accountType;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    @Digits(integer = 15, fraction = 2, message = "El saldo inicial debe tener como máximo 15 enteros y 2 decimales")
    private BigDecimal initialBalance;

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "El código de moneda debe tener exactamente 3 caracteres (ISO 4217)")
    @Pattern(regexp = "^[A-Z]{3}$", message = "El código de moneda debe ser un código ISO 4217 válido en mayúsculas")
    private String currency;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String description;

    @Size(max = 100, message = "El identificador de operación no puede exceder 100 caracteres")
    private String operationId;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String clientId, AccountType accountType, BigDecimal initialBalance, 
                                 String currency, String description, String operationId) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.description = description;
        this.operationId = operationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", initialBalance=" + initialBalance +
                ", currency='" + currency + '\'' +
                ", description='" + (description != null ? description.substring(0, Math.min(description.length(), 50)) + "..." : null) + '\'' +
                ", operationId='" + operationId + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/rest/dto/ModifyAccountRequest.java ===
package com.pragma.accountmanagement.infrastructure.rest.dto;


import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class ModifyAccountRequest {

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    private AccountType accountType;

    @NotBlank(message = "La moneda no puede estar vacía")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres")
    private String currency;

    @NotNull(message = "El estado de la cuenta no puede ser nulo")
    private Account.AccountStatus status;

    @NotNull(message = "El saldo no puede ser nulo")
    @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
    private BigDecimal balance;

    public ModifyAccountRequest() {
    }

    public ModifyAccountRequest(AccountType accountType, String currency, Account.AccountStatus status, BigDecimal balance) {
        this.accountType = accountType;
        this.currency = currency;
        this.status = status;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Account.AccountStatus getStatus() {
        return status;
    }

    public void setStatus(Account.AccountStatus status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ModifyAccountRequest{" +
                "accountType=" + accountType +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/rest/dto/AccountResponse.java ===
package com.pragma.accountmanagement.infrastructure.rest.dto;


import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.AccountType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private String accountId;
    private String clientId;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
    private Account.AccountStatus status;
    private String operationId;

    public AccountResponse() {
    }

    public AccountResponse(String accountId, String clientId, AccountType accountType, BigDecimal balance,
                           String currency, LocalDateTime createdAt, LocalDateTime lastUpdated,
                           Account.AccountStatus status, String operationId) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.operationId = operationId;
    }

    public static AccountResponse fromDomain(Account account) {
        return new AccountResponse(
            account.getAccountId(),
            account.getClientId(),
            account.getAccountType(),
            account.getBalance(),
            account.getCurrency(),
            account.getCreatedAt(),
            account.getLastUpdated(),
            account.getStatus(),
            account.getOperationId()
        );
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Account.AccountStatus getStatus() {
        return status;
    }

    public void setStatus(Account.AccountStatus status) {
        this.status = status;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "accountId='" + accountId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                ", status=" + status +
                ", operationId='" + operationId + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/application/command/CreateAccountCommand.java ===
package com.pragma.accountmanagement.application.command;

import com.pragma.accountmanagement.domain.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateAccountCommand {

    @NotBlank(message = "El ID del cliente no puede estar vacío")
    @Size(max = 50, message = "El ID del cliente no puede exceder 50 caracteres")
    private String clientId;

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    private AccountType accountType;

    @NotNull(message = "El saldo inicial no puede ser nulo")
    @DecimalMin(value = "0.0", message = "El saldo inicial no puede ser negativo")
    private BigDecimal initialBalance;

    @NotBlank(message = "La moneda no puede estar vacía")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres (ej. USD, EUR)")
    private String currency;

    @NotBlank(message = "El identificador de operación no puede estar vacío")
    private String operationId;

    public CreateAccountCommand() {
    }

    public CreateAccountCommand(String clientId, AccountType accountType, BigDecimal initialBalance,
                                String currency, String operationId) {
        this.clientId = clientId;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.operationId = operationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @Override
    public String toString() {
        return "CreateAccountCommand{" +
                "clientId='" + clientId + '\'' +
                ", accountType=" + accountType +
                ", initialBalance=" + initialBalance +
                ", currency='" + currency + '\'' +
                ", operationId='" + operationId + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/application/command/ModifyAccountCommand.java ===
package com.pragma.accountmanagement.application.command;

import com.pragma.accountmanagement.domain.model.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public final class ModifyAccountCommand {

    @NotBlank(message = "El ID de cuenta es obligatorio")
    private final String accountId;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private final AccountType accountType;

    @NotNull(message = "El saldo es obligatorio")
    @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
    private final BigDecimal balance;

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres")
    private final String currency;

    @NotBlank(message = "El ID de cliente es obligatorio")
    private final String clientId;

    private ModifyAccountCommand(Builder builder) {
        this.accountId = builder.accountId;
        this.accountType = builder.accountType;
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.clientId = builder.clientId;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getClientId() {
        return clientId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String accountId;
        private AccountType accountType;
        private BigDecimal balance;
        private String currency;
        private String clientId;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder accountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public ModifyAccountCommand build() {
            return new ModifyAccountCommand(this);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/application/command/DeleteAccountCommand.java ===
package com.pragma.accountmanagement.application.command;

import jakarta.validation.constraints.NotBlank;

public final class DeleteAccountCommand {

    @NotBlank(message = "El ID de cuenta es obligatorio para eliminación")
    private final String accountId;

    private final String reason;

    private DeleteAccountCommand(Builder builder) {
        this.accountId = builder.accountId;
        this.reason = builder.reason;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getReason() {
        return reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String accountId;
        private String reason;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public DeleteAccountCommand build() {
            return new DeleteAccountCommand(this);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/application/service/AccountService.java ===
package com.pragma.accountmanagement.application.service;

import com.pragma.accountmanagement.application.command.CreateAccountCommand;
import com.pragma.accountmanagement.application.command.DeleteAccountCommand;
import com.pragma.accountmanagement.application.command.ModifyAccountCommand;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort;
import com.pragma.accountmanagement.domain.port.out.AccountPersistencePort;
import com.pragma.accountmanagement.domain.port.out.NotificationPort;
import com.pragma.accountmanagement.domain.port.out.ValidationPort;
import com.pragma.accountmanagement.infrastructure.exception.AccountOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private static final String SERVICE_NAME = "AccountService";

    private final CreateAccountPort createAccountPort;
    private final ModifyAccountPort modifyAccountPort;
    private final DeleteAccountPort deleteAccountPort;
    private final AccountPersistencePort accountPersistencePort;
    private final NotificationPort notificationPort;
    private final ValidationPort validationPort;

    public AccountService(
            CreateAccountPort createAccountPort,
            ModifyAccountPort modifyAccountPort,
            DeleteAccountPort deleteAccountPort,
            AccountPersistencePort accountPersistencePort,
            NotificationPort notificationPort,
            ValidationPort validationPort) {
        this.createAccountPort = Objects.requireNonNull(createAccountPort, "CreateAccountPort no puede ser null");
        this.modifyAccountPort = Objects.requireNonNull(modifyAccountPort, "ModifyAccountPort no puede ser null");
        this.deleteAccountPort = Objects.requireNonNull(deleteAccountPort, "DeleteAccountPort no puede ser null");
        this.accountPersistencePort = Objects.requireNonNull(accountPersistencePort, "AccountPersistencePort no puede ser null");
        this.notificationPort = Objects.requireNonNull(notificationPort, "NotificationPort no puede ser null");
        this.validationPort = Objects.requireNonNull(validationPort, "ValidationPort no puede ser null");
        log.info("{} inicializado con todos los puertos inyectados", SERVICE_NAME);
    }

    public Mono<Account> createAccount(CreateAccountCommand command) {
        Objects.requireNonNull(command, "El comando de creación no puede ser null");
        log.info("Iniciando creación de cuenta para cliente: {} con operación: {}", 
                command.getClientId(), command.getOperationId());
        
        return validationPort.validateClientExists(command.getClientId())
                .then(validationPort.validateAccountType(command.getAccountType()))
                .then(Mono.defer(() -> {
                    OperationId operationId = OperationId.generate();
                    return createAccountPort.createAccount(command, operationId)
                            .flatMap(account -> {
                                log.info("Cuenta {} creada exitosamente para cliente: {}", 
                                        account.getAccountId(), account.getClientId());
                                return notificationPort.sendAccountCreatedNotification(account)
                                        .thenReturn(account);
                            })
                            .onErrorResume(error -> {
                                log.error("Error al crear cuenta para cliente {}: {}", 
                                        command.getClientId(), error.getMessage());
                                return Mono.error(new AccountOperationException(
                                        "Error al crear cuenta: " + error.getMessage(), error));
                            });
                }));
    }

    public Mono<Account> modifyAccount(ModifyAccountCommand command) {
        Objects.requireNonNull(command, "El comando de modificación no puede ser null");
        log.info("Iniciando modificación de cuenta: {} con operationId generado", command.getAccountId());
        
        return accountPersistencePort.findByAccountId(command.getAccountId())
                .switchIfEmpty(Mono.error(new AccountOperationException(
                        "Cuenta no encontrada: " + command.getAccountId())))
                .flatMap(existingAccount -> {
                    log.debug("Cuenta {} encontrada, procediendo con modificación", 
                            command.getAccountId());
                    return validationPort.validateClientExists(command.getClientId())
                            .then(validationPort.validateAccountType(command.getAccountType()))
                            .then(Mono.defer(() -> {
                                OperationId operationId = OperationId.generate();
                                return modifyAccountPort.modifyAccount(command, operationId)
                                        .flatMap(modifiedAccount -> {
                                            log.info("Cuenta {} modificada exitosamente", 
                                                    modifiedAccount.getAccountId());
                                            return notificationPort.sendAccountModifiedNotification(modifiedAccount)
                                                    .thenReturn(modifiedAccount);
                                        })
                                        .onErrorResume(error -> {
                                            log.error("Error al modificar cuenta {}: {}", 
                                                    command.getAccountId(), error.getMessage());
                                            return Mono.error(new AccountOperationException(
                                                    "Error al modificar cuenta: " + error.getMessage(), error));
                                        });
                            }));
                });
    }

    public Mono<Void> deleteAccount(DeleteAccountCommand command) {
        Objects.requireNonNull(command, "El comando de eliminación no puede ser null");
        log.info("Iniciando eliminación de cuenta: {} por razón: {}", 
                command.getAccountId(), command.getReason());
        
        return accountPersistencePort.findByAccountId(command.getAccountId())
                .switchIfEmpty(Mono.error(new AccountOperationException(
                        "Cuenta no encontrada para eliminación: " + command.getAccountId())))
                .flatMap(existingAccount -> {
                    log.debug("Cuenta {} encontrada, verificando dependencias antes de eliminación", 
                            command.getAccountId());
                    return validationPort.validateAccountCanBeDeleted(command.getAccountId())
                            .then(deleteAccountPort.deleteAccount(command)
                                    .doOnSuccess(unused -> {
                                        log.info("Cuenta {} eliminada exitosamente", command.getAccountId());
                                    })
                                    .flatMap(account -> notificationPort.sendAccountDeletedNotification(
                                            account.getAccountId(), command.getReason()))
                                    .then())
                                    .onErrorResume(error -> {
                                        log.error("Error al eliminar cuenta {}: {}", 
                                                command.getAccountId(), error.getMessage());
                                        return Mono.error(new AccountOperationException(
                                                "Error al eliminar cuenta: " + error.getMessage(), error));
                                    });
                });
    }

    public Mono<Account> findAccountById(String accountId) {
        Objects.requireNonNull(accountId, "El ID de cuenta no puede ser null");
        log.debug("Buscando cuenta por ID: {}", accountId);
        
        return accountPersistencePort.findByAccountId(accountId)
                .doOnSuccess(account -> {
                    if (account != null) {
                        log.debug("Cuenta {} encontrada", accountId);
                    } else {
                        log.warn("Cuenta {} no encontrada", accountId);
                    }
                });
    }

    public Mono<Account> findAccountByClientId(String clientId) {
        Objects.requireNonNull(clientId, "El ID de cliente no puede ser null");
        log.debug("Buscando cuentas para cliente: {}", clientId);
        
        return accountPersistencePort.findByClientId(clientId)
                .single()
                .doOnSuccess(account -> log.debug("Cuenta encontrada para cliente: {}", clientId))
                .onErrorResume(error -> Mono.error(new AccountOperationException(
                        "Error al buscar cuenta por cliente: " + error.getMessage(), error)));
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapter.java ===
package com.pragma.accountmanagement.infrastructure.adapter;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.out.AccountPersistencePort;
import com.pragma.accountmanagement.infrastructure.persistence.entity.AccountEntity;
import com.pragma.accountmanagement.infrastructure.persistence.mapper.AccountMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final DatabaseClient databaseClient;
    private final AccountMapper accountMapper;

    private static final String INSERT_SQL = """
        INSERT INTO accounts (account_id, client_id, account_type, balance, currency, 
                             created_at, last_updated, status, operation_id)
        VALUES (:accountId, :clientId, :accountType, :balance, :currency, 
                :createdAt, :lastUpdated, :status, :operationId)
        """;

    private static final String SELECT_BY_ID_SQL = """
        SELECT account_id, client_id, account_type, balance, currency, 
               created_at, last_updated, status, operation_id
        FROM accounts 
        WHERE account_id = :accountId
        """;

    private static final String SELECT_BY_CLIENT_ID_SQL = """
        SELECT account_id, client_id, account_type, balance, currency, 
               created_at, last_updated, status, operation_id
        FROM accounts 
        WHERE client_id = :clientId
        """;

    private static final String UPDATE_SQL = """
        UPDATE accounts 
        SET client_id = :clientId, account_type = :accountType, balance = :balance, 
            currency = :currency, last_updated = :lastUpdated, status = :status, 
            operation_id = :operationId
        WHERE account_id = :accountId
        """;

    private static final String DELETE_SQL = """
        DELETE FROM accounts WHERE account_id = :accountId
        """;

    private static final String UPDATE_BALANCE_SQL = """
        UPDATE accounts 
        SET balance = :balance, last_updated = :lastUpdated, operation_id = :operationId
        WHERE account_id = :accountId
        """;

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "saveAccountFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Account> saveAccount(Account account) {
        log.info("Persistiendo cuenta con ID: {} para cliente: {}", 
                account.getAccountId(), account.getClientId());
        
        AccountEntity entity = accountMapper.toEntity(account);
        LocalDateTime now = LocalDateTime.now();
        
        return databaseClient.sql(INSERT_SQL)
                .bind("accountId", entity.getAccountId())
                .bind("clientId", entity.getClientId())
                .bind("accountType", entity.getAccountType().name())
                .bind("balance", entity.getBalance())
                .bind("currency", entity.getCurrency())
                .bind("createdAt", now)
                .bind("lastUpdated", now)
                .bind("status", entity.getStatus().name())
                .bind("operationId", entity.getOperationId())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Cuenta {} persistida exitosamente", account.getAccountId());
                        return Mono.just(account);
                    }
                    log.error("Error al persistir cuenta {}", account.getAccountId());
                    return Mono.error(new RuntimeException("Error al persistir cuenta"));
                })
                .onErrorResume(e -> {
                    log.error("Error en persistencia de cuenta: {}", e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Account> saveAccountFallback(Account account, Throwable t) {
        log.error("Circuit breaker activado para saveAccount. Cuenta: {}, Error: {}", 
                account.getAccountId(), t.getMessage());
        return Mono.error(new RuntimeException("Servicio de persistencia temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "findByIdFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Optional<Account>> findById(String accountId) {
        log.debug("Buscando cuenta por ID: {}", accountId);
        
        return databaseClient.sql(SELECT_BY_ID_SQL)
                .bind("accountId", accountId)
                .map((row, metadata) -> accountMapper.toDomain(
                        row.get("account_id", String.class),
                        row.get("client_id", String.class),
                        row.get("account_type", String.class),
                        row.get("balance", BigDecimal.class),
                        row.get("currency", String.class),
                        row.get("created_at", LocalDateTime.class),
                        row.get("last_updated", LocalDateTime.class),
                        row.get("status", String.class),
                        row.get("operation_id", String.class)
                ))
                .first()
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .onErrorResume(e -> {
                    log.error("Error al buscar cuenta {}: {}", accountId, e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Optional<Account>> findByIdFallback(String accountId, Throwable t) {
        log.error("Circuit breaker activado para findById. Cuenta: {}, Error: {}", 
                accountId, t.getMessage());
        return Mono.error(new RuntimeException("Servicio de consulta temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "findByClientIdFallback")
    public Flux<Account> findByClientId(String clientId) {
        log.debug("Buscando cuentas para cliente: {}", clientId);
        
        return databaseClient.sql(SELECT_BY_CLIENT_ID_SQL)
                .bind("clientId", clientId)
                .map((row, metadata) -> accountMapper.toDomain(
                        row.get("account_id", String.class),
                        row.get("client_id", String.class),
                        row.get("account_type", String.class),
                        row.get("balance", BigDecimal.class),
                        row.get("currency", String.class),
                        row.get("created_at", LocalDateTime.class),
                        row.get("last_updated", LocalDateTime.class),
                        row.get("status", String.class),
                        row.get("operation_id", String.class)
                ))
                .all()
                .onErrorResume(e -> {
                    log.error("Error al buscar cuentas del cliente {}: {}", clientId, e.getMessage());
                    return Flux.error(e);
                });
    }

    private Flux<Account> findByClientIdFallback(String clientId, Throwable t) {
        log.error("Circuit breaker activado para findByClientId. Cliente: {}, Error: {}", 
                clientId, t.getMessage());
        return Flux.error(new RuntimeException("Servicio de consulta temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "updateAccountFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Account> updateAccount(Account account) {
        log.info("Actualizando cuenta con ID: {}", account.getAccountId());
        
        AccountEntity entity = accountMapper.toEntity(account);
        LocalDateTime now = LocalDateTime.now();
        
        return databaseClient.sql(UPDATE_SQL)
                .bind("accountId", entity.getAccountId())
                .bind("clientId", entity.getClientId())
                .bind("accountType", entity.getAccountType().name())
                .bind("balance", entity.getBalance())
                .bind("currency", entity.getCurrency())
                .bind("lastUpdated", now)
                .bind("status", entity.getStatus().name())
                .bind("operationId", entity.getOperationId())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Cuenta {} actualizada exitosamente", account.getAccountId());
                        return Mono.just(account);
                    }
                    log.warn("No se encontró cuenta {} para actualizar", account.getAccountId());
                    return Mono.empty();
                })
                .onErrorResume(e -> {
                    log.error("Error al actualizar cuenta {}: {}", account.getAccountId(), e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Account> updateAccountFallback(Account account, Throwable t) {
        log.error("Circuit breaker activado para updateAccount. Cuenta: {}, Error: {}", 
                account.getAccountId(), t.getMessage());
        return Mono.error(new RuntimeException("Servicio de actualización temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker", fallbackMethod = "deleteAccountFallback")
    @Retry(name = "accountPersistenceRetry")
    public Mono<Boolean> deleteAccount(String accountId) {
        log.info("Eliminando cuenta con ID: {}", accountId);
        
        return databaseClient.sql(DELETE_SQL)
                .bind("accountId", accountId)
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Cuenta {} eliminada exitosamente", accountId);
                        return Mono.just(true);
                    }
                    log.warn("No se encontró cuenta {} para eliminar", accountId);
                    return Mono.just(false);
                })
                .onErrorResume(e -> {
                    log.error("Error al eliminar cuenta {}: {}", accountId, e.getMessage());
                    return Mono.error(e);
                });
    }

    private Mono<Boolean> deleteAccountFallback(String accountId, Throwable t) {
        log.error("Circuit breaker activado para deleteAccount. Cuenta: {}, Error: {}", 
                accountId, t.getMessage());
        return Mono.error(new RuntimeException("Servicio de eliminación temporalmente no disponible", t));
    }

    @Override
    @CircuitBreaker(name = "accountPersistenceCircuitBreaker")
    public Mono<Account> updateBalance(String accountId, BigDecimal newBalance, OperationId operationId) {
        log.info("Actualizando balance de cuenta {} a {}", accountId, newBalance);
        
        LocalDateTime now = LocalDateTime.now();
        
        return databaseClient.sql(UPDATE_BALANCE_SQL)
                .bind("accountId", accountId)
                .bind("balance", newBalance)
                .bind("lastUpdated", now)
                .bind("operationId", operationId.getValue())
                .fetch()
                .rowsUpdated()
                .flatMap(rows -> {
                    if (rows > 0) {
                        log.info("Balance de cuenta {} actualizado exitosamente", accountId);
                        return findById(accountId)
                                .flatMap(opt -> opt.map(Mono::just)
                                        .orElseGet(() -> Mono.error(new RuntimeException("Cuenta no encontrada después de actualizar"))));
                    }
                    log.warn("No se encontró cuenta {} para actualizar balance", accountId);
                    return Mono.empty();
                })
                .onErrorResume(e -> {
                    log.error("Error al actualizar balance de cuenta {}: {}", accountId, e.getMessage());
                    return Mono.error(e);
                });
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/adapter/NotificationAdapter.java ===
package com.pragma.accountmanagement.infrastructure.adapter;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.port.out.NotificationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationAdapter implements NotificationPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topics.account-events:account-events}")
    private String accountEventsTopic;

    @Value("${app.kafka.topics.notifications:account-notifications}")
    private String notificationsTopic;

    private static final String EVENT_TYPE_ACCOUNT_CREATED = "ACCOUNT_CREATED";
    private static final String EVENT_TYPE_ACCOUNT_UPDATED = "ACCOUNT_UPDATED";
    private static final String EVENT_TYPE_ACCOUNT_DELETED = "ACCOUNT_DELETED";
    private static final String EVENT_TYPE_ACCOUNT_BALANCE_UPDATED = "ACCOUNT_BALANCE_UPDATED";

    @Override
    public Mono<Boolean> notifyAccountCreated(Account account) {
        log.info("Enviando notificación de cuenta creada: {}", account.getAccountId());
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_CREATED);
        event.put("accountId", account.getAccountId());
        event.put("clientId", account.getClientId());
        event.put("accountType", account.getAccountType().name());
        event.put("balance", account.getBalance().toPlainString());
        event.put("currency", account.getCurrency());
        event.put("status", account.getStatus().name());
        
        return publishEvent(accountEventsTopic, account.getAccountId(), event)
                .subscribeOn(Schedulers.boundedElastic())
                .then(publishEvent(notificationsTopic, account.getClientId(), event))
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar cuenta creada: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> notifyAccountUpdated(Account account) {
        log.info("Enviando notificación de cuenta actualizada: {}", account.getAccountId());
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_UPDATED);
        event.put("accountId", account.getAccountId());
        event.put("clientId", account.getClientId());
        event.put("accountType", account.getAccountType().name());
        event.put("balance", account.getBalance().toPlainString());
        event.put("currency", account.getCurrency());
        event.put("status", account.getStatus().name());
        event.put("operationId", account.getOperationId());
        
        return publishEvent(accountEventsTopic, account.getAccountId(), event)
                .subscribeOn(Schedulers.boundedElastic())
                .then(publishEvent(notificationsTopic, account.getClientId(), event))
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar cuenta actualizada: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> notifyAccountDeleted(String accountId, String clientId) {
        log.info("Enviando notificación de cuenta eliminada: {}", accountId);
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_DELETED);
        event.put("accountId", accountId);
        event.put("clientId", clientId);
        
        return publishEvent(accountEventsTopic, accountId, event)
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar cuenta eliminada: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    @Override
    public Mono<Boolean> notifyBalanceUpdate(String accountId, String clientId, String newBalance, String operationId) {
        log.info("Enviando notificación de actualización de balance para cuenta: {}", accountId);
        
        Map<String, Object> event = buildBaseEvent(EVENT_TYPE_ACCOUNT_BALANCE_UPDATED);
        event.put("accountId", accountId);
        event.put("clientId", clientId);
        event.put("newBalance", newBalance);
        event.put("operationId", operationId);
        
        return publishEvent(accountEventsTopic, accountId, event)
                .subscribeOn(Schedulers.boundedElastic())
                .thenReturn(true)
                .onErrorResume(e -> {
                    log.error("Error al notificar actualización de balance: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    private Map<String, Object> buildBaseEvent(String eventType) {
        Map<String, Object> event = new HashMap<>();
        event.put("eventType", eventType);
        event.put("timestamp", Instant.now().toString());
        event.put("source", "account-management-service");
        return event;
    }

    private Mono<Void> publishEvent(String topic, String key, Map<String, Object> event) {
        String eventJson = convertToJson(event);
        
        CompletableFuture<SendResult<String, String>> future = 
                kafkaTemplate.send(topic, key, eventJson);
        
        return Mono.fromFuture(future)
                .doOnSuccess(result -> log.debug("Evento publicado en {} con offset: {}", 
                        topic, result.getRecordMetadata().offset()))
                .doOnError(error -> log.error("Error al publicar evento en {}: {}", 
                        topic, error.getMessage()))
                .then();
    }

    private String convertToJson(Map<String, Object> map) {
        StringBuilder json = new StringBuilder("{");
        int count = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (count > 0) {
                json.append(",");
            }
            json.append("\"").append(entry.getKey()).append("\":");
            Object value = entry.getValue();
            if (value instanceof String) {
                json.append("\"").append(escapeJson((String) value)).append("\"");
            } else {
                json.append(value);
            }
            count++;
        }
        json.append("}");
        return json.toString();
    }

    private String escapeJson(String value) {
        return value.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/adapter/ValidationAdapter.java ===
package com.pragma.accountmanagement.infrastructure.adapter;



import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Builder;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.port.out.ValidationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ValidationAdapter implements ValidationPort {

    private final WebClient validationWebClient;
    
    @Value("${app.validation.external.timeout-ms:3000}")
    private int validationTimeoutMs;
    
    @Value("${app.validation.external.enabled:true}")
    private boolean externalValidationEnabled;
    
    private static final Set<String> SUPPORTED_CURRENCIES = Set.of("USD", "EUR", "GBP", "JPY", "COP", "MXN");
    private static final Set<String> VALID_ACCOUNT_TYPES = Set.of("SAVINGS", "CHECKING", "INVESTMENT");
    private static final BigDecimal MIN_INITIAL_BALANCE = new BigDecimal("100.00");
    private static final BigDecimal MAX_INITIAL_BALANCE = new BigDecimal("1000000.00");
    private static final Pattern CLIENT_ID_PATTERN = Pattern.compile("^[A-Z0-9]{6,20}$");
    private static final Pattern ACCOUNT_ID_PATTERN = Pattern.compile("^ACC-[0-9]{10}$");

    public ValidationAdapter(WebClient.Builder webClientBuilder) {
        this.validationWebClient = webClientBuilder
                .baseUrl("http://validation-service:8080")
                .build();
    }

    @Override
    public Mono<Boolean> validateAccountData(Account account) {
        log.debug("Validando datos de cuenta: {}", account.getAccountId());
        
        if (account == null) {
            log.warn("Cuenta nula recibida para validación");
            return Mono.just(false);
        }
        
        if (!validateAccountId(account.getAccountId())) {
            log.warn("AccountId inválido: {}", account.getAccountId());
            return Mono.just(false);
        }
        
        if (!validateClientId(account.getClientId())) {
            log.warn("ClientId inválido: {}", account.getClientId());
            return Mono.just(false);
        }
        
        if (!validateAccountType(account.getAccountType())) {
            log.warn("AccountType inválido: {}", account.getAccountType());
            return Mono.just(false);
        }
        
        if (!validateBalance(account.getBalance())) {
            log.warn("Balance inválido: {}", account.getBalance());
            return Mono.just(false);
        }
        
        if (!validateCurrency(account.getCurrency())) {
            log.warn("Currency inválida: {}", account.getCurrency());
            return Mono.just(false);
        }
        
        if (externalValidationEnabled) {
            return performExternalValidation(account)
                    .subscribeOn(Schedulers.boundedElastic())
                    .timeout(Duration.ofMillis(validationTimeoutMs))
                    .onErrorResume(e -> {
                        log.error("Error en validación externa: {}. Usando validación local.", e.getMessage());
                        return Mono.just(true);
                    });
        }
        
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> validateClientEligibility(String clientId) {
        log.debug("Validando elegibilidad del cliente: {}", clientId);
        
        if (clientId == null || clientId.isBlank()) {
            return Mono.just(false);
        }
        
        if (!validateClientId(clientId)) {
            return Mono.just(false);
        }
        
        if (externalValidationEnabled) {
            return validationWebClient.get()
                    .uri("/api/v1/clients/{clientId}/eligibility", clientId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        Object eligible = response.get("eligible");
                        return eligible instanceof Boolean && (Boolean) eligible;
                    })
                    .timeout(Duration.ofMillis(validationTimeoutMs))
                    .onErrorResume(e -> {
                        log.error("Error al validar elegibilidad del cliente {}: {}", clientId, e.getMessage());
                        return Mono.just(true);
                    });
        }
        
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> validateAccountStatusTransition(String currentStatus, String newStatus) {
        log.debug("Validando transición de estado de {} a {}", currentStatus, newStatus);
        
        if (currentStatus == null || newStatus == null) {
            log.warn("Estado nulo recibido en transición");
            return Mono.just(false);
        }
        
        boolean validTransition = switch (currentStatus) {
            case "ACTIVE" -> Set.of("ACTIVE", "SUSPENDED", "CLOSED").contains(newStatus);
            case "SUSPENDED" -> Set.of("ACTIVE", "CLOSED").contains(newStatus);
            case "CLOSED" -> "CLOSED".equals(newStatus);
            default -> false;
        };
        
        if (!validTransition) {
            log.warn("Transición inválida de {} a {}", currentStatus, newStatus);
            return Mono.just(false);
        }
        
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> validateOperationIdempotency(String operationId, String operationType) {
        log.debug("Validando idempotencia de operación: {} tipo: {}", operationId, operationType);
        
        if (operationId == null || operationId.isBlank()) {
            return Mono.just(false);
        }
        
        if (operationType == null || operationType.isBlank()) {
            return Mono.just(false);
        }
        
        if (externalValidationEnabled) {
            return validationWebClient.get()
                    .uri("/api/v1/operations/{operationId}/exists", operationId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        Object exists = response.get("exists");
                        return !(exists instanceof Boolean && (Boolean) exists);
                    })
                    .timeout(Duration.ofMillis(validationTimeoutMs))
                    .onErrorResume(e -> {
                        log.error("Error al validar idempotencia {}: {}", operationId, e.getMessage());
                        return Mono.just(true);
                    });
        }
        
        return Mono.just(true);
    }

    private boolean validateAccountId(String accountId) {
        return accountId != null && ACCOUNT_ID_PATTERN.matcher(accountId).matches();
    }

    private boolean validateClientId(String clientId) {
        return clientId != null && CLIENT_ID_PATTERN.matcher(clientId).matches();
    }

    private boolean validateAccountType(Object accountType) {
        if (accountType == null) {
            return false;
        }
        String typeName = accountType.toString();
        return VALID_ACCOUNT_TYPES.contains(typeName);
    }

    private boolean validateBalance(BigDecimal balance) {
        if (balance == null) {
            return false;
        }
        return balance.compareTo(BigDecimal.ZERO) >= 0 && 
               balance.compareTo(new BigDecimal("999999999.99")) <= 0;
    }

    private boolean validateCurrency(String currency) {
        return currency != null && SUPPORTED_CURRENCIES.contains(currency.toUpperCase());
    }

    private Mono<Boolean> performExternalValidation(Account account) {
        return validationWebClient.post()
                .uri("/api/v1/accounts/validate")
                .bodyValue(Map.of(
                        "accountId", account.getAccountId(),
                        "clientId", account.getClientId(),
                        "accountType", account.getAccountType().name(),
                        "balance", account.getBalance().toPlainString(),
                        "currency", account.getCurrency()
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    Object valid = response.get("valid");
                    return valid instanceof Boolean && (Boolean) valid;
                });
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.accountmanagement.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        return RetryRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/config/KafkaConfig.java ===
package com.pragma.accountmanagement.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id:account-management-group}")
    private String groupId;

    @Value("${spring.kafka.producer.acks:all}")
    private String acks;

    @Value("${spring.kafka.producer.retries:3}")
    private int retries;

    @Bean
    public NewTopic notificationEventsTopic() {
        return TopicBuilder.name("account-notification-events")
                .partitions(6)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic accountOperationsTopic() {
        return TopicBuilder.name("account-operations")
                .partitions(6)
                .replicas(1)
                .build();
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.pragma.accountmanagement.*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.accountmanagement.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountOperationException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleAccountOperationException(AccountOperationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", ex.getStatus().value());
        body.put("error", ex.getStatus().getReasonPhrase());
        body.put("message", ex.getMessage());
        
        if (ex.getOperationId() != null) {
            body.put("operationId", ex.getOperationId().getValue());
        }
        
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(body));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleValidationException(WebExchangeBindException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Error");
        
        String validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        body.put("message", validationErrors);
        
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occurred. Please contact support.");
        
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/exception/AccountOperationException.java ===
package com.pragma.accountmanagement.infrastructure.exception;

import java.util.Map;

public class AccountOperationException extends RuntimeException {

    private final ErrorType errorType;
    private final String accountId;
    private final Map<String, Object> metadata;

    public enum ErrorType {
        ACCOUNT_NOT_FOUND("La cuenta especificada no existe en el sistema"),
        DUPLICATE_ACCOUNT("Ya existe una cuenta con los mismos identificadores"),
        INVALID_ACCOUNT_STATE("La cuenta se encuentra en un estado inválido para la operación"),
        INSUFFICIENT_BALANCE("La cuenta no tiene saldo suficiente para la operación"),
        PERSISTENCE_ERROR("Error al persistir los datos de la cuenta"),
        VALIDATION_ERROR("Error de validación en los datos de la cuenta"),
        CONCURRENT_MODIFICATION("La cuenta fue modificada concurrentemente por otra operación"),
        OPERATION_CANCELLED("La operación fue cancelada debido a una falla"),
        UNKNOWN_ERROR("Error desconocido durante la operación de cuenta");

        private final String defaultMessage;

        ErrorType(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }

    public AccountOperationException(ErrorType errorType, String accountId) {
        super(buildMessage(errorType, accountId, null));
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, String customMessage) {
        super(customMessage != null ? customMessage : buildMessage(errorType, accountId, null));
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, Throwable cause) {
        super(buildMessage(errorType, accountId, null), cause);
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, Map<String, Object> metadata) {
        super(buildMessage(errorType, accountId, metadata));
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = metadata != null ? metadata : Map.of();
    }

    public AccountOperationException(ErrorType errorType, String accountId, String customMessage, Throwable cause) {
        super(customMessage != null ? customMessage : buildMessage(errorType, accountId, null), cause);
        this.errorType = errorType;
        this.accountId = accountId;
        this.metadata = Map.of();
    }

    private static String buildMessage(ErrorType errorType, String accountId, Map<String, Object> metadata) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(errorType.name()).append("] ");
        sb.append(errorType.getDefaultMessage());
        if (accountId != null && !accountId.isBlank()) {
            sb.append(" | AccountId: ").append(accountId);
        }
        if (metadata != null && !metadata.isEmpty()) {
            sb.append(" | Metadata: ").append(metadata);
        }
        return sb.toString();
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getAccountId() {
        return accountId;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public boolean isRetryable() {
        return errorType == ErrorType.PERSISTENCE_ERROR ||
               errorType == ErrorType.OPERATION_CANCELLED ||
               errorType == ErrorType.CONCURRENT_MODIFICATION;
    }

    public boolean isFatal() {
        return errorType == ErrorType.ACCOUNT_NOT_FOUND ||
               errorType == ErrorType.DUPLICATE_ACCOUNT ||
               errorType == ErrorType.INVALID_ACCOUNT_STATE;
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/persistence/entity/AccountEntity.java ===
package com.pragma.accountmanagement.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("accounts")
public class AccountEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("account_id")
    private String accountId;

    @Column("client_id")
    private String clientId;

    @Column("account_type")
    private String accountType;

    @Column("balance")
    private BigDecimal balance;

    @Column("currency")
    private String currency;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("last_updated")
    private LocalDateTime lastUpdated;

    @Column("status")
    private String status;

    @Column("operation_id")
    private String operationId;

    public AccountEntity() {
    }

    public AccountEntity(Long id, String accountId, String clientId, String accountType,
                        BigDecimal balance, String currency, LocalDateTime createdAt,
                        LocalDateTime lastUpdated, String status, String operationId) {
        this.id = id;
        this.accountId = accountId;
        this.clientId = clientId;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.operationId = operationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/persistence/mapper/AccountMapper.java ===
package com.pragma.accountmanagement.infrastructure.persistence.mapper;



import com.pragma.accountmanagement.domain.model.AccountStatus;
import com.pragma.accountmanagement.domain.model.AccountType;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.infrastructure.persistence.entity.AccountEntity;

import java.time.LocalDateTime;

public class AccountMapper {

    private AccountMapper() {
    }

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        Account.AccountType accountTypeEnum;
        try {
            accountTypeEnum = Account.AccountType.valueOf(entity.getAccountType());
        } catch (IllegalArgumentException e) {
            accountTypeEnum = Account.AccountType.UNKNOWN;
        }

        Account.AccountStatus statusEnum;
        try {
            statusEnum = Account.AccountStatus.valueOf(entity.getStatus());
        } catch (IllegalArgumentException e) {
            statusEnum = Account.AccountStatus.UNKNOWN;
        }

        return Account.builder()
                .accountId(entity.getAccountId())
                .clientId(entity.getClientId())
                .accountType(accountTypeEnum)
                .balance(entity.getBalance())
                .currency(entity.getCurrency())
                .createdAt(entity.getCreatedAt())
                .lastUpdated(entity.getLastUpdated())
                .status(statusEnum)
                .operationId(entity.getOperationId())
                .build();
    }

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }

        AccountEntity entity = new AccountEntity();
        entity.setAccountId(account.getAccountId());
        entity.setClientId(account.getClientId());
        entity.setAccountType(account.getAccountType() != null ? account.getAccountType().name() : null);
        entity.setBalance(account.getBalance());
        entity.setCurrency(account.getCurrency());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setLastUpdated(account.getLastUpdated());
        entity.setStatus(account.getStatus() != null ? account.getStatus().name() : null);
        entity.setOperationId(account.getOperationId());

        return entity;
    }

    public static AccountEntity toEntityWithId(Account account, Long databaseId) {
        AccountEntity entity = toEntity(account);
        entity.setId(databaseId);
        return entity;
    }

    public static void updateEntityFromDomain(AccountEntity entity, Account account) {
        if (entity == null || account == null) {
            return;
        }

        if (account.getClientId() != null) {
            entity.setClientId(account.getClientId());
        }
        if (account.getAccountType() != null) {
            entity.setAccountType(account.getAccountType().name());
        }
        if (account.getBalance() != null) {
            entity.setBalance(account.getBalance());
        }
        if (account.getCurrency() != null) {
            entity.setCurrency(account.getCurrency());
        }
        if (account.getStatus() != null) {
            entity.setStatus(account.getStatus().name());
        }
        if (account.getOperationId() != null) {
            entity.setOperationId(account.getOperationId());
        }

        entity.setLastUpdated(LocalDateTime.now());
    }

    public static boolean isValidAccountType(String accountType) {
        if (accountType == null || accountType.isBlank()) {
            return false;
        }
        try {
            Account.AccountType.valueOf(accountType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isValidStatus(String status) {
        if (status == null || status.isBlank()) {
            return false;
        }
        try {
            Account.AccountStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java ===
package com.pragma.accountmanagement.infrastructure.rest;




import com.pragma.accountmanagement.domain.port.in.DeleteAccountCommand;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountCommand;
import com.pragma.accountmanagement.domain.port.in.CreateAccountCommand;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort;
import com.pragma.accountmanagement.infrastructure.rest.dto.AccountResponse;
import com.pragma.accountmanagement.infrastructure.rest.dto.CreateAccountRequest;
import com.pragma.accountmanagement.infrastructure.rest.dto.ModifyAccountRequest;
import com.pragma.accountmanagement.infrastructure.persistence.mapper.AccountMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST para la gestión de cuentas.
 * Expone los endpoints para crear, modificar, consultar y eliminar cuentas.
 * Utiliza WebFlux para manejo reactivo de solicitudes.
 */
@RestController
@RequestMapping("/api/v1/accounts")
@Validated
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    private final CreateAccountPort createAccountPort;
    private final ModifyAccountPort modifyAccountPort;
    private final DeleteAccountPort deleteAccountPort;
    private final AccountMapper accountMapper;

    public AccountController(
            CreateAccountPort createAccountPort,
            ModifyAccountPort modifyAccountPort,
            DeleteAccountPort deleteAccountPort,
            AccountMapper accountMapper) {
        this.createAccountPort = createAccountPort;
        this.modifyAccountPort = modifyAccountPort;
        this.deleteAccountPort = deleteAccountPort;
        this.accountMapper = accountMapper;
    }

    /**
     * Crea una nueva cuenta en el sistema.
     *
     * @param request Datos de la cuenta a crear
     * @return Respuesta con la cuenta creada
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<AccountResponse>> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
        log.info("Recibida solicitud de creación de cuenta para cliente: {}", request.getClientId());
        
        OperationId operationId = OperationId.generate();
        CreateAccountPort.CreateAccountCommand command = accountMapper.toCommand(request);
        
        return createAccountPort.createAccount(command, operationId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta creada exitosamente con ID: {} para operación: {}", 
                            account.getAccountId(), operationId.getValue());
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(response);
                });
    }

    /**
     * Consulta una cuenta por su identificador.
     *
     * @param accountId Identificador de la cuenta
     * @return Respuesta con los datos de la cuenta
     */
    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountResponse>> getAccount(
            @PathVariable @NotBlank String accountId) {
        log.info("Consultando cuenta con ID: {}", accountId);
        
        return modifyAccountPort.findByAccountId(accountId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta encontrada: {}", accountId);
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Lista todas las cuentas de un cliente específico.
     *
     * @param clientId Identificador del cliente
     * @return Lista de cuentas del cliente
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AccountResponse> getAccountsByClient(
            @RequestParam @NotBlank String clientId) {
        log.info("Listando cuentas para el cliente: {}", clientId);
        
        return modifyAccountPort.findByClientId(clientId)
                .map(accountMapper::toResponse)
                .doOnComplete(() -> log.info("Listado de cuentas completado para cliente: {}", clientId));
    }

    /**
     * Lista todas las cuentas activas del sistema.
     *
     * @return Lista de cuentas activas
     */
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AccountResponse> getActiveAccounts() {
        log.info("Listando cuentas activas");
        
        return modifyAccountPort.findActiveAccounts()
                .map(accountMapper::toResponse)
                .doOnComplete(() -> log.info("Listado de cuentas activas completado"));
    }

    /**
     * Modifica los datos de una cuenta existente.
     *
     * @param accountId Identificador de la cuenta
     * @param request   Datos a modificar
     * @return Respuesta con la cuenta modificada
     */
    @PutMapping(value = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountResponse>> modifyAccount(
            @PathVariable @NotBlank String accountId,
            @Valid @RequestBody ModifyAccountRequest request) {
        log.info("Solicitud de modificación de cuenta: {}", accountId);
        
        OperationId operationId = OperationId.generate();
        ModifyAccountPort.ModifyAccountCommand command = accountMapper.toCommand(accountId, request);
        
        return modifyAccountPort.modifyAccount(accountId, command, operationId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta modificada exitosamente: {} para operación: {}", 
                            accountId, operationId.getValue());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza parcialmente una cuenta (PATCH).
     *
     * @param accountId Identificador de la cuenta
     * @param request   Datos parciales a actualizar
     * @return Respuesta con la cuenta actualizada
     */
    @PatchMapping(value = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AccountResponse>> patchAccount(
            @PathVariable @NotBlank String accountId,
            @Valid @RequestBody ModifyAccountRequest request) {
        log.info("Solicitud de actualización parcial de cuenta: {}", accountId);
        
        OperationId operationId = OperationId.generate();
        ModifyAccountPort.ModifyAccountCommand command = accountMapper.toCommand(accountId, request);
        
        return modifyAccountPort.patchAccount(accountId, command, operationId)
                .map(account -> {
                    AccountResponse response = accountMapper.toResponse(account);
                    log.info("Cuenta actualizada parcialmente: {} para operación: {}", 
                            accountId, operationId.getValue());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Elimina una cuenta del sistema.
     *
     * @param accountId Identificador de la cuenta a eliminar
     * @return Respuesta sin contenido si la eliminación fue exitosa
     */
    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteAccount(
            @PathVariable @NotBlank String accountId) {
        log.info("Solicitud de eliminación de cuenta: {}", accountId);
        
        OperationId operationId = OperationId.generate();
        DeleteAccountPort.DeleteAccountCommand command = new DeleteAccountPort.DeleteAccountCommand(accountId);
        
        return deleteAccountPort.deleteAccount(command, operationId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(e -> {
                    log.error("Error al eliminar cuenta: {}", accountId, e);
                    return Mono.just(ResponseEntity.notFound().build());
                });
    }

    /**
     * Obtiene el saldo de una cuenta específica.
     *
     * @param accountId Identificador de la cuenta
     * @return Saldo de la cuenta
     */
    @GetMapping(value = "/{accountId}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BalanceResponse>> getAccountBalance(
            @PathVariable @NotBlank String accountId) {
        log.info("Consultando saldo de cuenta: {}", accountId);
        
        return modifyAccountPort.findByAccountId(accountId)
                .map(account -> {
                    BalanceResponse response = new BalanceResponse(
                            account.getAccountId(),
                            account.getBalance(),
                            account.getCurrency(),
                            account.getLastUpdated()
                    );
                    log.info("Saldo consultado para cuenta: {} = {}", accountId, account.getBalance());
                    return ResponseEntity.ok(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * DTO para respuestas de saldo.
     */
    public static class BalanceResponse {
        private final String accountId;
        private final BigDecimal balance;
        private final String currency;
        private final LocalDateTime lastUpdated;

        public BalanceResponse(String accountId, BigDecimal balance, String currency, LocalDateTime lastUpdated) {
            this.accountId = accountId;
            this.balance = balance;
            this.currency = currency;
            this.lastUpdated = lastUpdated;
        }

        public String getAccountId() {
            return accountId;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public String getCurrency() {
            return currency;
        }

        public LocalDateTime getLastUpdated() {
            return lastUpdated;
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java ===
package com.pragma.accountmanagement.application.service;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort.CreateAccountCommand;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort;
import com.pragma.accountmanagement.domain.port.in.DeleteAccountPort.DeleteAccountCommand;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort.ModifyAccountCommand;
import com.pragma.accountmanagement.domain.port.out.NotificationPort;
import com.pragma.accountmanagement.domain.port.out.ValidationPort;
import com.pragma.accountmanagement.infrastructure.exception.AccountOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AccountService")
class AccountServiceTest {

    @Mock
    private CreateAccountPort createAccountPort;

    @Mock
    private ModifyAccountPort modifyAccountPort;

    @Mock
    private DeleteAccountPort deleteAccountPort;

    @Mock
    private NotificationPort notificationPort;

    @Mock
    private ValidationPort validationPort;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private CreateAccountCommand createCommand;
    private OperationId operationId;

    @BeforeEach
    void setUp() {
        operationId = OperationId.generate();
        testAccount = Account.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .status(AccountStatus.ACTIVE)
                .operationId(operationId.getValue())
                .build();

        createCommand = new CreateAccountCommand(
                "CLI-001",
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                "USD"
        );
    }

    @Test
    @DisplayName("Debe crear una cuenta exitosamente cuando la validación pasa")
    void createAccount_Success_WhenValidationPasses() {
        when(validationPort.validateClient(anyString())).thenReturn(Mono.just(true));
        when(createAccountPort.createAccount(any(CreateAccountCommand.class), any(OperationId.class)))
                .thenReturn(Mono.just(testAccount));
        when(notificationPort.sendNotification(any(Account.class)))
                .thenReturn(Mono.empty());

        StepVerifier.create(accountService.createAccount(createCommand))
                .expectNext(testAccount)
                .verifyComplete();

        verify(validationPort).validateClient("CLI-001");
        verify(createAccountPort).createAccount(createCommand, any(OperationId.class));
        verify(notificationPort).sendNotification(testAccount);
    }

    @Test
    @DisplayName("Debe fallar al crear cuenta cuando la validación del cliente falla")
    void createAccount_Fails_WhenClientValidationFails() {
        when(validationPort.validateClient(anyString())).thenReturn(Mono.just(false));

        StepVerifier.create(accountService.createAccount(createCommand))
                .expectError(AccountOperationException.class)
                .verify();

        verify(validationPort).validateClient("CLI-001");
        verify(createAccountPort, never()).createAccount(any(), any());
        verify(notificationPort, never()).sendNotification(any());
    }

    @Test
    @DisplayName("Debe modificar una cuenta exitosamente")
    void modifyAccount_Success() {
        ModifyAccountCommand modifyCommand = new ModifyAccountCommand(
                "ACC-001",
                new BigDecimal("2000.00"),
                AccountStatus.ACTIVE
        );

        when(validationPort.validateAccount(anyString())).thenReturn(Mono.just(true));
        when(modifyAccountPort.modifyAccount(any(ModifyAccountCommand.class), any(OperationId.class)))
                .thenReturn(Mono.just(testAccount));
        when(notificationPort.sendNotification(any(Account.class)))
                .thenReturn(Mono.empty());

        StepVerifier.create(accountService.modifyAccount(modifyCommand))
                .expectNext(testAccount)
                .verifyComplete();

        verify(validationPort).validateAccount("ACC-001");
        verify(modifyAccountPort).modifyAccount(modifyCommand, any(OperationId.class));
    }

    @Test
    @DisplayName("Debe eliminar una cuenta exitosamente")
    void deleteAccount_Success() {
        DeleteAccountCommand deleteCommand = new DeleteAccountCommand("ACC-001");

        when(validationPort.validateAccount(anyString())).thenReturn(Mono.just(true));
        when(deleteAccountPort.deleteAccount(any(DeleteAccountCommand.class), any(OperationId.class)))
                .thenReturn(Mono.empty());
        when(notificationPort.sendNotification(any(Account.class)))
                .thenReturn(Mono.empty());

        StepVerifier.create(accountService.deleteAccount(deleteCommand))
                .verifyComplete();

        verify(validationPort).validateAccount("ACC-001");
        verify(deleteAccountPort).deleteAccount(deleteCommand, any(OperationId.class));
    }

    @Test
    @DisplayName("Debe propagar errores del puerto de creación")
    void createAccount_PropagatesErrorFromPort() {
        when(validationPort.validateClient(anyString())).thenReturn(Mono.just(true));
        when(createAccountPort.createAccount(any(), any(OperationId.class)))
                .thenReturn(Mono.error(new AccountOperationException("Error de base de datos")));

        StepVerifier.create(accountService.createAccount(createCommand))
                .expectError(AccountOperationException.class)
                .verify();
    }
}

// === ARCHIVO: src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java ===
package com.pragma.accountmanagement.infrastructure.adapter;

import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import com.pragma.accountmanagement.domain.model.OperationId;
import com.pragma.accountmanagement.domain.port.in.CreateAccountPort.CreateAccountCommand;
import com.pragma.accountmanagement.domain.port.in.ModifyAccountPort.ModifyAccountCommand;
import com.pragma.accountmanagement.infrastructure.persistence.entity.AccountEntity;
import com.pragma.accountmanagement.infrastructure.persistence.mapper.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas de integración para AccountPersistenceAdapter")
class AccountPersistenceAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountPersistenceAdapter accountPersistenceAdapter;

    private AccountEntity testEntity;
    private Account testAccount;
    private OperationId operationId;

    @BeforeEach
    void setUp() {
        operationId = OperationId.generate();
        LocalDateTime now = LocalDateTime.now();

        testEntity = AccountEntity.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS.name())
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(now)
                .lastUpdated(now)
                .status(AccountStatus.ACTIVE.name())
                .operationId(operationId.getValue())
                .build();

        testAccount = Account.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(now)
                .lastUpdated(now)
                .status(AccountStatus.ACTIVE)
                .operationId(operationId.getValue())
                .build();
    }

    @Test
    @DisplayName("Debe guardar una cuenta exitosamente en la base de datos")
    void save_Success() {
        when(accountMapper.toEntity(any(Account.class))).thenReturn(testEntity);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(Mono.just(testEntity));
        when(accountMapper.toDomain(any(AccountEntity.class))).thenReturn(testAccount);

        CreateAccountCommand command = new CreateAccountCommand(
                "CLI-001",
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                "USD"
        );

        StepVerifier.create(accountPersistenceAdapter.save(command, operationId))
                .assertNext(account -> {
                    assertThat(account.getAccountId()).isEqualTo("ACC-001");
                    assertThat(account.getClientId()).isEqualTo("CLI-001");
                    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal("1000.00"));
                })
                .verifyComplete();

        verify(accountMapper).toEntity(any(Account.class));
        verify(accountRepository).save(any(AccountEntity.class));
        verify(accountMapper).toDomain(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Debe actualizar una cuenta existente")
    void update_Success() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Mono.just(testEntity));
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(Mono.just(testEntity));
        when(accountMapper.toDomain(any(AccountEntity.class))).thenReturn(testAccount);

        ModifyAccountCommand command = new ModifyAccountCommand(
                "ACC-001",
                new BigDecimal("2000.00"),
                AccountStatus.ACTIVE
        );

        StepVerifier.create(accountPersistenceAdapter.update(command, operationId))
                .assertNext(account -> {
                    assertThat(account.getAccountId()).isEqualTo("ACC-001");
                    assertThat(account.getBalance()).isEqualByComparingTo(new BigDecimal("1000.00"));
                })
                .verifyComplete();

        verify(accountRepository).findByAccountId("ACC-001");
        verify(accountRepository).save(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Debe eliminar una cuenta por su ID")
    void deleteByAccountId_Success() {
        when(accountRepository.deleteByAccountId(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(accountPersistenceAdapter.deleteByAccountId("ACC-001"))
                .verifyComplete();

        verify(accountRepository).deleteByAccountId("ACC-001");
    }

    @Test
    @DisplayName("Debe encontrar una cuenta por su ID")
    void findByAccountId_Success() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Mono.just(testEntity));
        when(accountMapper.toDomain(any(AccountEntity.class))).thenReturn(testAccount);

        StepVerifier.create(accountPersistenceAdapter.findByAccountId("ACC-001"))
                .assertNext(account -> {
                    assertThat(account).isNotNull();
                    assertThat(account.getAccountId()).isEqualTo("ACC-001");
                })
                .verifyComplete();

        verify(accountRepository).findByAccountId("ACC-001");
    }

    @Test
    @DisplayName("Debe retornar vacio cuando la cuenta no existe")
    void findByAccountId_ReturnsEmpty_WhenNotFound() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(accountPersistenceAdapter.findByAccountId("NON-EXISTENT"))
                .verifyComplete();

        verify(accountRepository).findByAccountId("NON-EXISTENT");
    }

    @Test
    @DisplayName("Debe verificar si existe una cuenta por su ID")
    void existsByAccountId_ReturnsTrue_WhenExists() {
        when(accountRepository.existsByAccountId(anyString())).thenReturn(Mono.just(true));

        StepVerifier.create(accountPersistenceAdapter.existsByAccountId("ACC-001"))
                .expectNext(true)
                .verifyComplete();

        verify(accountRepository).existsByAccountId("ACC-001");
    }

    @Test
    @DisplayName("Debe manejar errores de base de datos al guardar")
    void save_HandlesDatabaseError() {
        when(accountMapper.toEntity(any(Account.class))).thenReturn(testEntity);
        when(accountRepository.save(any(AccountEntity.class)))
                .thenReturn(Mono.error(new RuntimeException("Database connection failed")));

        CreateAccountCommand command = new CreateAccountCommand(
                "CLI-001",
                AccountType.SAVINGS,
                new BigDecimal("1000.00"),
                "USD"
        );

        StepVerifier.create(accountPersistenceAdapter.save(command, operationId))
                .expectError(RuntimeException.class)
                .verify();
    }
}

// === ARCHIVO: src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java ===
package com.pragma.accountmanagement.infrastructure.rest;

import com.pragma.accountmanagement.application.service.AccountService;
import com.pragma.accountmanagement.domain.model.Account;
import com.pragma.accountmanagement.domain.model.Account.AccountStatus;
import com.pragma.accountmanagement.domain.model.Account.AccountType;
import com.pragma.accountmanagement.infrastructure.rest.dto.AccountResponse;
import com.pragma.accountmanagement.infrastructure.rest.dto.CreateAccountRequest;
import com.pragma.accountmanagement.infrastructure.rest.dto.ModifyAccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@WebFluxTest(AccountController.class)
@DisplayName("Pruebas de integración para AccountController")
class AccountControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AccountService accountService;

    @Test
    @DisplayName("POST /api/cuentas debe crear una cuenta exitosamente")
    void createAccount_Returns201_WhenSuccessful() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setClientId("CLI-001");
        request.setAccountType(AccountType.SAVINGS.name());
        request.setBalance(new BigDecimal("1000.00"));
        request.setCurrency("USD");

        Account createdAccount = createTestAccount();
        when(accountService.createAccount(any())).thenReturn(Mono.just(createdAccount));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AccountResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getAccountId().equals("ACC-001");
                    assert response.getClientId().equals("CLI-001");
                });
    }

    @Test
    @DisplayName("POST /api/cuentas debe retornar 400 cuando la validacion falla")
    void createAccount_Returns400_WhenValidationFails() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setClientId("");
        request.setAccountType(AccountType.SAVINGS.name());
        request.setBalance(new BigDecimal("-100.00"));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("PUT /api/cuentas/{accountId} debe modificar una cuenta exitosamente")
    void modifyAccount_Returns200_WhenSuccessful() {
        String accountId = "ACC-001";
        ModifyAccountRequest request = new ModifyAccountRequest();
        request.setBalance(new BigDecimal("2000.00"));
        request.setStatus(AccountStatus.ACTIVE.name());

        Account modifiedAccount = createTestAccount();
        modifiedAccount = Account.builder()
                .accountId(accountId)
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("2000.00"))
                .currency("USD")
                .createdAt(modifiedAccount.getCreatedAt())
                .lastUpdated(LocalDateTime.now())
                .status(AccountStatus.ACTIVE)
                .operationId(modifiedAccount.getOperationId())
                .build();

        when(accountService.modifyAccount(any())).thenReturn(Mono.just(modifiedAccount));

        webTestClient.put()
                .uri("/api/cuentas/{accountId}", accountId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AccountResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getBalance().compareTo(new BigDecimal("2000.00")) == 0;
                });
    }

    @Test
    @DisplayName("DELETE /api/cuentas/{accountId} debe eliminar una cuenta exitosamente")
    void deleteAccount_Returns204_WhenSuccessful() {
        String accountId = "ACC-001";
        when(accountService.deleteAccount(any())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/cuentas/{accountId}", accountId)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @DisplayName("GET /api/cuentas/{accountId} debe retornar una cuenta existente")
    void getAccount_Returns200_WhenExists() {
        String accountId = "ACC-001";
        Account account = createTestAccount();
        when(accountService.getAccount(accountId)).thenReturn(Mono.just(account));

        webTestClient.get()
                .uri("/api/cuentas/{accountId}", accountId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AccountResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getAccountId().equals(accountId);
                });
    }

    @Test
    @DisplayName("GET /api/cuentas/{accountId} debe retornar 404 cuando no existe")
    void getAccount_Returns404_WhenNotExists() {
        String accountId = "NON-EXISTENT";
        when(accountService.getAccount(accountId)).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/api/cuentas/{accountId}", accountId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("POST /api/cuentas debe manejar errores internos del servidor")
    void createAccount_Returns500_WhenInternalError() {
        CreateAccountRequest request = new CreateAccountRequest();
        request.setClientId("CLI-001");
        request.setAccountType(AccountType.SAVINGS.name());
        request.setBalance(new BigDecimal("1000.00"));
        request.setCurrency("USD");

        when(accountService.createAccount(any()))
                .thenReturn(Mono.error(new RuntimeException("Internal server error")));

        webTestClient.post()
                .uri("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(request))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    private Account createTestAccount() {
        return Account.builder()
                .accountId("ACC-001")
                .clientId("CLI-001")
                .accountType(AccountType.SAVINGS)
                .balance(new BigDecimal("1000.00"))
                .currency("USD")
                .createdAt(LocalDateTime.now())
                .lastUpdated(LocalDateTime.now())
                .status(AccountStatus.ACTIVE)
                .operationId("OP-001")
                .build();
    }
}
```
