import React from 'react';//step1
//PROPS AND STATE ARE BASICALLY THE ONLY VARIABLES YOU NEED IN REACT
//step 2 make a function, name it the same as the file, camelcase
//step 4 return some jsx
const MyComponent = ({text,asd,color}) => {//IMPORTANT props are readonly
    return(
        <div>
            <h1>{text}</h1>
        </div>
    );
};
//step 3 export
export default MyComponent;