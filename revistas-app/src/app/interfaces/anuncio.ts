import { TipoAnuncio, Vigencia } from "./anunciocreate";

export interface Anuncio {
    imagen: File;
    textoAnuncio: string;
    urlVideo: string;
    tipo: TipoAnuncio;
    vigencia: Vigencia;
    fecha: Date;
    id: number;
    usuario: string;
    precio: number;
    extension: string;
}