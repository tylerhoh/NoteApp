import React from 'react';
import logo from './logo.svg';
import produce from 'immer';
//import axios from 'axios';
import './App.css';

function App() {
  // const[text,setText] = React.useState('');
  // const [responseText, setResponse] = React.useState(''); // creates state variable, retuns tuple
  // const[_id,set_id]= React.useState('');
  const Notes = props => props.data.map(note => <div>{note.text}</div>);

  const handleClick = () => {
    const text = document.querySelector('#noteinput').value.trim();
    if (text) {
      const nextState = produce(data,draftState =>{
        draftState.push({text});
      });
      document.querySelector('#noteinput').value = '';
      if(typeof window !== 'undefined'){
        localStorage.setItem('data',JSON.stringify(nextState));
      }
      setData(nextState);
    }
  };
  
  const initialData = [{ text: 'Loading Notes...' }];
  
  const [data, setData] = React.useState(initialData);

  
React.useEffect(()=>{
  if(typeof window !== 'undefined'){
    const getData = localStorage.getItem('data');
  if(getData!== ''&& getData !== null){
    return setData(JSON.parse(getData));
  }
  return setData([]);
}
},[])
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className=" App-logo" alt="logo" />
        <p>
          This app should displays some initial notes
        </p>
        <input value={setData} onChange={e => setData(e.target.value)} />
        <hr />
        <>
        <input style={{ width: '80%' }} type="text" placeholder="Enter a new note" />
        <button onClick={()=>handleClick()}>Save note</button>
        <Notes data={data} />
    </>
      </header>
    </div>
  );
}

export default App;
