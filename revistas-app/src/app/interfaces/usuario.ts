export interface Usuario {
    nombre:     string;
    contraseña: string;
    confirmacionContraseña: string;
    rol:        TipoRol;
}

export enum TipoRol {
    ADMINISTRADOR = "administrador",
    EDITOR = "editor",
    LECTOR = "lector",
    ANUNCIADOR = "anunciador",
}