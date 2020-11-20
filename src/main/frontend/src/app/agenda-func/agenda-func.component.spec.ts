import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaFuncComponent } from './agenda-func.component';

describe('AgendaFuncComponent', () => {
  let component: AgendaFuncComponent;
  let fixture: ComponentFixture<AgendaFuncComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgendaFuncComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgendaFuncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
