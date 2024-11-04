import { TipoAnuncio, Vigencia } from "./anunciocreate";

export interface Anuncio {
    imagen: File | null;
    textoAnuncio: string;
    urlVideo: string;
    tipo: TipoAnuncio;
    vigencia: Vigencia;
    fecha: Date | null;
    id: number;
    usuario: string;
    precio: number;
    extension: string;
}