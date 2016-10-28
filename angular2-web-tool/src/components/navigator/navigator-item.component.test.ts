import {
  async,
  inject,
  TestBed,
} from '@angular/core/testing';
import {SolarVillageNavigatorItem} from './navigator-item.component';
import {SolarVillageNavigatorModule} from './navigator.module';
import {configTests} from '../../tests.config';

describe('Component: Navigator Item', () => {
  let fixture;

  beforeEach(done => {
    const configure = (testBed: TestBed) => {
      testBed.configureTestingModule({
        imports: [SolarVillageNavigatorModule],
      });
    };

    configTests(configure).then(testBed => {
      fixture = testBed.createComponent(SolarVillageNavigatorItem);
      fixture.detectChanges();
      done();
    });
  });

  it('should render the button with the correct classes applied',
    async(inject([], () => {
      fixture.whenStable().then(() => {
        fixture.componentInstance.mr = true;
        fixture.componentInstance.ml = true;
        fixture.detectChanges();
        let compiled = fixture.debugElement.nativeElement;
        expect(compiled.querySelector('div').getAttribute('class'))
          .toBe('truncate mr2 ml2');
        fixture.componentInstance.mr = false;
        fixture.detectChanges();
        expect(compiled.querySelector('div').getAttribute('class'))
          .toBe('truncate ml2');
      });
    })
  ));
});
