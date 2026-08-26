import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Produto } from '../../models/produto.model';
import { ProdutoService } from '../../services/produto.service';
import { ProdutoCardComponent } from '../produto-card/produto-card.component';

@Component({
  selector: 'app-cardapio',
  standalone: true,
  imports: [CommonModule, ProdutoCardComponent],
  templateUrl: './cardapio.component.html',
  styleUrls: ['./cardapio.component.scss'],
})
export class CardapioComponent implements OnInit {
  produtos: Produto[] = [];
  carregando = true;

  constructor(private produtoService: ProdutoService) {}

  ngOnInit(): void {
    this.produtoService.listar().subscribe({
      next: (dados) => {
        this.produtos = dados.filter(p => p.disponivel);
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar cardápio', erro);
        this.carregando = false;
      }
    });
  }
}
