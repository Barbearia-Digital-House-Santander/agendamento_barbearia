import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisponiblidadeFuncionarioComponent } from './disponiblidade-funcionario.component';

describe('DisponiblidadeFuncionarioComponent', () => {
  let component: DisponiblidadeFuncionarioComponent;
  let fixture: ComponentFixture<DisponiblidadeFuncionarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisponiblidadeFuncionarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisponiblidadeFuncionarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
