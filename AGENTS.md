# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Diseño de arquitectura de un sistema de gestión de cuentas**.

| | |
|---|---|
| Tema | Diseño de arquitectura |
| Nivel | master-l3 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con CQRS y manejo reactivo de eventos |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-data-r2dbc 3.5.6
- io.r2dbc:r2dbc-postgresql 1.0.5.RELEASE
- org.springframework.kafka:spring-kafka 3.2.3
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.springdoc:springdoc-openapi-starter-webflux-ui 2.6.0
- org.springframework.boot:spring-boot-starter-validation n/a
- org.projectlombok:lombok 1.18.34
- org.springframework.boot:spring-boot-starter-test n/a
- org.junit.jupiter:junit-jupiter-api 5.11.0
- org.mockito:mockito-core 5.12.0
- org.testcontainers:postgresql 1.20.1
- org.testcontainers:kafka 1.20.1

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Identificación de atributos de calidad**: Lista de atributos de calidad priorizados para el sistema de gestión de cuentas.
- **Fase 2 — Diseño de la arquitectura**: Diagrama de relaciones del sistema de gestión de cuentas y descripción de las decisiones de diseño tomadas.
- **Fase 3 — Evaluación de trade-offs**: Descripción de al menos dos trade-offs significativos en el diseño de la arquitectura y justificación de las decisiones tomadas.
- **Fase 4 — Comunicación de la arquitectura**: Presentación de la arquitectura diseñada, adaptada a diferentes audiencias.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (50)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/accountmanagement/AccountManagementApplication.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/domain/port/in/CreateAccountPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/domain/port/in/ModifyAccountPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/domain/port/in/DeleteAccountPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/domain/port/out/AccountPersistencePort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/domain/port/out/NotificationPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/domain/port/out/ValidationPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/NotificationAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/NotificationAdapter.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/ValidationAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/ValidationAdapter.java` — `reactor.core.scheduler`
      El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/exception/GlobalExceptionHandler.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `DeleteAccountCommand.getClientId`
      Se invoca `getClientId` sobre `DeleteAccountCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `DeleteAccountCommand.getOperationId`
      Se invoca `getOperationId` sobre `DeleteAccountCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `ValidationPort.validateAccountType`
      Se invoca `validateAccountType` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `DeleteAccountCommand.getAccountType`
      Se invoca `getAccountType` sobre `DeleteAccountCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `NotificationPort.sendAccountCreatedNotification`
      Se invoca `sendAccountCreatedNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `AccountPersistencePort.findByAccountId`
      Se invoca `findByAccountId` sobre `AccountPersistencePort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `NotificationPort.sendAccountModifiedNotification`
      Se invoca `sendAccountModifiedNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `ValidationPort.validateAccountCanBeDeleted`
      Se invoca `validateAccountCanBeDeleted` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java` — `NotificationPort.sendAccountDeletedNotification`
      Se invoca `sendAccountDeletedNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/exception/AccountOperationException.java` — `ErrorType.name`
      Se invoca `name` sobre `ErrorType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/exception/AccountOperationException.java` — `ErrorType.getDefaultMessage`
      Se invoca `getDefaultMessage` sobre `ErrorType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountRequest.getClientId`
      Se invoca `getClientId` sobre `ModifyAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `AccountMapper.toCommand`
      Se invoca `toCommand` sobre `AccountMapper`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `AccountMapper.toResponse`
      Se invoca `toResponse` sobre `AccountMapper`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.findByAccountId`
      Se invoca `findByAccountId` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.findByClientId`
      Se invoca `findByClientId` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.findActiveAccounts`
      Se invoca `findActiveAccounts` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java` — `ModifyAccountPort.patchAccount`
      Se invoca `patchAccount` sobre `ModifyAccountPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `ValidationPort.validateClient`
      Se invoca `validateClient` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `NotificationPort.sendNotification`
      Se invoca `sendNotification` sobre `NotificationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java` — `ValidationPort.validateAccount`
      Se invoca `validateAccount` sobre `ValidationPort`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.save`
      Se invoca `save` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.update`
      Se invoca `update` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.deleteByAccountId`
      Se invoca `deleteByAccountId` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.findByAccountId`
      Se invoca `findByAccountId` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java` — `AccountPersistenceAdapter.existsByAccountId`
      Se invoca `existsByAccountId` sobre `AccountPersistenceAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `CreateAccountRequest.setBalance`
      Se invoca `setBalance` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `CreateAccountRequest.setStatus`
      Se invoca `setStatus` sobre `CreateAccountRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java` — `AccountService.getAccount`
      Se invoca `getAccount` sobre `AccountService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`
      io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (31)

