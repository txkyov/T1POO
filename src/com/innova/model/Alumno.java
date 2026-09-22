package com.innova.model;

public class Alumno {
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private char nivelSocioeconomico;
    private String tipoBeca;

    public Alumno(String nombre, String tipoDocumento, String numeroDocumento, String nivelSocioeconomico, String tipoBeca) {
        this.nombre = nombre;
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setNivelSocioeconomico(nivelSocioeconomico);
        setTipoBeca(tipoBeca);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        if (tipoDocumento == null) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo.");
        }
        if (tipoDocumento.equalsIgnoreCase("DNI") || tipoDocumento.equalsIgnoreCase("Residencia Temporal")) {
            this.tipoDocumento = tipoDocumento;
        } else {
            throw new IllegalArgumentException("Tipo de documento invalido. Debe ser 'DNI' o 'Residencia Temporal'.");
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (this.tipoDocumento == null) {
            throw new IllegalStateException("Debe asignar el tipo de documento antes del numero.");
        }
        if (this.tipoDocumento.equalsIgnoreCase("DNI")) {
            if (numeroDocumento.length() == 8 && numeroDocumento.matches("\\d+")) {
                this.numeroDocumento = numeroDocumento;
            } else {
                throw new IllegalArgumentException("El DNI debe tener exactamente 8 digitos numericos.");
            }
        } else if (this.tipoDocumento.equalsIgnoreCase("Residencia Temporal")) {
            if (numeroDocumento.length() == 11 && numeroDocumento.matches("\\d+")) {
                this.numeroDocumento = numeroDocumento;
            } else {
                throw new IllegalArgumentException("El Carne de Residencia Temporal debe tener 11 digitos numericos.");
            }
        }
    }

    public char getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }

    public void setNivelSocioeconomico(String nivelSocioeconomico) {
        if (nivelSocioeconomico == null || nivelSocioeconomico.trim().isEmpty()) {
            throw new IllegalArgumentException("El nivel no puede estar vacio.");
        }
        String nivel = nivelSocioeconomico.trim().toLowerCase();
        if (nivel.equals("a") || nivel.equals("alto")) {
            this.nivelSocioeconomico = 'A';
        } else if (nivel.equals("b") || nivel.equals("medio")) {
            this.nivelSocioeconomico = 'B';
        } else if (nivel.equals("c") || nivel.equals("bajo")) {
            this.nivelSocioeconomico = 'C';
        } else {
            throw new IllegalArgumentException("Nivel invalido. Debe ser Alto(A), Medio(B) o Bajo(C).");
        }
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        if (tipoBeca == null) {
            throw new IllegalArgumentException("El tipo de beca no puede ser nulo.");
        }
        if (tipoBeca.equalsIgnoreCase("Ninguna") || 
            tipoBeca.equalsIgnoreCase("Parcial") || 
            tipoBeca.equalsIgnoreCase("Total")) {
            this.tipoBeca = tipoBeca;
        } else {
            throw new IllegalArgumentException("Beca invalida. Debe ser 'Ninguna', 'Parcial' o 'Total'.");
        }
    }

    private double obtenerTarifaBase() {
        switch (this.nivelSocioeconomico) {
            case 'A': return 1000.0;
            case 'B': return 800.0;
            case 'C': return 600.0;
            default: return 0.0;
        }
    }

    private double obtenerPorcentajeDescuento() {
        if (this.tipoBeca.equalsIgnoreCase("Parcial")) {
            return 0.50;
        } else if (this.tipoBeca.equalsIgnoreCase("Total")) {
            return 1.00;
        }
        return 0.0;
    }

    public double calcularPensionFinal() {
        double tarifaBase = obtenerTarifaBase();
        double descuento = tarifaBase * obtenerPorcentajeDescuento();
        return tarifaBase - descuento;
    }

    @Override
    public String toString() {
        return String.format("Alumno: %s | Doc: %s (%s) | Nivel: %c | Beca: %s | Pension a pagar: S/ %.2f",
                nombre, tipoDocumento, numeroDocumento, nivelSocioeconomico, tipoBeca, calcularPensionFinal());
    }
}
