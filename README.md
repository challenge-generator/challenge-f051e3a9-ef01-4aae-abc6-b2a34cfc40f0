# Diseño de arquitectura de un sistema de gestión de cuentas

El sistema de gestión de cuentas debe manejar la creación, modificación y eliminación de cuentas de clientes, asegurando la consistencia de los datos y la disponibilidad del servicio. Los actores involucrados son el 'originador de cuentas', el'motor de validación de datos', el 'almacenamiento de cuentas' y el'sistema de notificación'. El sistema debe procesar un mínimo de 1 500 solicitudes por segundo durante la hora pico y mantener un tiempo de respuesta promedio de 200 ms. La consistencia de los datos se garantiza mediante la idempotencia de las operaciones de creación y modificación de cuentas, utilizando un identificador único por operación. El modo de falla a considerar es la pérdida de conexión con el almacenamiento de cuentas durante una operación.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Diseño de arquitectura |
| **Nivel** | master-l3 |
| **Tipo** | mixed |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Identificación de atributos de calidad

**Objetivo:** Identificar los atributos de calidad más importantes para el sistema de gestión de cuentas.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analiza los requerimientos del sistema y determina los atributos de calidad que deben ser priorizados.
- Considera la consistencia, disponibilidad, seguridad y escalabilidad del sistema.

**Entregable:** Lista de atributos de calidad priorizados para el sistema de gestión de cuentas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los impactos de cada atributo en el funcionamiento del sistema.
- Evalúa cómo cada atributo afecta a los diferentes actores del sistema.

</details>

### Fase 2: Diseño de la arquitectura

**Objetivo:** Diseñar una arquitectura que favorezca los atributos de calidad identificados.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crea un diagrama de relaciones que muestre los componentes del sistema y cómo interactúan entre sí.
- Describe las decisiones de diseño tomadas para favorecer los atributos de calidad identificados.
- Considera las posibles restricciones y ambigüedades en el sistema.

**Entregable:** Diagrama de relaciones del sistema de gestión de cuentas y descripción de las decisiones de diseño tomadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la separación de preocupaciones y el encapsulamiento de la lógica de negocio.
- Evalúa las posibles trade-offs entre los diferentes atributos de calidad.

</details>

### Fase 3: Evaluación de trade-offs

**Objetivo:** Evaluar y justificar las decisiones de diseño tomadas en términos de trade-offs.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identifica al menos dos trade-offs significativos en el diseño de la arquitectura.
- Justifica las decisiones tomadas en términos de los atributos de calidad priorizados.
- Considera las consecuencias de cada decisión en el funcionamiento del sistema.

**Entregable:** Descripción de al menos dos trade-offs significativos en el diseño de la arquitectura y justificación de las decisiones tomadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el impacto de cada trade-off en la consistencia, disponibilidad, seguridad y escalabilidad del sistema.
- Evalúa las posibles consecuencias de cada decisión en el funcionamiento del sistema y en los diferentes actores.

</details>

### Fase 4: Comunicación de la arquitectura

**Objetivo:** Comunicar la arquitectura diseñada a diferentes audiencias.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Crea una presentación que describa la arquitectura diseñada, destacando los componentes clave y las decisiones de diseño tomadas.
- Considera al menos dos audiencias diferentes: técnicos y no técnicos.
- Asegura que la presentación sea clara y concisa, y que cada audiencia pueda tomar decisiones informadas sin pedir aclaraciones adicionales.

**Entregable:** Presentación de la arquitectura diseñada, adaptada a diferentes audiencias.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de diagramas y ejemplos concretos para ilustrar los conceptos.
- Evalúa la efectividad de la presentación en términos de claridad y concisión.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un atributo de calidad y por qué es importante en el diseño de la arquitectura?
- **paraQueSirve**: ¿Para qué sirve el diagrama de relaciones en el diseño de la arquitectura?
- **comoSeUsa**: ¿Cómo se usa el encapsulamiento de la lógica de negocio en el diseño de la arquitectura?
- **erroresComunes**: ¿Cuáles son los errores comunes al diseñar una arquitectura que favorezca los atributos de calidad?
- **queDecisionesImplica**: ¿Qué decisiones implica la evaluación de trade-offs en el diseño de la arquitectura?

## Criterios de Evaluacion

- Identificación de atributos de calidad priorizados para el sistema de gestión de cuentas.
- Diseño de una arquitectura que favorezca los atributos de calidad identificados.
- Evaluación y justificación de trade-offs significativos en el diseño de la arquitectura.
- Comunicación efectiva de la arquitectura diseñada a diferentes audiencias.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
