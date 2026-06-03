# Guía breve para exposición del proyecto Biblioteca

## ✅ Idea general
Proyecto de biblioteca con control de usuarios, contenidos y préstamos. El sistema simula una base de datos usando archivos y búsquedas secuenciales, porque el curso no permite estructuras más complejas.

## 🧩 Qué hace cada parte del código (explicación simple)

**`MainBiblioteca`**
- Muestra el menú y valida la opción.
- No guarda ni busca datos, solo pide y manda.
- Es el “control remoto” del sistema.

**`Biblioteca`**
- Orquesta toda la lógica.
- Decide qué se guarda y qué se consulta.
- Contiene las validaciones del flujo.

**`Generar` y subclases (`GenerarUsuarios`, `GenerarContenidos`, `GenerarPrestamos`)**
- Escribe objetos en archivos **uno por uno**.
- No guarda listas completas en memoria.
- Se usa cuando el usuario registra algo.

**`Registro`**
- Lee archivos cuando se necesita consultar.
- Devuelve `Vector` y busca de forma secuencial.
- Simula relaciones por ID (como una base de datos simple).

**Modelos (`Cliente`, `Contenido`, `Prestamo`, etc.)**
- Son los datos del sistema.
- Cada tipo tiene su propia lógica (por ejemplo, `Estudiante` vs `Profesor`).

## 🎭 Roles y permisos

**Admin**
- Registrar clientes
- Registrar contenidos
- Registrar préstamos
- Buscar en todo
- Ver información general

**Cliente**
- Ver contenidos
- Buscar contenidos
- Ver su credencial

> El cliente no puede crear ni prestar, solo consultar.

## 🧭 Flujo general

1. Inicio
   - Carga `sistemaUsuarios.dat`.
   - Si está vacío, se crea el primer admin.
   - Si hay usuarios, se solicita identificación.
2. Login
   - Admin → menú completo.
   - Cliente → menú limitado.
3. Operaciones
   - Menú principal → delega a `Biblioteca`.
   - `Biblioteca` orquesta acciones.
   - Escritura → `Generar`.
   - Lectura/consulta → `Registro`.

## 🧱 Estructura conceptual

**Main**
- Solo menú y lectura de opción.
- No guarda ni busca datos.

**Biblioteca**
- Lógica principal del sistema.
- Control de permisos.
- Decide qué guardar o leer.

**Generar**
- Solo escritura.
- Guarda un objeto por operación (sin arrays en memoria).

**Registro**
- Lee archivos cuando se necesita consultar.
- Usa `Vector` y búsqueda secuencial.

## 🔎 Búsquedas (sin Map)

1. Usuario escribe cualquier dato.
2. Se intenta convertir a número:
   - Si se convierte → buscar por ID.
   - Si falla → buscar por texto (nombre, título, autor).
3. Si no se encuentra:
   - Lanza excepción propia (`ContenidoException` o `AlumnoException`).

## 📚 Submenús

**Menú Admin**
1. Información general
2. Registrar cliente
3. Registrar contenido
4. Préstamos
5. Buscar
6. Salir

**Menú Cliente**
1. Información general
2. Buscar contenidos
3. Salir

**Submenú Buscar (Admin)**
1. Buscar clientes
2. Buscar contenidos
3. Volver

## 💬 Frases útiles para exponer

- “Separé escritura y lectura: `Generar` guarda, `Registro` consulta.”
- “Simulo foreign keys: `Prestamo` guarda IDs y `Registro` resuelve relaciones.”
- “La búsqueda es secuencial porque el curso no permite estructuras más complejas.”
- “El menú es dinámico según permisos del usuario.”

## ✅ Ventajas del diseño

- Código organizado y defendible.
- Separación clara de responsabilidades.
- Flujo realista (login + permisos).
- Consistente con el estilo del autor.

## 🧠 Preguntas típicas y cómo responder

**1) “¿Por qué no usas `ArrayList` o `Map`?”**
- “El curso limita las estructuras. Usamos `Vector` y búsqueda secuencial por requisito.”

**2) “¿Por qué separaste `Generar` y `Registro`?”**
- “Para separar escritura y lectura. Eso organiza el flujo y facilita el mantenimiento.”

