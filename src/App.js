import React from 'react';
import axios from 'axios';
import './App.css';

function App() {
const [local_id, inputId] = React.useState('');
const [local_body, inputBody] = React.useState('');
const url = 'http://localhost:1234'
//const [list, setList] = React.useState('');

const saveNote = () => {
  const requestObj = {
    _id: local_id,
    body: local_body,
  }

  if (local_body !== ''){
    if (local_id !== ''){
      // Update a note
      axios.post(url.concat('/update'), requestObj)
        .then(console.log)
        .catch(console.log)
    } else {
      // Store new note
      axios.post(url.concat('/store'), requestObj)
        .then(console.log)
        .catch(console.log)
    }
  }
};

const deleteNote = () => {
  if (local_id !== ''){
    axios.post(url.concat('/delete'), {_id: local_id})
      .then(console.log)
      .catch(console.log)
  }
}

  return (
    <div className="App">
      <header className="App-header">
        <h1>_id</h1>
        <input value={local_id} onChange={e => inputId(e.target.value)} />
        <h1>body</h1>
        <input value={local_body} onChange={e => inputBody(e.target.value)} />
        <button onClick={saveNote}>Save</button>
        <button onClick={deleteNote}>Delete</button>
      </header>
    </div>
  );
}

export default App;
