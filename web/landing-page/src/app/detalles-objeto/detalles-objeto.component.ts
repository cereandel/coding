import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalles-objeto',
  standalone: true,
  imports: [],
  templateUrl: './detalles-objeto.component.html',
  styleUrl: './detalles-objeto.component.css'
})
export class DetallesObjetoComponent implements OnInit {

  objeto: string = '';

  constructor(private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.objeto = params['objetosId'];
    });
  }

}
