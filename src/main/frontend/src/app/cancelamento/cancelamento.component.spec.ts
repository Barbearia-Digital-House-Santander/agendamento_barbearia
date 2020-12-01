import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelamentoComponent } from './cancelamento.component';

describe('CancelamentoComponent', () => {
  let component: CancelamentoComponent;
  let fixture: ComponentFixture<CancelamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelamentoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
