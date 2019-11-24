import React, { Component } from 'react';
import './App.css';

class App extends Component {
  state={
    quote: '',
    author: '',
    transQuote: '',
    check: '0',
  };

  getQuote = () => {
    const url = 'https://jsonp.afeld.me/?callback=&url=https://breaking-bad-quotes.herokuapp.com/v1/quotes';
    fetch(url)
      .then(response => response.json())
      .then(data => this.setState({
        quote: data[0].quote,
        author: data[0].author,
        check: '1',
      }))
      .catch(e => console.log('error in fetching ffs', e));
  }

  getTranslation = () => {
    if(this.state.check === '1'){
      const apikey = 'trnsl.1.1.20181003T110140Z.b371e1a35f1a4d4e.259317feb1c740b78e6556d9771fe5327ae86304';
      const url = 'https://translate.yandex.net/api/v1.5/tr.json/translate?key='+apikey+'&text="'+this.state.quote+'"&lang=en-no';
      fetch(url)
        .then(response=> response.json())
        .then(data => this.setState({
          transQuote: data.text,
          check: '0',
        }))
        .catch(e => console.log('error in trans', e));
      }
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          {this.state.quote !== '' &&
            <div>
              Quote:"{this.state.quote}"<br></br>
              Author:{this.state.author}<br></br>
              Translated text: {this.state.transQuote}
            </div>
          }
          

          <br></br><button className="button" onClick = {this.getQuote}>Click me to get a quote</button>
          {this.getTranslation()}

        </header>
      </div>
    );
  }
}

export default App;
