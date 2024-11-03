export interface AnuncioCreate {
    imagen: File;
    texto: string;
    video: string;
    tipo: TipoAnuncio;
    vigencia: Vigencia;
    fecha: any;
}

export enum TipoAnuncio {
    TEXTO = 'TEXTO',
    TEXTO_E_IMAGEN = 'TEXTO_E_IMAGEN',
    VIDEO = 'VIDEO',
}

export enum Vigencia {
    DIA_1 = 'DIA_1',
    DIA_3 = 'DIA_3',
    SEMANA_1 = 'SEMANA_1',
    SEMANA_2 = 'SEMANA_2',
}