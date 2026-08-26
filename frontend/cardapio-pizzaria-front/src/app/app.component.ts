import { Component } from '@angular/core';
import { CardapioComponent } from './components/cardapio/cardapio.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CardapioComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'cardapio-pizzaria-front';
}
