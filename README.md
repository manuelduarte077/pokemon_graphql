# PokedexKMP

Una aplicación Pokédex multiplataforma desarrollada con Kotlin Multiplatform.

## Descripción

PokedexKMP es una aplicación que permite explorar información sobre Pokémon utilizando la [PokéAPI](https://pokeapi.co/). La aplicación está construida con Kotlin Multiplatform (KMP) y utiliza Jetpack Compose para la interfaz de usuario, lo que permite compartir la mayor parte del código entre Android, iOS y Desktop.

## Características

- Lista de Pokémon con paginación
- Detalles de cada Pokémon (nombre, número, altura, peso, tipos)
- Imágenes oficiales de los Pokémon
- Almacenamiento en caché local para funcionamiento sin conexión
- Soporte para múltiples plataformas (Android, iOS, Desktop)

## Tecnologías utilizadas

- **Kotlin Multiplatform**: Para compartir código entre plataformas
- **Jetpack Compose**: Para la interfaz de usuario multiplataforma
- **Ktor**: Cliente HTTP para comunicación con la API
- **SQLDelight**: Para persistencia de datos local
- **Koin**: Para inyección de dependencias
- **Coil**: Para carga y caché de imágenes
- **Kotlin Serialization**: Para serialización/deserialización de JSON
- **Kotlin Coroutines**: Para programación asíncrona

## Arquitectura

El proyecto sigue una arquitectura MVVM (Model-View-ViewModel) con los siguientes componentes:

- **Model**: Clases de datos que representan la información de los Pokémon
- **View**: Pantallas y componentes UI construidos con Jetpack Compose
- **ViewModel**: Maneja la lógica de negocio y el estado de la UI
- **Repository**: Gestiona el acceso a datos, tanto de la API como de la base de datos local
- **Network**: Servicios para comunicación con la API
- **Database**: Persistencia local con SQLDelight

## Estructura del proyecto

- `/composeApp`: Código compartido entre todas las plataformas
  - `commonMain`: Código común para todas las plataformas
    - `data`: Mappers y conversiones de datos
    - `database`: Configuración de SQLDelight y DAOs
    - `di`: Configuración de inyección de dependencias con Koin
    - `model`: Clases de datos
    - `navigation`: Navegación entre pantallas
    - `network`: Servicios de API y modelos de red
    - `repository`: Repositorios para acceso a datos
    - `ui`: Pantallas y componentes de UI
  - `androidMain`: Código específico para Android
  - `iosMain`: Código específico para iOS
  - `desktopMain`: Código específico para Desktop

- `/iosApp`: Punto de entrada para la aplicación iOS

## Cómo ejecutar el proyecto

### Android
```bash
./gradlew :composeApp:assembleDebug
```

### iOS
Abrir el proyecto en Xcode y ejecutar la aplicación:
```bash
open iosApp/iosApp.xcworkspace
```

### Desktop
```bash
./gradlew :composeApp:run
```

## Recursos

- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [PokéAPI](https://pokeapi.co/)