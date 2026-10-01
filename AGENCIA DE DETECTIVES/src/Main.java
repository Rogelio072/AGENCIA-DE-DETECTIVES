import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("  SISTEMA DE LA AGENCIA DE DETECTIVES   ");

        // Pedimos los datos del primer caso al iniciar el programa
        System.out.println("\n--- REGISTRO DEL CASO ---");
        System.out.print("Ingrese el nombre del caso: ");
        String nombreC = scanner.nextLine();
        
        System.out.print("Ingrese el codigo del caso: ");
        String codigoC = scanner.nextLine();
        
        System.out.print("Ingrese el nombre del detective a cargo: ");
        String detectiveC = scanner.nextLine();
        
        // Creamos el objeto del caso
        Caso casoActual = new Caso(nombreC, codigoC, detectiveC);
        System.out.println("Caso registrado con éxito!\n");

        int opcion = 0;

        // Bucle para repetir el menú hasta que el usuario decida salir (opción 13)
        while (opcion != 13) {
            System.out.println("\n----------------- MENU -----------------");
            System.out.println("1. Registrar un nuevo caso (reiniciar)");
            System.out.println("2. Registrar ubicación");
            System.out.println("3. Consultar todas las ubicaciones");
            System.out.println("4. Consultar una ubicación específica");
            System.out.println("5. Modificar una ubicación");
            System.out.println("6. Descartar una ubicación");
            System.out.println("7. Registrar una pista");
            System.out.println("8. Consultar todas las pistas");
            System.out.println("9. Buscar una pista por código");
            System.out.println("10. Modificar una pista");
            System.out.println("11. Eliminar una pista");
            System.out.println("12. Ver reporte de la investigación");
            System.out.println("13. Salir");
            System.out.print("Elija una opción del menú: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Para limpiar el salto de línea

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- NUEVO CASO ---");
                        System.out.print("Nombre del nuevo caso: ");
                        String nom = scanner.nextLine();
                        System.out.print("Código del nuevo caso: ");
                        String cod = scanner.nextLine();
                        System.out.print("Detective a cargo: ");
                        String det = scanner.nextLine();
                        
                        casoActual = new Caso(nom, cod, det);
                        System.out.println("Se ha iniciado un nuevo caso desde cero.");
                        break;

                    case 2:
                        try {
                            System.out.print("Ingrese la posición del arreglo donde guardará la ubicación (0 a 4): ");
                            int pos = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Código de la ubicación: ");
                            String codU = scanner.nextLine();

                            System.out.print("Nombre del lugar: ");
                            String nomU = scanner.nextLine();

                            System.out.print("Dirección o descripción: ");
                            String dirU = scanner.nextLine();

                            System.out.print("Nivel de riesgo (del 1 al 10): ");
                            int riesgoU = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Estado del lugar: ");
                            String estU = scanner.nextLine();

                            Ubicacion nuevaUbi = new Ubicacion(codU, nomU, dirU, riesgoU, estU);
                            casoActual.registrarUbicacion(pos, nuevaUbi);
                            System.out.println("Ubicación guardada correctamente.");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingresó un texto en lugar de un número entero.");
                            scanner.nextLine(); // Limpiar el error
                        } catch (Exception e) {
                            System.out.println("Error al guardar: " + e.getMessage());
                        } finally {
                            System.out.println("(Fin del intento de registro de ubicación)");
                        }
                        break;

                    case 3:
                        System.out.println("\n--- LISTA DE UBICACIONES ---");
                        Ubicacion[] arregloUbi = casoActual.getUbicaciones();
                        boolean hayAlgo = false;

                        for (int i = 0; i < arregloUbi.length; i++) {
                            if (arregloUbi[i] != null) {
                                System.out.println("Posición " + i + ": " + arregloUbi[i].toString());
                                hayAlgo = true;
                            }
                        }

                        if (hayAlgo == false) {
                            System.out.println("No hay ninguna ubicación registrada todavia.");
                        }
                        break;

                    case 4:
                        try {
                            System.out.print("¿Qué posición desea consultar? (0-4): ");
                            int posConsulta = scanner.nextInt();
                            scanner.nextLine();

                            Ubicacion ubiEncontrada = casoActual.consultarUbicacion(posConsulta);

                            if (ubiEncontrada == null) {
                                System.out.println("Esa posición está vacía (null).");
                            } else {
                                System.out.println("Datos de la ubicación: " + ubiEncontrada.toString());
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número.");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            System.out.print("Ingrese la posición de la ubicación a modificar (0-4): ");
                            int posMod = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Ingrese el nuevo nivel de riesgo (1-10): ");
                            int nuevoRiesgo = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Ingrese el nuevo estado: ");
                            String nuevoEstado = scanner.nextLine();

                            casoActual.modificarUbicacion(posMod, nuevoRiesgo, nuevoEstado);
                            System.out.println("Ubicación modificada con éxito.");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: No ingresó un número válido.");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6:
                        try {
                            System.out.print("Ingrese la posición de la ubicación a descartar (0-4): ");
                            int posDesc = scanner.nextInt();
                            scanner.nextLine();

                            casoActual.descartarUbicacion(posDesc);
                            System.out.println("La ubicación ha sido descartada (ahora es null).");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe escribir un número entero.");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 7:
                        try {
                            System.out.print("Código de la pista: ");
                            String codP = scanner.nextLine();

                            System.out.print("Descripción: ");
                            String descP = scanner.nextLine();

                            System.out.print("Tipo de evidencia: ");
                            String tipoP = scanner.nextLine();

                            System.out.print("Nivel de importancia (1 a 10): ");
                            int impP = scanner.nextInt();

                            System.out.print("Nivel de confiabilidad (0 a 100): ");
                            int confP = scanner.nextInt();
                            scanner.nextLine();

                            Pista nuevaPista = new Pista(codP, descP, tipoP, impP, confP);
                            casoActual.registrarPista(nuevaPista);
                            System.out.println("Pista guardada exitosamente.");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar números en los niveles de importancia y confiabilidad.");
                            scanner.nextLine();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 8:
                        System.out.println("\n--- LISTA DE PISTAS ---");
                        if (casoActual.getPistas().size() == 0) {
                            System.out.println("No hay pistas registradas en el ArrayList.");
                        } else {
                            for (int i = 0; i < casoActual.getPistas().size(); i++) {
                                System.out.println(casoActual.getPistas().get(i).toString());
                            }
                        }
                        break;

                    case 9:
                        System.out.print("Ingrese el código de la pista que quiere buscar: ");
                        String codBuscar = scanner.nextLine();

                        Pista pBuscada = casoActual.buscarPista(codBuscar);

                        if (pBuscada != null) {
                            System.out.println("Pista encontrada: " + pBuscada.toString());
                        } else {
                            System.out.println("No se encontró ninguna pista con ese código.");
                        }
                        break;

                    case 10:
                        try {
                            System.out.print("Ingrese el código de la pista a modificar: ");
                            String codMod = scanner.nextLine();

                            System.out.print("Nueva descripción: ");
                            String nuevaDesc = scanner.nextLine();

                            System.out.print("Nuevo tipo de evidencia: ");
                            String nuevoTipo = scanner.nextLine();

                            System.out.print("Nuevo nivel de importancia (1-10): ");
                            int nuevaImp = scanner.nextInt();

                            System.out.print("Nuevo nivel de confiabilidad (0-100): ");
                            int nuevaConf = scanner.nextInt();
                            scanner.nextLine();

                            casoActual.modificarPista(codMod, nuevaDesc, nuevoTipo, nuevaImp, nuevaConf);
                            System.out.println("Pista actualizada con éxito.");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingrese números en las opciones numéricas.");
                            scanner.nextLine();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 11:
                        System.out.print("Ingrese el código de la pista que desea eliminar: ");
                        String codEli = scanner.nextLine();

                        try {
                            casoActual.eliminarPista(codEli);
                            System.out.println("La pista fue eliminada del ArrayList.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 12:
                        System.out.println("\n==========================================");
                        System.out.println("        REPORTE DE LA INVESTIGACION        ");
                        System.out.println("==========================================");
                        System.out.println("Caso: " + casoActual.getNombreCaso() + " (Código: " + casoActual.getCodigoIdentificacion() + ")");
                        System.out.println("Detective responsable: " + casoActual.getDetectiveResponsable());
                        System.out.println("------------------------------------------");
                        System.out.println("Ubicaciones registradas: " + casoActual.cantidadUbicacionesRegistradas());
                        System.out.println("Espacios disponibles: " + casoActual.cantidadEspaciosDisponibles());

                        Ubicacion ubiRiesgo = casoActual.ubicacionMayorRiesgo();
                        if (ubiRiesgo != null) {
                            System.out.println("Ubicación con mayor riesgo: " + ubiRiesgo.getNombre() + " (Nivel: " + ubiRiesgo.getNivelRiesgo() + ")");
                        } else {
                            System.out.println("Ubicación con mayor riesgo: No hay ubicaciones.");
                        }

                        System.out.println("------------------------------------------");
                        System.out.println("Cantidad de pistas: " + casoActual.getPistas().size());

                        if (casoActual.getPistas().size() > 0) {
                            Pista pImp = casoActual.pistaMayorImportancia();
                            Pista pConf = casoActual.pistaMayorConfiabilidad();

                            System.out.println("Pista con mayor importancia: " + pImp.getCodigo() + " - " + pImp.getDescripcion() + " (Nivel: " + pImp.getNivelImportancia() + ")");
                            System.out.println("Pista con mayor confiabilidad: " + pConf.getCodigo() + " - " + pConf.getDescripcion() + " (" + pConf.getNivelConfiabilidad() + "%)");
                            System.out.println("Promedio de importancia de las pistas: " + casoActual.promedioImportanciaPistas());
                        } else {
                            System.out.println("No hay datos suficientes de pistas para mostrar los promedios y máximos.");
                        }
                        System.out.println("==========================================\n");
                        break;

                    case 13:
                        System.out.println("Saliendo del programa... ¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción incorrecta. Por favor elija un número del 1 al 13.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("¡Error! Debe ingresar un número entero para seleccionar la opción del menú.");
                scanner.nextLine(); // Limpiamos la entrada del scanner para evitar bucles infinitos
            }
        }

        scanner.close();
    }
}