import java.util.ArrayList;

public class Caso {
    private String nombreCaso;
    private String codigoIdentificacion;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombreCaso, String codigoIdentificacion, String detectiveResponsable) {
        this.nombreCaso = nombreCaso;
        this.codigoIdentificacion = codigoIdentificacion;
        this.detectiveResponsable = detectiveResponsable;
        this.ubicaciones = new Ubicacion[5]; // Requisito: Máximo 5 ubicaciones
        this.pistas = new ArrayList<>();
    }

    public String getNombreCaso() { return nombreCaso; }
    public String getCodigoIdentificacion() { return codigoIdentificacion; }
    public String getDetectiveResponsable() { return detectiveResponsable; }
    public Ubicacion[] getUbicaciones() { return ubicaciones; }
    public ArrayList<Pista> getPistas() { return pistas; }

    // --- MÉTODOS DE UBICACIONES ---

    public void registrarUbicacion(int posicion, Ubicacion ub) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("Posición fuera del límite del arreglo (0-4).");
        }
        if (ubicaciones[posicion] != null) {
            throw new IllegalStateException("La posición seleccionada ya está ocupada.");
        }
        ubicaciones[posicion] = ub;
    }

    public Ubicacion consultarUbicacion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("Posición inválida.");
        }
        return ubicaciones[posicion];
    }

    public void modificarUbicacion(int posicion, int nuevoRiesgo, String nuevoEstado) {
        Ubicacion ub = consultarUbicacion(posicion);
        if (ub == null) {
            throw new IllegalArgumentException("No se puede modificar una posición vacía (null).");
        }
        ub.setNivelRiesgo(nuevoRiesgo);
        ub.setEstado(nuevoEstado);
    }

    public void descartarUbicacion(int posicion) {
        if (posicion < 0 || posicion >= ubicaciones.length) {
            throw new IndexOutOfBoundsException("Posición inválida.");
        }
        if (ubicaciones[posicion] == null) {
            throw new IllegalArgumentException("La posición ya se encuentra vacía.");
        }
        ubicaciones[posicion] = null;
    }

    // --- MÉTODOS DE PISTAS ---

    public void registrarPista(Pista pista) {
        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una pista con el código especificado.");
        }
        pistas.add(pista);
    }

    public Pista buscarPista(String codigo) {
        for (Pista p : pistas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void modificarPista(String codigo, String nuevaDesc, String nuevoTipo, int nuevaImp, int nuevaConf) {
        Pista p = buscarPista(codigo);
        if (p == null) {
            throw new IllegalArgumentException("No se encontró la pista solicitada.");
        }
        p.setDescripcion(nuevaDesc);
        p.setTipoEvidencia(nuevoTipo);
        p.setNiveles(nuevaImp, nuevaConf);
    }

    public void eliminarPista(String codigo) {
        Pista p = buscarPista(codigo);
        if (p == null) {
            throw new IllegalArgumentException("No se encontró la pista a eliminar.");
        }
        pistas.remove(p);
    }

    // --- REPORTE Y CÁLCULOS ---

    public int cantidadUbicacionesRegistradas() {
        int contador = 0;
        for (Ubicacion u : ubicaciones) {
            if (u != null) contador++;
        }
        return contador;
    }

    public int cantidadEspaciosDisponibles() {
        return ubicaciones.length - cantidadUbicacionesRegistradas();
    }

    public Ubicacion ubicacionMayorRiesgo() {
        Ubicacion mayor = null;
        for (Ubicacion u : ubicaciones) {
            if (u != null) {
                if (mayor == null || u.getNivelRiesgo() > mayor.getNivelRiesgo()) {
                    mayor = u;
                }
            }
        }
        return mayor;
    }

    public Pista pistaMayorImportancia() {
        if (pistas.isEmpty()) return null;
        Pista mayor = pistas.get(0);
        for (Pista p : pistas) {
            if (p.getNivelImportancia() > mayor.getNivelImportancia()) {
                mayor = p;
            }
        }
        return mayor;
    }

    public Pista pistaMayorConfiabilidad() {
        if (pistas.isEmpty()) return null;
        Pista mayor = pistas.get(0);
        for (Pista p : pistas) {
            if (p.getNivelConfiabilidad() > mayor.getNivelConfiabilidad()) {
                mayor = p;
            }
        }
        return mayor;
    }

    public double promedioImportanciaPistas() {
        if (pistas.isEmpty()) return 0.0;
        double suma = 0;
        for (Pista p : pistas) {
            suma += p.getNivelImportancia();
        }
        return suma / pistas.size();
    }
}