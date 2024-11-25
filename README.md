# 📚 LiterAlura: Solución al Reto de Alura Latam

![Version](https://img.shields.io/badge/version-1.0-blue.svg)

Este proyecto, desarrollado para el desafío de Alura Latam, es una aplicación Java que gestiona información sobre libros y autores. Consume datos de la API de Gutendex , se realiza la logica para su procesamiento y almacenamiento.

## 🎥 Demostración del Proyecto

Video demostrativo que muestra las principales funcionalidades de LiterAlura , ¡Dale click a la imagen para ver el video!

[![Demo de LiterAlura](https://img.youtube.com/vi/YiPKEbI5UVQ/maxresdefault.jpg)](https://youtu.be/YiPKEbI5UVQ)
En este video, podrás ver:

- 🔍 Cómo buscar libros usando la API de Gutendex
- 📊 Visualización de estadísticas de libros y autores
- 🌍 Filtrado de libros por idioma
- 👥 Búsqueda avanzada de autores
- ...otras funcionalidades

## 🌟 Características Principales

- 🔍 Consumo de API de Gutendex para obtener información de libros
- 🔄 Serialización y deserialización de JSON con Jackson
- 💾 Persistencia de datos utilizando JPA y Hibernate
- 🐘 Base de datos PostgreSQL para almacenamiento
- 🐳 Containerización con Docker para facilitar el despliegue
- 🔎 Implementación de búsquedas y filtros avanzados

## 🚀 Uso y Funcionalidades

1. **Búsqueda de Libros (API)**:
   - Consume la API de Gutendex para buscar libros por título
   - Guarda el primer libro encontrado en la base de datos local
   - Evita duplicados mediante validación de títulos existentes

2. **Listado de Libros y Autores (DB)**:
   - Muestra todos los libros almacenados, ordenados por número de descargas
   - Lista autores únicos en la base de datos

3. **Filtrado de Autores por Año**:
   - Encuentra autores vivos en un año específico ingresado por el usuario
   - Maneja casos de autores sin fecha de nacimiento o muerte

4. **Filtrado de Libros por Idioma**:
   - Utiliza el enum `CategoryLanguage` para clasificar y filtrar libros
   - Ofrece opciones de idioma predefinidas al usuario

5. **Estadísticas de Descargas**:
   - Genera estadísticas detalladas usando `DoubleSummaryStatistics`
   - Muestra total de libros, total de descargas, promedio, mínimo y máximo

6. **Top 10 Libros**:
   - Lista los 10 libros más populares según el número de descargas
   - Muestra detalles como título, autor y número de descargas

7. **Búsqueda Avanzada**:
   - Busca autores por nombre o apellido, mostrando coincidencias parciales
   - Permite buscar todos los libros de un autor específico

## 🛠 Tecnologías Utilizadas

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- Jackson
- JPA/Hibernate

## 🏗 Arquitectura

- Anotaciones Spring (@Component, @Service, @Autowired)
- Inyección de dependencias por constructor
- Servicios separados para lógica de negocio

## ⚠ Manejo de Errores

- Validación de entradas de usuario
- Excepciones personalizadas
- Prevención de duplicados
- Uso de streams y lambdas para procesamiento eficiente

## 📥 Instalación y Configuración

1. **Clonar el Repositorio:**

   - Clonar el repositorio
2. **Configurar Docker:**

   - Asegúrate de tener instalados Docker
3. **Iniciar Servicios:**

   - Levanta los servicios necesarios utilizando Docker Compose con el siguiente comando:

     ```bash
     docker compose up -d
     ```

4. **Configurar `application.properties`:**

   - Asegúrate de que el archivo `application.properties` esté correctamente configurado con los detalles de conexión a la base de datos y otras configuraciones necesarias para la aplicación.