- `pom.xml`
- `src/main/java/com/pragma/accountmanagement/AccountManagementApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/accountmanagement/domain/model/Account.java`
- `src/main/java/com/pragma/accountmanagement/domain/model/OperationId.java`
- `src/main/java/com/pragma/accountmanagement/domain/port/in/CreateAccountPort.java`
- `src/main/java/com/pragma/accountmanagement/domain/port/in/ModifyAccountPort.java`
- `src/main/java/com/pragma/accountmanagement/domain/port/in/DeleteAccountPort.java`
- `src/main/java/com/pragma/accountmanagement/domain/port/out/AccountPersistencePort.java`
- `src/main/java/com/pragma/accountmanagement/domain/port/out/NotificationPort.java`
- `src/main/java/com/pragma/accountmanagement/domain/port/out/ValidationPort.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/dto/CreateAccountRequest.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/dto/ModifyAccountRequest.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/dto/AccountResponse.java`
- `src/main/java/com/pragma/accountmanagement/application/command/CreateAccountCommand.java`
- `src/main/java/com/pragma/accountmanagement/application/command/ModifyAccountCommand.java`
- `src/main/java/com/pragma/accountmanagement/application/command/DeleteAccountCommand.java`
- `src/main/java/com/pragma/accountmanagement/application/service/AccountService.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapter.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/NotificationAdapter.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter/ValidationAdapter.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/config/KafkaConfig.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/exception/AccountOperationException.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/persistence/entity/AccountEntity.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/persistence/mapper/AccountMapper.java`
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest/AccountController.java`
- `src/test/java/com/pragma/accountmanagement/application/service/AccountServiceTest.java`
- `src/test/java/com/pragma/accountmanagement/infrastructure/adapter/AccountPersistenceAdapterTest.java`
- `src/test/java/com/pragma/accountmanagement/infrastructure/rest/AccountControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/accountmanagement`
- `src/main/java/com/pragma/accountmanagement/domain`
- `src/main/java/com/pragma/accountmanagement/domain/model`
- `src/main/java/com/pragma/accountmanagement/domain/port`
- `src/main/java/com/pragma/accountmanagement/application`
- `src/main/java/com/pragma/accountmanagement/application/command`
- `src/main/java/com/pragma/accountmanagement/application/query`
- `src/main/java/com/pragma/accountmanagement/application/service`
- `src/main/java/com/pragma/accountmanagement/infrastructure`
- `src/main/java/com/pragma/accountmanagement/infrastructure/adapter`
- `src/main/java/com/pragma/accountmanagement/infrastructure/config`
- `src/main/java/com/pragma/accountmanagement/infrastructure/exception`
- `src/main/java/com/pragma/accountmanagement/infrastructure/persistence`
- `src/main/java/com/pragma/accountmanagement/infrastructure/rest`
- `src/main/resources`
- `src/test/java/com/pragma/accountmanagement`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con CQRS y manejo reactivo de eventos**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Master
- Brecha que el reto ataca: Ha trabajado identificando los atributos de calidad más importantes para los requerimientos de una cuenta / proyecto y ha diseñado arquitecturas de software que favorezcan estos atributos de calidad
- Mision: Candidato con experiencia como Master en Backend

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
