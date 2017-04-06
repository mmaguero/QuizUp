# QuizApp

## Temática
Se trata de un juego Quiz, denominado QuizApp con preguntas sobre películas animadas con un público orientativo hacia los niños pero no excluyente, para smartphones Android desde 4.4 Kitkat (API level 20).

## Preguntas
Son 30 preguntas de tres tipos:
+ Pregunta de texto con opciones de respuesta tipo texto: 9
+ Pregunta de texto con opciones de respuesta tipo imagen: 3
+ Pregunta sobre una imagen con opciones de respuesta tipo texto: 15
+ Pregunta sobre un sonido con opciones de respuesta tipo texto: 3

## Modo de juego
+ Conjunto de preguntas aleatorias, 10 en total
+ Recuperadas de una base de datos en texto plano
+ 4 posibles respuestas
+ Se responden una por una
+ Tras un fallo, suena un sonido de error, se puede continuar (se restan dos puntos) o empezar de vuelta el quiz
+ Tras una cierto, suena un sonido de éxito con mensaje.
+ Al finalizar las preguntas del conjunto, suena un sonido de finalización, se muestra puntuación conseguida
+ En todo momento se puede empezar una nueva tanda, consultar puntuación de ronda anterior o abrir otros juegos pero en navegador
+ Único acceso a la base de datos (al crearse actividad principal).

## Instalación
Se puede instalar usando el archivo app-release.apk, aunque se debe habilitar la instalación de aplicaciones de orígenes desconocidos en el smartphone objetivo, generalmente al copiar al teléfono o descargarlo y quererlo ejecutar, pregunta si deseamos habilitarlo.