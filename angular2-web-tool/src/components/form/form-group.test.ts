import { async, inject, TestBed } from '@angular/core/testing';
import { Component } from '@angular/core';
import { By } from '@angular/platform-browser';
import { SolarVillageFormGroup } from './form-group';
import { SolarVillageFormModule } from './form.module';
import { configTests } from '../../tests.config';

describe('', () => {});

@Component({
  selector: 'test',
  template:'<sv-form-group qaid="test-1"></sv-form-group>'
})
class SolarVillageFormGroupTestController {}
