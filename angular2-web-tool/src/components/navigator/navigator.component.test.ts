import {
  async,
  inject,
  TestBed,
} from '@angular/core/testing';
import {Component} from '@angular/core';
import {By} from '@angular/platform-browser';
import {SolarVillageNavigator} from './navigator.component';
import {SolarVillageFormModule} from '../form/form.module';
import {SolarVillageNavigatorModule} from './navigator.module';
import {configTests} from '../../tests.config';

describe('Component: Navigator', () => {
  let fixture;

  beforeEach(done => {
    const configure = (testBed: TestBed) => {
      testBed.configureTestingModule({
        imports: [
          SolarVillageNavigatorModule,
        ],
        declarations: [
          SolarVillageNavigatorTestController
        ],
      });
    };

    configTests(configure).then(testBed => {
      fixture = testBed.createComponent(SolarVillageNavigatorTestController);
      fixture.detectChanges();
      done();
    });
  });

  it('should create the component', async(inject([], () => {
    fixture.whenStable().then(() => {
      fixture.autoDetectChanges();
      let query = fixture.debugElement
        .query(By.directive(SolarVillageNavigator));
      expect(query).toBeTruthy();
      expect(query.componentInstance).toBeTruthy();
    });
  })));
});

@Component({
  selector: 'test',
  template: `
    <sv-navigator></sv-navigator>
  `
})
class SolarVillageNavigatorTestController { }
