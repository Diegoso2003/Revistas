export interface Revista {
    id: number;
    nombre: string;
    fecha: Date;
    nombreEditor: string;
    nombreCategoria: string;
    descripcion: string;
    etiquetasUnidas: string;
    bloqueoComentario: boolean;
    bloqueoAnuncios: boolean;
    bloqueoSuscripcion: boolean;
    precio: number;
    precioBloqueo: number;
    numeroLikes: number;
    pdfs: number[];
    etiquetas: string[];
}