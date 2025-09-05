# Changelog
Todas las modificaciones notables de este proyecto se documentarán en este archivo.

El formato se inspira en **Keep a Changelog** y el proyecto sigue **Semantic Versioning**.

## [Unreleased]
### Planeado
- Diálogo para agregar nueva tarea desde el FAB.
- Persistencia local con `SharedPreferences`.
- Marcar tareas como completadas y eliminar.
- Mejora visual de ítems (checkbox/acciones).

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
