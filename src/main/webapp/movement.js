/** The function that calls the two functions below */
function callsotherfunctions(){
    function loadgetmovement();
    function creategetmovementElement(movement);
}


/** Fetches movement hashtags from the server and adds them to the DOM. */
function loadgetmovement() {
  fetch('/get-movement').then(response => response.json()).then((movements));
  const movementListElement = document.getElementById('movement-list');
  movementListElement.appendChild(getMovementElement(movement));
    
  }


/** Uses the fields that were in Stop Asian Hate */
function getmovementElement(movement) {
    getmovementElement.name = 'name';
    getmovementElement.description = 'description';
    getmovementElement.hashtag = 'hashtag';
    getmovementElement.video = 'video';
    getmovementElement.image = 'image';
    getmovementElement.donatation = 'donatation';
    getmovementElement.resources = 'resources'; 
}




for (var i = 0, keys = Object.keys(LinkedHashMap), ii = keys.length; i < ii; i++) {
  console.log('key : ' + keys[i] + ' val : ' + LinkedHashMap[keys[i]]);
}

async function getBot() {
    const responseFromServer = await fetch('/twitter-bot' + new URLSearchParams({
    hashtag: '#stopasianhate',

  }));
  const textFromResponse = await responseFromServer.text();

  const botContainer = document.getElementById('bot');
  botContainer.innerText = textFromResponse;
}
