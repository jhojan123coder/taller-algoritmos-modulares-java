import java.util.Scanner;

/*
 * TALLER DE ALGORITMOS MODULARES
 * Solucion de los casos 1, 3 y 5.
 * Implementacion procedural: arreglos, matrices, ciclos, condicionales,
 * funciones y procedimientos. No se crean objetos propios.
 */
public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ", 0, 3);

            switch (opcion) {
                case 1:
                    ejecutarCasoPacientes();
                    break;
                case 2:
                    ejecutarCasoNotas();
                    break;
                case 3:
                    ejecutarCasoProduccion();
                    break;
                case 0:
                    System.out.println("Programa finalizado. Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
            if (opcion != 0) {
                System.out.println("\nPresione ENTER para volver al menu...");
                sc.nextLine();
            }
        } while (opcion != 0);
    }

    // ------------------------- MENU PRINCIPAL -------------------------
    static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("       SISTEMA DE ALGORITMOS MODULARES");
        System.out.println("==============================================");
        System.out.println("1. Sistema de control de turnos para una IPS");
        System.out.println("2. Sistema de seguimiento academico de notas");
        System.out.println("3. Sistema de control de produccion semanal");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------------");
    }

    // ------------------------- UTILIDADES -----------------------------
    static int leerEntero(String mensaje, int minimo, int maximo) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                sc.nextLine();
                if (valor >= minimo && valor <= maximo) return valor;
            } else {
                sc.nextLine();
            }
            System.out.println("Dato invalido. Ingrese un numero entre "
                    + minimo + " y " + maximo + ".");
        }
    }

    static double leerDecimal(String mensaje, double minimo, double maximo) {
        double valor;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                sc.nextLine();
                if (valor >= minimo && valor <= maximo) return valor;
            } else {
                sc.nextLine();
            }
            System.out.println("Dato invalido. Ingrese un valor entre "
                    + minimo + " y " + maximo + ".");
        }
    }

    static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) System.out.println("El campo no puede quedar vacio.");
        } while (texto.isEmpty());
        return texto;
    }

    // ==================================================================
    // CASO 1. CONTROL DE TURNOS PARA UNA IPS
    // ==================================================================
    static void ejecutarCasoPacientes() {
        final int MAX = 30;
        String[] documentos = new String[MAX];
        String[] nombres = new String[MAX];
        String[] tipos = new String[MAX];
        int[] edades = new int[MAX];
        int[] prioridades = new int[MAX];
        int cantidad = 0;
        int opcion;

        do {
            System.out.println("\n--- CONTROL DE TURNOS IPS ---");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Mostrar listado general");
            System.out.println("3. Buscar paciente por documento");
            System.out.println("4. Contar pacientes por tipo de atencion");
            System.out.println("5. Mostrar paciente con mayor prioridad");
            System.out.println("0. Volver al menu principal");
            opcion = leerEntero("Opcion: ", 0, 5);

            switch (opcion) {
                case 1:
                    if (cantidad == MAX) {
                        System.out.println("No hay cupos disponibles: limite de 30 pacientes.");
                    } else {
                        cantidad = registrarPaciente(documentos, nombres, edades,
                                tipos, prioridades, cantidad);
                    }
                    break;
                case 2:
                    mostrarPacientes(documentos, nombres, edades, tipos, prioridades, cantidad);
                    break;
                case 3:
                    buscarPaciente(documentos, nombres, edades, tipos, prioridades, cantidad);
                    break;
                case 4:
                    contarPorTipo(tipos, cantidad);
                    break;
                case 5:
                    mostrarMayorPrioridad(documentos, nombres, prioridades, cantidad);
                    break;
            }
        } while (opcion != 0);
    }

    static int registrarPaciente(String[] documentos, String[] nombres, int[] edades,
                                 String[] tipos, int[] prioridades, int cantidad) {
        String documento;
        boolean repetido;
        do {
            documento = leerTexto("Numero de documento: ");
            repetido = false;
            for (int i = 0; i < cantidad; i++) {
                if (documentos[i].equalsIgnoreCase(documento)) repetido = true;
            }
            if (repetido) System.out.println("Ya existe un paciente con ese documento.");
        } while (repetido);

        documentos[cantidad] = documento;
        nombres[cantidad] = leerTexto("Nombre completo: ");
        edades[cantidad] = leerEntero("Edad (0-120): ", 0, 120);

        System.out.println("Tipo de atencion: 1. Urgencias  2. Consulta general  3. Prioritaria");
        int tipo = leerEntero("Seleccione: ", 1, 3);
        tipos[cantidad] = nombreTipo(tipo);
        prioridades[cantidad] = leerEntero("Nivel de prioridad (1-5): ", 1, 5);

        cantidad++;
        System.out.println("Paciente registrado correctamente. Cupos ocupados: "
                + cantidad + "/30.");
        return cantidad;
    }

    static String nombreTipo(int tipo) {
        if (tipo == 1) return "Urgencias";
        if (tipo == 2) return "Consulta general";
        return "Prioritaria";
    }

    static void mostrarPacientes(String[] documentos, String[] nombres, int[] edades,
                                 String[] tipos, int[] prioridades, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        System.out.println("\nDOCUMENTO | NOMBRE | EDAD | ATENCION | PRIORIDAD");
        for (int i = 0; i < cantidad; i++) {
            System.out.println(documentos[i] + " | " + nombres[i] + " | "
                    + edades[i] + " | " + tipos[i] + " | " + prioridades[i]);
        }
    }

    static void buscarPaciente(String[] documentos, String[] nombres, int[] edades,
                               String[] tipos, int[] prioridades, int cantidad) {
        String buscado = leerTexto("Documento que desea consultar: ");
        int posicion = -1;
        for (int i = 0; i < cantidad; i++) {
            if (documentos[i].equalsIgnoreCase(buscado)) posicion = i;
        }
        if (posicion == -1) {
            System.out.println("No se encontro un paciente con ese documento.");
        } else {
            System.out.println("Paciente: " + nombres[posicion] + " | Edad: "
                    + edades[posicion] + " | Atencion: " + tipos[posicion]
                    + " | Prioridad: " + prioridades[posicion]);
        }
    }

    static void contarPorTipo(String[] tipos, int cantidad) {
        int urgencias = 0, consulta = 0, prioritaria = 0;
        for (int i = 0; i < cantidad; i++) {
            if (tipos[i].equals("Urgencias")) urgencias++;
            else if (tipos[i].equals("Consulta general")) consulta++;
            else if (tipos[i].equals("Prioritaria")) prioritaria++;
        }
        System.out.println("Urgencias: " + urgencias);
        System.out.println("Consulta general: " + consulta);
        System.out.println("Prioritaria: " + prioritaria);
    }

    static void mostrarMayorPrioridad(String[] documentos, String[] nombres,
                                      int[] prioridades, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        int mayor = prioridades[0];
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > mayor) mayor = prioridades[i];
        }
        System.out.println("Mayor nivel de prioridad registrado: " + mayor);
        for (int i = 0; i < cantidad; i++) {
            if (prioridades[i] == mayor) {
                System.out.println(nombres[i] + " | Documento: " + documentos[i]);
            }
        }
    }

    // ==================================================================
    // CASO 3. SEGUIMIENTO ACADEMICO DE NOTAS
    // ==================================================================
    static void ejecutarCasoNotas() {
        final int MAX = 25;
        String[] codigos = new String[MAX];
        String[] nombres = new String[MAX];
        double[][] notas = new double[MAX][3];
        double[] asistencia = new double[MAX];
        double[] definitivas = new double[MAX];
        int cantidad = 0;
        int opcion;

        do {
            System.out.println("\n--- SEGUIMIENTO ACADEMICO ---");
            System.out.println("1. Registrar estudiante y sus notas");
            System.out.println("2. Mostrar reporte de estudiantes");
            System.out.println("3. Consultar promedio general del grupo");
            System.out.println("4. Identificar mejor y peor promedio");
            System.out.println("0. Volver al menu principal");
            opcion = leerEntero("Opcion: ", 0, 4);

            switch (opcion) {
                case 1:
                    if (cantidad == MAX) {
                        System.out.println("Se alcanzo el limite de 25 estudiantes.");
                    } else {
                        cantidad = registrarEstudiante(codigos, nombres, notas,
                                asistencia, definitivas, cantidad);
                    }
                    break;
                case 2:
                    mostrarReporteAcademico(codigos, nombres, notas, asistencia,
                            definitivas, cantidad);
                    break;
                case 3:
                    mostrarPromedioGrupo(definitivas, cantidad);
                    break;
                case 4:
                    mostrarMejorPeor(codigos, nombres, definitivas, cantidad);
                    break;
            }
        } while (opcion != 0);
    }

    static int registrarEstudiante(String[] codigos, String[] nombres, double[][] notas,
                                   double[] asistencia, double[] definitivas, int cantidad) {
        String codigo;
        boolean repetido;
        do {
            codigo = leerTexto("Codigo del estudiante: ");
            repetido = false;
            for (int i = 0; i < cantidad; i++) {
                if (codigos[i].equalsIgnoreCase(codigo)) repetido = true;
            }
            if (repetido) System.out.println("Ese codigo ya esta registrado.");
        } while (repetido);

        codigos[cantidad] = codigo;
        nombres[cantidad] = leerTexto("Nombre completo: ");
        for (int j = 0; j < 3; j++) {
            notas[cantidad][j] = leerDecimal("Nota parcial " + (j + 1)
                    + " (0.0 a 5.0): ", 0.0, 5.0);
        }
        asistencia[cantidad] = leerDecimal("Porcentaje de asistencia (0 a 100): ",
                0.0, 100.0);
        definitivas[cantidad] = calcularDefinitiva(notas[cantidad]);
        cantidad++;
        System.out.println("Estudiante registrado. Definitiva: "
                + String.format("%.2f", definitivas[cantidad - 1])
                + " - Estado: " + determinarEstado(definitivas[cantidad - 1],
                asistencia[cantidad - 1]));
        return cantidad;
    }

    static double calcularDefinitiva(double[] parciales) {
        return (parciales[0] + parciales[1] + parciales[2]) / 3.0;
    }

    static String determinarEstado(double definitiva, double asistencia) {
        // Criterio operativo adoptado porque el enunciado no fija porcentajes:
        // asistencia minima 80%; aprobado desde 3.0; habilita desde 2.0.
        if (asistencia < 80.0) return "Reprueba (asistencia inferior al 80%)";
        if (definitiva >= 3.0) return "Aprobado";
        if (definitiva >= 2.0) return "Habilita";
        return "Reprueba";
    }

    static void mostrarReporteAcademico(String[] codigos, String[] nombres,
                                        double[][] notas, double[] asistencia,
                                        double[] definitivas, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("\nCODIGO | NOMBRE | P1 | P2 | P3 | DEFINITIVA | ASISTENCIA | ESTADO");
        for (int i = 0; i < cantidad; i++) {
            System.out.printf("%s | %s | %.2f | %.2f | %.2f | %.2f | %.1f%% | %s%n",
                    codigos[i], nombres[i], notas[i][0], notas[i][1], notas[i][2],
                    definitivas[i], asistencia[i],
                    determinarEstado(definitivas[i], asistencia[i]));
        }
    }

    static void mostrarPromedioGrupo(double[] definitivas, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay datos para calcular el promedio.");
            return;
        }
        double suma = 0;
        for (int i = 0; i < cantidad; i++) suma += definitivas[i];
        System.out.printf("Promedio general del grupo: %.2f%n", suma / cantidad);
    }

    static void mostrarMejorPeor(String[] codigos, String[] nombres,
                                 double[] definitivas, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        int mejor = 0, peor = 0;
        for (int i = 1; i < cantidad; i++) {
            if (definitivas[i] > definitivas[mejor]) mejor = i;
            if (definitivas[i] < definitivas[peor]) peor = i;
        }
        System.out.printf("Mejor promedio: %s (%s) - %.2f%n",
                nombres[mejor], codigos[mejor], definitivas[mejor]);
        System.out.printf("Peor promedio: %s (%s) - %.2f%n",
                nombres[peor], codigos[peor], definitivas[peor]);
    }

    // ==================================================================
    // CASO 5. CONTROL DE PRODUCCION SEMANAL
    // ==================================================================
    static void ejecutarCasoProduccion() {
        final int OPERARIOS = 10;
        final int DIAS = 5;
        String[] codigos = new String[OPERARIOS];
        String[] nombres = new String[OPERARIOS];
        int[][] produccion = new int[OPERARIOS][DIAS];
        int cantidad = 0;
        int opcion;

        do {
            System.out.println("\n--- CONTROL DE PRODUCCION ---");
            System.out.println("1. Registrar operarios");
            System.out.println("2. Ingresar produccion diaria");
            System.out.println("3. Calcular produccion semanal por operario");
            System.out.println("4. Identificar mejor operario");
            System.out.println("5. Mostrar promedio general de produccion");
            System.out.println("6. Detectar dias de baja produccion");
            System.out.println("0. Volver al menu principal");
            opcion = leerEntero("Opcion: ", 0, 6);

            switch (opcion) {
                case 1:
                    cantidad = registrarOperarios(codigos, nombres, cantidad);
                    break;
                case 2:
                    ingresarProduccion(produccion, nombres, cantidad);
                    break;
                case 3:
                    mostrarProduccionSemanal(codigos, nombres, produccion, cantidad);
                    break;
                case 4:
                    mostrarMejorOperario(codigos, nombres, produccion, cantidad);
                    break;
                case 5:
                    mostrarPromedioProduccion(produccion, cantidad);
                    break;
                case 6:
                    detectarDiasBajaProduccion(produccion, cantidad);
                    break;
            }
        } while (opcion != 0);
    }

    static int registrarOperarios(String[] codigos, String[] nombres, int cantidad) {
        if (cantidad == 10) {
            System.out.println("Ya se registraron los 10 operarios permitidos.");
            return cantidad;
        }
        int nuevos = leerEntero("Cuantos operarios desea registrar? ",
                1, 10 - cantidad);
        for (int i = 0; i < nuevos; i++) {
            String codigo;
            boolean repetido;
            do {
                codigo = leerTexto("Codigo del operario: ");
                repetido = false;
                for (int j = 0; j < cantidad; j++) {
                    if (codigos[j].equalsIgnoreCase(codigo)) repetido = true;
                }
                if (repetido) System.out.println("Codigo ya registrado.");
            } while (repetido);
            codigos[cantidad] = codigo;
            nombres[cantidad] = leerTexto("Nombre del operario: ");
            cantidad++;
        }
        System.out.println("Total de operarios registrados: " + cantidad);
        return cantidad;
    }

    static void ingresarProduccion(int[][] produccion, String[] nombres, int cantidad) {
        if (cantidad == 0) {
            System.out.println("Primero debe registrar al menos un operario.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Produccion de " + nombres[i] + ":");
            for (int d = 0; d < 5; d++) {
                produccion[i][d] = leerEntero("Dia " + (d + 1)
                        + " - unidades empacadas: ", 0, Integer.MAX_VALUE);
            }
        }
        System.out.println("Produccion diaria almacenada correctamente.");
    }

    static int sumarSemana(int[] dias) {
        int suma = 0;
        for (int d = 0; d < dias.length; d++) suma += dias[d];
        return suma;
    }

    static void mostrarProduccionSemanal(String[] codigos, String[] nombres,
                                         int[][] produccion, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay operarios registrados.");
            return;
        }
        System.out.println("\nCODIGO | OPERARIO | TOTAL SEMANAL");
        for (int i = 0; i < cantidad; i++) {
            System.out.println(codigos[i] + " | " + nombres[i] + " | "
                    + sumarSemana(produccion[i]));
        }
    }

    static void mostrarMejorOperario(String[] codigos, String[] nombres,
                                     int[][] produccion, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay operarios registrados.");
            return;
        }
        int mejor = 0;
        for (int i = 1; i < cantidad; i++) {
            if (sumarSemana(produccion[i]) > sumarSemana(produccion[mejor])) mejor = i;
        }
        System.out.println("Mejor operario de la semana: " + nombres[mejor]
                + " (" + codigos[mejor] + "), con "
                + sumarSemana(produccion[mejor]) + " unidades.");
    }

    static void mostrarPromedioProduccion(int[][] produccion, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay datos para calcular el promedio.");
            return;
        }
        int total = 0;
        for (int i = 0; i < cantidad; i++) total += sumarSemana(produccion[i]);
        System.out.printf("Promedio general: %.2f unidades por operario-dia.%n",
                (double) total / (cantidad * 5));
    }

    static void detectarDiasBajaProduccion(int[][] produccion, int cantidad) {
        if (cantidad == 0) {
            System.out.println("No hay datos de produccion.");
            return;
        }
        int[] totalesDia = new int[5];
        int totalSemana = 0;
        for (int i = 0; i < cantidad; i++) {
            for (int d = 0; d < 5; d++) {
                totalesDia[d] += produccion[i][d];
                totalSemana += produccion[i][d];
            }
        }
        double promedioDiario = (double) totalSemana / 5.0;
        System.out.printf("Promedio diario de la fabrica: %.2f unidades.%n", promedioDiario);
        boolean hayBajos = false;
        for (int d = 0; d < 5; d++) {
            System.out.println("Dia " + (d + 1) + ": " + totalesDia[d] + " unidades.");
            if (totalesDia[d] < promedioDiario) {
                System.out.println("  ALERTA: produccion inferior al promedio semanal diario.");
                hayBajos = true;
            }
        }
        if (!hayBajos) System.out.println("No se detectaron dias por debajo del promedio.");
    }
}
