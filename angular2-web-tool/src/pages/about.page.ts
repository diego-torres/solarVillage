import { Component } from '@angular/core';
import { SolarVillageContainer } from '../components';

@Component({
  selector: 'sv-about-page',
  template: `
    <sv-container [size]=4 [center]=true>
      <h2 class="caps">About Us</h2>
      <p>
      <strong>Solar Village</strong> specializes in the leasing of residential grid-enabled solar systems.
      </p>
      <p>
      The purpose of the POC is to determine the feasibility of BPM Suite to orchestrate the organization’s new order permitting business process.
      </p>
      <p>
      At this time, the focus of the POC is more on the functionality of the Red Hat BPM product. If successful, this POC would lead into a larger initiative that investigates the scalability and high-availability capabilities of the BPM Suite product.
      </p>
<p>Some of the characteristics of the new order permitting business process are as follows:
<ol>
<li>Due to legacy bureaucratic red-tape with government permitting agencies, the lifespan of a new order permitting business process instance is typically several weeks.</li>
<li>Many residences are members of a Home Owner’s Association. For these residences:<br/>
<ul><li>Approval for solar installation from the home owner’s association is required.</li>
<li>Solar Village company policy is always to send a Solar Village representative from the sales department to attend a home owner association meeting.</li>
<li>If by one week prior to the home owner association meeting no one from the sales department has accepted the task, then the task should be re-assigned to an executive and an email should be sent out.</li>
</ul>
</li>
<li>For a residential solar installation, the following government permits are required:
<ul><li>Residential electric permit</li>
<li>Residential structural permit</li>
</ul></li>
<li>Fortunately, all government agencies provide remote on line services that:<br/>
<ul><li>Accept new permit requests (ie: http PUT)</li>
<li>Provides the status (ie; APPROVED, DENIED, IN_PROGRESS) of that permit request (ie: http GET)</li>
<li>Allow for cancellation of that permit request (ie: http DELETE)</li>
</ul>
</li>
<li>The application for both electrical and structural permits can occur in parallel after approval from the home owner’s association.</li>
<li>If one of the government permits is approved and the other has been denied, then the approved permit needs to be rescinded.</li>
<li>Once approval from the HOA and both government agencies has been achieved, the new order permitting business process can be considered complete.</li>
</ol>
</p>
    </sv-container>
  `
})
export class SolarVillageAboutPage {}
