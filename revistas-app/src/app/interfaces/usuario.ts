export interface Usuario {
    nombre:     string;
    contraseña: string;
    confirmacionContraseña: string | null;  
    rol:        TipoRol | null;
}

export enum TipoRol {
    ADMINISTRADOR = "ADMINISTRADOR",
    EDITOR = "EDITOR",
    LECTOR = "LECTOR",
    ANUNCIADOR = "ANUNCIADOR",
}