// Arrays in js
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array

// arrays can be delared explicitly
const array = [8, 9, 3, 4, 'asd']; // notice types don't matter

// or with new keyword
const anotherArray = new Array(5);

// access array elements
console.log(array[0]);

// change elements
array[1] = 'hello';

array.forEach(item => console.log(item)); // foreach works without having to call stream

const newArray = array.map(item => item + 1) // notice js arrow functions have "=" for arrow
  .filter(i => i % 2 === 0); // no need for collect
console.log(newArray)