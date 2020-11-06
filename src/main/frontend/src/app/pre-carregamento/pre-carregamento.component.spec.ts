import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreCarregamentoComponent } from './pre-carregamento.component';

describe('PreCarregamentoComponent', () => {
  let component: PreCarregamentoComponent;
  let fixture: ComponentFixture<PreCarregamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreCarregamentoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreCarregamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
