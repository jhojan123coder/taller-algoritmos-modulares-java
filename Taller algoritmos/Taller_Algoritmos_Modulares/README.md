# TALLER DE ALGORITMOS MODULARES - JAVA

## Contenido
- `Main.java`: código fuente de los tres casos seleccionados.
- `Informe_Taller.docx`: informe editable con análisis, algoritmos, pruebas de escritorio, validaciones y conclusiones.
- `Informe_Taller.pdf`: versión lista para lectura e impresión.
- `INSTRUCCIONES_ENTREGA.txt`: pasos finales para publicar y entregar.

## Casos desarrollados
1. Sistema de control de turnos para una IPS.
2. Sistema de seguimiento académico de notas.
3. Sistema de control de producción semanal para una fábrica de empaques.

## Ejecución
Requiere JDK 8 o superior. En una terminal ubicada en esta carpeta:

```bash
javac Main.java
java Main
```

El método `main` coordina el menú principal y llama a los módulos. Se utilizan variables, condicionales, ciclos, arreglos, matrices, funciones y procedimientos, sin crear clases propias para los registros.

## Supuestos documentados
- Caso académico: definitiva como promedio simple de los tres parciales; asistencia mínima del 80%; aprobado desde 3.0, habilita desde 2.0 y reprueba por debajo de 2.0. El enunciado no fija ponderaciones ni umbrales.
- Producción: un día se marca como bajo si el total del día queda por debajo del promedio de los cinco totales diarios.
