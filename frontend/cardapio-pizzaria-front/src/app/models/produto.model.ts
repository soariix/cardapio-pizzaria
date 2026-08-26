export interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  categoria: 'PIZZA_SALGADA' | 'PIZZA_DOCE' | 'BEBIDA' | 'SOBREMESA' | 'ACOMPANHAMENTO';
  imagemUrl?: string;
  disponivel: boolean;
}