**3) “¿Por qué no guardas todo en memoria?”**
- “Para no cargar todo el sistema. Solo leo cuando necesito consultar.”

**4) “¿Cómo simulas una base de datos?”**
- “Los préstamos guardan IDs, y `Registro` resuelve esas relaciones al buscar.”

**5) “¿Qué pasa si el usuario escribe mal?”**
- “Se valida con `while` y `try/catch`, así el programa no se cierra.”

**6) “¿Qué diferencia hay entre admin y cliente?”**
- “El admin puede registrar y prestar; el cliente solo consulta.”

**7) “¿Dónde está la lógica principal?”**
- “En `Biblioteca`; el `Main` solo muestra el menú.”

## 📎 Apéndice: ¿Qué es AppendableObjectOutputStream?

En el proyecto usamos archivos binarios con serialización Java (ObjectOutputStream/ObjectInputStream) para guardar objetos individualmente.

Problema que resuelve
- Cuando se crea un `ObjectOutputStream` sobre un archivo ya existente, Java escribe un encabezado (stream header) al inicio. Si abrimos y cerramos el stream repetidamente para "añadir" objetos, cada creación vuelve a escribir ese encabezado. Al leer luego con `ObjectInputStream` esto puede producir errores o un formato inválido.

Qué es `AppendableObjectOutputStream`
- Es una pequeña subclase de `ObjectOutputStream` cuyo propósito es omitir la reescritura del encabezado cuando abrimos un archivo en modo append. La clase sobrescribe el método que escribe el encabezado (writeStreamHeader) y no escribe nada en esa llamada, de modo que los objetos se encadenan correctamente sin insertar un segundo encabezado.

Por qué lo usamos
- Queremos escribir objetos "uno por uno" (append) en vez de reescribir un array completo en cada operación. Esto ahorra memoria y evita tener que leer todo el archivo para volver a escribirlo.

Cómo se usa en el código
- Al guardar un objeto `o` en un archivo `f` hacemos:
   1. Abrir `FileOutputStream(f, true)` en modo append.
   2. Si el archivo está vacío, crear un `new ObjectOutputStream(out)` normal — éste escribe el encabezado inicial.
   3. Si el archivo ya existe y tiene contenido, crear `new AppendableObjectOutputStream(out)` — así se evita escribir un nuevo encabezado.
   4. Escribir el objeto y cerrar el stream.

Limitaciones y riesgos
- Esta técnica funciona bien para añadir objetos a un archivo serializado, pero tiene algunas advertencias:
   - No es un sustituto de una base de datos; la serialización de objetos no gestiona concurrencia ni índices eficientes.
   - Si los tipos serializados cambian (versiones de clase con different serialVersionUID), la lectura puede fallar.
   - Si el archivo se corrompe (por ejemplo, por una interrupción en medio de una escritura), puede hacerse ilegible; conviene controles de integridad (backups o archivos tmp y renombrado atómico).
   - Append con ObjectOutputStream no permite eliminar ni actualizar objetos intermedios sin reescribir el archivo completo.

Alternativas
- Si el proyecto escala, evaluar:
   - Una base de datos ligera (SQLite),
   - Ficheros con índices separados (un índice en texto que apunte a offsets binarios),
   - Uso de formatos como JSON/NDJSON si se prefiere legibilidad y edición externa.

Pequeño ejemplo (conceptual)
```
FileOutputStream fos = new FileOutputStream(file, true);
ObjectOutputStream oos;
if (file.length() == 0) oos = new ObjectOutputStream(fos);
else oos = new AppendableObjectOutputStream(fos); // omite el header
oos.writeObject(obj);
oos.close();
```

Dónde encontrarlo en el código
- La clase/implementación aparece en `Generar.java` (método que guarda objetos). Busca la clase interna `AppendableObjectOutputStream` o comentario equivalente.

Consejo para la exposición
- Explica el problema del "header" y di que la solución fue crear un stream que no vuelve a escribirlo cuando se hace append. Es un detalle técnico pequeño pero importante: demuestra comprensión sobre serialización y formatos binarios.
