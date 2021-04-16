/** Fetches movement hashtags from the server and adds them to the DOM. */
function loadMovements() {
  fetch('/get-hashtags').then(response => response.json()).then((movements) => {
    const movementListElement = document.getElementById('movement-list');
    movements.forEach((movement) => {
      movementListElement.appendChild(createMovementElement(movement));
    })
  });
}

/** Creates an element that represents a task, including its delete button. */
function createMovementElement(movement) {
  const movementElement = document.createElement('li');
  const linebreak = document.createElement("br");

  const titleElement = document.createElement('span');
  titleElement.innerText = movement;

  
  movementElement.appendChild(linebreak);
  movementElement.appendChild(titleElement);
  return movementElement;
}





