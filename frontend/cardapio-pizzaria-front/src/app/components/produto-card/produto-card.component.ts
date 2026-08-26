import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Produto } from '../../models/produto.model';

@Component({
  selector: 'app-produto-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './produto-card.component.html',
  styleUrls: ['./produto-card.component.scss'],
})
export class ProdutoCardComponent {
  @Input({ required: true }) produto!: Produto;

  numeroWhatsapp = '5581999999999';

  pedirPeloWhatsapp() {
    const mensagem = `Olá! Gostaria de pedir: *${this.produto.nome}* - R$ ${this.produto.preco.toFixed(2)}`;
    const url = `https://wa.me/${this.numeroWhatsapp}?text=${encodeURIComponent(mensagem)}`;
    window.open(url, '_blank');
  }
}
