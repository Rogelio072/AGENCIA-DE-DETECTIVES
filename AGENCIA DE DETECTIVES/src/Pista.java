public class Pista {
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;

    public Pista(String codigo, String descripcion, String tipoEvidencia, int nivelImportancia, int nivelConfiabilidad) {
        validarNiveles(nivelImportancia, nivelConfiabilidad);
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoEvidencia = tipoEvidencia;
        this.nivelImportancia = nivelImportancia;
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    private void validarNiveles(int importancia, int confiabilidad) {
        if (importancia < 1 || importancia > 10) {
            throw new IllegalArgumentException("El nivel de importancia debe estar entre 1 y 10.");
        }
        if (confiabilidad < 0 || confiabilidad > 100) {
            throw new IllegalArgumentException("El nivel de confiabilidad debe estar entre 0 y 100.");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoEvidencia() {
        return tipoEvidencia;
    }

    public void setTipoEvidencia(String tipoEvidencia) {
        this.tipoEvidencia = tipoEvidencia;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public int getNivelConfiabilidad() {
        return nivelConfiabilidad;
    }

    public void setNiveles(int nivelImportancia, int nivelConfiabilidad) {
        validarNiveles(nivelImportancia, nivelConfiabilidad);
        this.nivelImportancia = nivelImportancia;
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    @Override
    public String toString() {
        return "Pista {" +
                "Código='" + codigo + '\'' +
                ", Descripción='" + descripcion + '\'' +
                ", Tipo Evidencia='" + tipoEvidencia + '\'' +
                ", Importancia=" + nivelImportancia +
                ", Confiabilidad=" + nivelConfiabilidad + "%" +
                '}';
    }
}