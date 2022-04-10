package com.gafahtec.util;

public enum Constantes {
	SEXO (6),
	TIPODOCUMENTO (3);

	private final Integer levelCode;

    private Constantes(Integer levelCode) {
        this.levelCode = levelCode;
    }
	
    public int getLevelCode() {
        return this.levelCode;
    }
}
