# Changelog
Todas las modificaciones notables de este proyecto se documentarán en este archivo.

El formato se inspira en **Keep a Changelog** y el proyecto sigue **Semantic Versioning**.

## [Unreleased]
### Planeado
- Tachado visual del texto cuando la tarea esté completada.
- Ordenar: no completadas arriba, completadas abajo.
- Editar título de una tarea al tocarla.
- Divider entre ítems y mejoras de UI.
- (Opcional) Migrar persistencia a Room.
- (Opcional) Tests instrumentados básicos.

---

## [1.0.0] - 2025-09-04
### Added
- **Persistencia local** con `SharedPreferences` (JSON): las tareas y su estado se guardan y recuperan.
- **Restauración de estado** al abrir la app.
- **Checkbox** por ítem para marcar como completada.
- **Botón borrar** por ítem.

### Changed
- `item_tarea.xml` ahora incluye `CheckBox` + `TextView` + botón de borrar.
- `TaskAdapter` usa **callbacks** hacia la `MainActivity`.
- `MainActivity` implementa `TaskAdapter.TaskCallbacks` y centraliza guardado/carga.

### Fixed
- Se evita el “rebote” del listener del `CheckBox` al reciclar vistas.

### Chores
- Commit sugerido: **part 4: checkbox y borrar en ítems + persistencia con SharedPreferences**
- Tag sugerido: `v1.0.0` o `part-4`

---

## [0.3.0] - 2025-09-04
### Added
- **FAB** conectado a `AlertDialog` para **agregar nuevas tareas**.
- Desplazamiento automático al último ítem tras agregar.

### Chores
- Commit sugerido: **part 3: FAB con diálogo para agregar nuevas tareas (sin persistencia)**
- Tag sugerido: `part-3`

---

## [0.2.0] - 2025-09-04
### Added
- Modelo `Task` (title, done).
- Layout de ítem `item_tarea.xml`.
- `TaskAdapter` básico para `RecyclerView`.
- Datos de prueba (3 tareas) conectados a la lista.

### Changed
- `MainActivity` ahora inicializa y conecta el `TaskAdapter`.

### Chores
- Commit: **part 2: modelo Task, item_tarea y Adapter con datos de prueba**
- Tag sugerido: `part-2`

---

## [0.1.0] - 2025-09-04
### Added
- Estructura base del proyecto Android (Empty Activity).
- `activity_main.xml` con `RecyclerView` y `FloatingActionButton`.
- `MainActivity` con `LinearLayoutManager`.
- Nombre visible de la app: **Lista de Tareas**.

### Chores
- Commit: **part 1: UI base con RecyclerView y FAB (sin adapter)**
- Tag sugerido: `part-1`

### Security
- `.gitignore` básico para excluir `build/`, `.idea/`, etc.
