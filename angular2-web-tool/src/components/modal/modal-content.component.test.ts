import { TestBed, inject } from '@angular/core/testing';
import { Component } from '@angular/core';
import { By } from '@angular/platform-browser';
import { SolarVillageModalContent } from './modal-content.component';
import { SolarVillageModalModule } from './modal.module';
import { configTests } from '../../tests.config';

describe('Component: Modal Content', () => {
  let fixture;

  beforeEach(done => {
    const configure = (testBed: TestBed) => {
      testBed.configureTestingModule({
        imports: [
          SolarVillageModalModule
        ],
        declarations: [
          SolarVillageModalContentTestController
        ],
      });
    };

    configTests(configure).then(testBed => {
      fixture = testBed.createComponent(SolarVillageModalContentTestController);
      fixture.detectChanges();
      done();
    });
  });

  it('should create the component', inject([], () => {
    fixture.whenStable().then(() => {
      fixture.autoDetectChanges();
      let query = fixture.debugElement
        .query(By.directive(SolarVillageModalContent));
      expect(query).toBeTruthy();
      expect(query.componentInstance).toBeTruthy();
    });
  }));
});

@Component({
  selector: 'test',
  template: `
    <sv-modal-content></sv-modal-content>
  `
})
class SolarVillageModalContentTestController {}
