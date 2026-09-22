package com.innova.model;

public class Alumno {
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private char nivelSocioeconomico; // 'A', 'B', 'C'
    private String tipoBeca; // "Ninguna", "Parcial", "Total"

    public Alumno(String nombre, String tipoDocumento, String numeroDocumento, char nivelSocioeconomico, String tipoBeca) {
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
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        if (tipoDocumento.equalsIgnoreCase("DNI") || tipoDocumento.equalsIgnoreCase("Residencia Temporal")) {
            this.tipoDocumento = tipoDocumento;
        } else {
            throw new IllegalArgumentException("Tipo de documento inválido. Debe ser 'DNI' o 'Residencia Temporal'.");
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (this.tipoDocumento == null) {
            throw new IllegalStateException("Debe asignar el tipo de documento antes del número.");
        }
        
        if (this.tipoDocumento.equalsIgnoreCase("DNI")) {
            if (numeroDocumento.length() == 8 && numeroDocumento.matches("\\d+")) {
                this.numeroDocumento = numeroDocumento;
            } else {
                throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos numéricos.");
            }
        } else if (this.tipoDocumento.equalsIgnoreCase("Residencia Temporal")) {
            if (numeroDocumento.length() == 11 && numeroDocumento.matches("\\d+")) {
                this.numeroDocumento = numeroDocumento;
            } else {
                throw new IllegalArgumentException("El Carné de Residencia Temporal debe tener exactamente 11 dígitos numéricos.");
            }
        }
    }

    public char getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }

    public void setNivelSocioeconomico(char nivelSocioeconomico) {
        char nivel = Character.toUpperCase(nivelSocioeconomico);
        if (nivel == 'A' || nivel == 'B' || nivel == 'C') {
            this.nivelSocioeconomico = nivel;
        } else {
            throw new IllegalArgumentException("El nivel socioeconómico debe ser 'A', 'B' o 'C'.");
        }
    }

    public String getTipoBeca() {
        return tipoBeca;
    }

    public void setTipoBeca(String tipoBeca) {
        if (tipoBeca.equalsIgnoreCase("Ninguna") || 
            tipoBeca.equalsIgnoreCase("Parcial") || 
            tipoBeca.equalsIgnoreCase("Total")) {
            this.tipoBeca = tipoBeca;
        } else {
            throw new IllegalArgumentException("El tipo de beca debe ser 'Ninguna', 'Parcial' o 'Total'.");
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
            return 0.50; // 50% de descuento
        } else if (this.tipoBeca.equalsIgnoreCase("Total")) {
            return 1.00; // 100% de descuento (queda exento)
        }
        return 0.0; // Sin beca
    }

    public double calcularPensionFinal() {
        double tarifaBase = obtenerTarifaBase();
        double descuento = tarifaBase * obtenerPorcentajeDescuento();
        return tarifaBase - descuento;
    }

    @Override
    public String toString() {
        return String.format("Alumno: %s | Documento: %s (%s) | Nivel: %c | Beca: %s | Pensión a pagar: S/ %.2f",
                nombre, tipoDocumento, numeroDocumento, nivelSocioeconomico, tipoBeca, calcularPensionFinal());
    }
}
