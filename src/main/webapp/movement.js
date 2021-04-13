/** The function that calls the two functions below */
function callsotherfunctions(){
    function loadgetmovement();
    function creategetmovementElement(movement);
}


/** Fetches movement hashtags from the server and adds them to the DOM. */
function loadgetmovement() {
  fetch('/get-movement').then(response => response.json()).then((movements) => {
    const movementListElement = document.getElementById('movement-list');
    movements.forEach((movement) => {
      movementListElement.appendChild(createMovementElement(movement));
    })
  });
}


function creategetmovementElement(movement) {
  const getmovementElement = document.createElement('li');
  getmovementElement.className = 'movement';

  const nameElement = document.createElement('span');
  nameElement.innerText = movement.name;

  const descriptionElement = document.createElement('span');
  descriptionElement.innerText = movement.description;

  const hashtagElement = document.createElement('span');
  hashtagElement.innerText = movement.hashtag;

  taskElement.appendChild(titleElement);
  taskElement.appendChild(deleteButtonElement);
  return taskElement;
}


for (var i = 0, keys = Object.keys(LinkedHashMap), ii = keys.length; i < ii; i++) {
  console.log('key : ' + keys[i] + ' val : ' + LinkedHashMap[keys[i]]);
}