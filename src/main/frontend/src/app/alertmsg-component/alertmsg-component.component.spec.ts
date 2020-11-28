import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlertmsgComponentComponent } from './alertmsg-component.component';

describe('AlertmsgComponentComponent', () => {
  let component: AlertmsgComponentComponent;
  let fixture: ComponentFixture<AlertmsgComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlertmsgComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AlertmsgComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
