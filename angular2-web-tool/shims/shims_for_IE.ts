'use strict';

require('classlist-polyfill'); // classlist: IE9
require('console-polyfill');   // console: IE9
require('raf').polyfill();     // requestAnimationFrame: IE9, Android < 4.4

if (!Object.hasOwnProperty('name')) {
  Object.defineProperty(Function.prototype, 'name', {
    get: function getName() {
      const matches = this.toString()
        .match(/^\s*function\s*([a-zA-z\_\$]*)\s*\(/);
      const name = matches && matches.length > 1 ? matches[1] : '';
      // For better performance only parse once, and then cache the
      // result through a new accessor for repeated access.
      Object.defineProperty(this, 'name', { value: name });
      return name;
    },
  });
}
